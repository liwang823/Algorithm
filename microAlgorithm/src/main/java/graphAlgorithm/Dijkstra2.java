package graphAlgorithm;

import bean.GraphEdge;
import bean.GraphNode;
import bean.NodeRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/20 21:29
 */
public class Dijkstra2 {

    public static Map<GraphNode, Integer> solution(GraphNode head, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        Map<GraphNode, Integer> result = new HashMap<>();
        while (! nodeHeap.isEmpty()){
            NodeRecord nodeRecord = nodeHeap.pop();
            GraphNode cur = nodeRecord.node;
            int distance = nodeRecord.distance;
            for (GraphEdge edge: cur.edges){
                nodeHeap.addOrUpdateOrIgnore(cur, distance + edge.weight);
            }
            result.put(cur, distance);
        }

        return result;
    }

    public static class NodeHeap{

        public GraphNode[] nodes;
        public Map<GraphNode, Integer> indexMap;
        public Map<GraphNode, Integer> distanceMap;
        public int size;

        public NodeHeap(int size){
            nodes = new GraphNode[size];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public boolean isEntered(GraphNode node){
            return indexMap.containsKey(node);
        }

        public boolean inHeap(GraphNode node){
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public void swap(int index1, int index2){
            indexMap.put(nodes[index1], index2);
            indexMap.put(nodes[index2], index1);
            GraphNode temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }

        public void insertHeapify(int index){
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1)/2])){
                swap(index, (index - 1)/2);
                index = (index - 1)/2;
            }
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            indexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        public void heapify(int index, int size){
            int left = index * 2 + 1;
            while (left < size){
                int smallest = left + 1 < size && distanceMap.get(nodes[left]) > distanceMap.get(nodes[left + 1]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;

                if (smallest == index){
                    break;
                }

                swap(smallest, index);
                index = smallest;
                left = 2 * index + 1;
            }
        }

        public void addOrUpdateOrIgnore(GraphNode node, int distance){
            if (inHeap(node)){
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(indexMap.get(node));
            }
            if (! isEntered(node)){
                nodes[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
        }
    }
}
