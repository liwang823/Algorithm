import bean.Node;
import binaryTreeAlgorithm.binaryTreePrint;
import org.junit.Test;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 14:01
 */
public class binaryTreeAlgorithmTest {

    public static Node node01 = new Node(1);
    public static Node node02 = new Node(2);
    public static Node node03 = new Node(3);
    public static Node node04 = new Node(4);
    public static Node node05 = new Node(5);

    static {
        node01.left = node02;
        node01.right = node03;
        node02.left = node04;
        node02.right = node05;
    }

    @Test
    public void preOrderRecurTest(){
        binaryTreePrint.preOrderRecur(node01);
    }

    @Test
    public void preOrderUnRecurTest(){
        binaryTreePrint.preOrderUnRecur(node01);
    }

    @Test
    public void postOrderRecurTest(){
        binaryTreePrint.postOrderRecur(node01);
    }

    @Test
    public void postOrderUnRecurTest(){
        binaryTreePrint.postOrderUnRecur(node01);
    }
}
