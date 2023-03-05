package listNodeAlgorithm;

import bean.ListNode;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 18:59
 */
public class existLoop {

    public static ListNode solution(ListNode head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }

        ListNode F = head.next.next;
        ListNode S = head.next;

        while (F != S){
            if (F == null || F.next == null || F.next.next == null){
                return null;
            }
            F = F.next.next;
            S = S.next;
        }

        F = head;

        while (F != S){
            F = F.next;
            S = S.next;
        }

        return F;
    }
}
