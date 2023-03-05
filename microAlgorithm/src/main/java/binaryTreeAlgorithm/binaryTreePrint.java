package binaryTreeAlgorithm;

import bean.Node;

import java.util.Stack;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 13:58
 */
public class binaryTreePrint {

    public static void postOrderUnRecur(Node head){
        if (head == null){
            return;
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);

        while (! s1.isEmpty()){
            Node cur = s1.pop();
            s2.push(cur);

            if (cur.left != null){
                s1.push(cur.left);
            }

            if (cur.right != null){
                s1.push(cur.right);
            }
        }

        while (! s2.isEmpty()){
            Node cur = s2.pop();
            System.out.println(cur.val);
        }
    }

    // 左 - 右 - 根
    public static void postOrderRecur(Node head){
        if (head == null){
            return;
        }

        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.println(head.val);
    }

    // 左 - 根 - 右
    public static void inOrderUnRecur(Node head){
        if (head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (! stack.isEmpty() || head != null){
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.println(head.val);
                head = head.right;
            }
        }
    }

    public static void inOrderRecur(Node head){
        if (head == null){
            return;
        }

        inOrderRecur(head.left);
        System.out.println(head.val);
        inOrderRecur(head.right);
    }

    // 根 - 左 - 右
    public static void preOrderRecur(Node head){
        if (head == null){
            return;
        }

        System.out.println(head.val);
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void preOrderUnRecur(Node head){
        if (head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (! stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.val);

            if (cur.right != null){
                stack.push(cur.right);
            }

            if (cur.left != null){
                stack.push(cur.left);
            }
        }
    }
}
