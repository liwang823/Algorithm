package graphAlgorithm;

import bean.Graph;
import bean.GraphEdge;
import bean.GraphNode;

import java.util.*;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 22:28
 */
public class kruskal {

    public static class UnionFind{

        public HashMap<GraphNode, List<GraphNode>> setMap = new HashMap<>();

        public UnionFind(){}

        public void makeSet(Collection<GraphNode> nodes){
            List<GraphNode> set = new ArrayList<>();

            for (GraphNode node: nodes){
                set.add(node);
                setMap.put(node, set);
            }
        }

        public boolean isSameSet(GraphNode fromNode, GraphNode toNode){
            List<GraphNode> fromSet = setMap.get(fromNode);
            List<GraphNode> toSet = setMap.get(toNode);
            return fromSet == toSet;
        }

        public void Union(GraphNode fromNode, GraphNode toNode){
            List<GraphNode> fromSet = setMap.get(fromNode);
            List<GraphNode> toSet = setMap.get(toNode);
            for (GraphNode node: toSet){
                fromSet.add(node);
                setMap.put(node, fromSet);
            }
        }
    }

    public static class EdgeComparator implements Comparator<GraphEdge>{

        @Override
        public int compare(GraphEdge o1, GraphEdge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static List<GraphEdge> solution(Graph graph){

        List<GraphEdge> result = new ArrayList<>();

        PriorityQueue<GraphEdge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());

        priorityQueue.addAll(graph.edges);

        while (! priorityQueue.isEmpty()){
            GraphEdge cur = priorityQueue.poll();
            GraphNode fromNode = cur.from;
            GraphNode toNode = cur.to;
            if (! unionFind.isSameSet(fromNode, toNode)){
                unionFind.Union(fromNode, toNode);
                result.add(cur);
            }
        }

        return result;
    }
}
