package algorithmApplication;

import java.util.PriorityQueue;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/9 23:46
 */
public class lessMoneySpiltGold {

    public static int solution(int[] arr){

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : arr){
            priorityQueue.add(num);
        }

        int sum = 0;
        while (priorityQueue.size() > 1){
            int temp = priorityQueue.poll() + priorityQueue.poll();
            sum += temp;
            priorityQueue.add(temp);
        }

        return sum;
    }
}
