import bean.ListNode;
import listNodeAlgorithm.existLoop;
import org.junit.Test;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 19:03
 */
public class listNodeAlgorithmTest {

    @Test
    public void test(){

        ListNode node1 = new ListNode();
        node1.setVal(1);
        ListNode node2 = new ListNode();
        node2.setVal(2);
        ListNode node3 = new ListNode();
        node3.setVal(3);
        ListNode node4 = new ListNode();
        node4.setVal(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode result = existLoop.solution(node1);
        System.out.println(result == null ? null : result.val);
    }
}
