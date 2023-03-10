package graphAlgorithm;

import bean.Graph;
import bean.GraphNode;

import java.util.*;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/10 21:09
 */
public class sortTopology {

    public List<GraphNode> solution(Graph graph){

        Map<GraphNode, Integer> inMap = new HashMap<>();
        Queue<GraphNode> zeroInQueue = new LinkedList<>();

        for (GraphNode node : graph.nodes.values()){
            inMap.put(node, node.in);
            if (inMap.get(node) == 0){
                zeroInQueue.add(node);
            }
        }

        List<GraphNode> result = new ArrayList<>();

        while (! zeroInQueue.isEmpty()){
            GraphNode cur = zeroInQueue.poll();
            result.add(cur);

            for (GraphNode next : cur.nexts){
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }

        return result;
    }
}
