package graphAlgorithm;

import bean.Graph;
import bean.GraphEdge;
import bean.GraphNode;

import java.util.*;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/6 22:06
 */

public class Dijkstra {

    public static class EdgeComparator implements Comparator<GraphEdge>{

        @Override
        public int compare(GraphEdge o1, GraphEdge o2){
            return o1.weight - o2.weight;
        }
    }

    public static Map<GraphNode, Integer> solution(GraphNode head){

        Map<GraphNode, Integer> distanceMap = new HashMap<>();
        Set<GraphNode> selectedNodes = new HashSet<>();

        distanceMap.put(head, 0);
        GraphNode minNode = getMinDistanceAndNotSelectedNode(distanceMap, selectedNodes);

        while (minNode != null){
            int distance = distanceMap.get(minNode);
            for (GraphEdge edge: minNode.edges){
                GraphNode toNode = edge.to;
                if (! distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode, edge.weight);
                }else {
                    distanceMap.put(toNode, Math.min(edge.weight + distance, distanceMap.get(toNode)));
                }
            }

            selectedNodes.add(minNode);
            minNode = getMinDistanceAndNotSelectedNode(distanceMap, selectedNodes);
        }

        return distanceMap;
    }

    public static GraphNode getMinDistanceAndNotSelectedNode(Map<GraphNode, Integer> distanceMap, Set<GraphNode> touchedNodes){
        GraphNode minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<GraphNode, Integer> entry: distanceMap.entrySet()){
            GraphNode node = entry.getKey();
            Integer distance = entry.getValue();
            if (! touchedNodes.contains(node) && distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }
}
