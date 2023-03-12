package algorithmApplication;

import bean.MoneyNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/11 19:10
 */
public class findMaximizedCapital {

    public static int solution(int k, int W, int[] Profits, int[] Capital){
        PriorityQueue<MoneyNode> minCostQueue = new PriorityQueue<>(Comparator.comparingInt((MoneyNode o) -> o.cost));
        PriorityQueue<MoneyNode> maxProfitQueue = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);

        // cost 进小根堆
        for (int i = 0; i < Profits.length; i++){
            minCostQueue.add(new MoneyNode(Profits[i], Capital[i]));
        }

        for (int i = 0; i < k; i++){
            while (! minCostQueue.isEmpty() && minCostQueue.peek().cost <= W){
                maxProfitQueue.add(minCostQueue.poll());
            }

            if (maxProfitQueue.isEmpty()){
                return W;
            }

            W += maxProfitQueue.poll().profit;
        }

        return W;
    }
}
