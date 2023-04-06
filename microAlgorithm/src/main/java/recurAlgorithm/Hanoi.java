package recurAlgorithm;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/4/2 09:43
 */
public class Hanoi {

    public static void main(String[] args) {
        solution(3);
    }

    public static void solution(int n){
        if (n < 1){
            return;
        }
        process("A", "C", "B", n);
    }

    public static void process(String from, String to, String other, int i){
        if(i == 1){
            System.out.println("move " + i + " from " + from + " to " + to);
            return;
        }

        process(from, other, to, i - 1);
        System.out.println("move " + i + " from " + from + " to " + to);
        process(other, to, from, i - 1);
    }
}
