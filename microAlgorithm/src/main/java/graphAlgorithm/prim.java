package graphAlgorithm;

import bean.Graph;
import bean.GraphEdge;
import bean.GraphNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 22:42
 */
public class prim {

    public static List<GraphEdge> solution(Graph graph){

        List<GraphEdge> result = new ArrayList<>();

        PriorityQueue<GraphEdge> priorityQueue = new PriorityQueue<>(new kruskal.EdgeComparator());

        HashSet<GraphNode> set = new HashSet<>();

        for (GraphNode node: graph.nodes.values()){

            if (! set.contains(node)){
                priorityQueue.addAll(node.edges);

                while (! priorityQueue.isEmpty()){
                    GraphEdge cur = priorityQueue.poll();
                    if (! set.contains(cur.to)){
                        result.add(cur);
                        set.add(cur.to);

                        priorityQueue.addAll(cur.to.edges);
                    }
                }
            }
        }

        return result;
    }
}
