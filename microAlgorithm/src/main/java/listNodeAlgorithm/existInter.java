package listNodeAlgorithm;

import bean.ListNode;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 19:08
 */
public class existInter {

    public static ListNode solution(ListNode node01, ListNode node02){

        if (node01 == null || node02 == null){
            return null;
        }

        ListNode loop01 = existLoop.solution(node01);
        ListNode loop02 = existLoop.solution(node02);

        if (loop01 == null && loop02 == null){
            return noLoop(node01, node02);
        }

        if (loop01 != null && loop02 != null){
            return bothLoop(node01, node02, loop01, loop02);
        }else {
            return null;
        }
    }

    public static ListNode bothLoop(ListNode node01, ListNode node02, ListNode loop01, ListNode loop02){
        ListNode cur1 = null;
        ListNode cur2 = null;

        if (loop01 == loop02){
            cur1 = node01;
            cur2 = node02;

            int n = 0;
            while (cur1 != loop01){
                cur1 = cur1.next;
                n++;
            }

            while (cur2 != loop02){
                cur2 = cur2.next;
                n--;
            }

            cur1 = n > 0 ? node01 : node02;
            cur2 = cur1 == node01 ? node02 : node01;

            n = Math.abs(n);

            while (n > 0){
                cur1 = cur1.next;
                n--;
            }

            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;

        }else {
            cur1 = loop01.next;
            while (cur1 != loop01){
                if (cur1 == loop02){
                    return cur1;
                }
                cur1 = cur1.next;
            }

            return null;
        }
    }

    public static ListNode noLoop(ListNode node01, ListNode node02){
        if (node01 == null || node02 == null){
            return null;
        }

        int n = 0;
        ListNode temp = node01;
        while (node01.next != null){
            node01 = node01.next;
            n++;
        }
        node01 = temp;

        temp = node02;
        while (node02.next != null){
            node02 = node02.next;
            n--;
        }
        node02 = temp;

        // 长链表
        ListNode cur1 = n > 0 ? node01 : node02;

        // 短链表
        ListNode cur2 = cur1 == node01 ? node02: node01;

        // n此时为长度差
        n = Math.abs(n);

        while (n > 0){
            cur1 = cur1.next;
            n--;
        }

        while (cur1 != cur2){
            if (cur1 == null || cur2 == null){
                return null;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }
}
