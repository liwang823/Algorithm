package bean;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/20 21:45
 */
public class NodeRecord {

    public GraphNode node;

    public int distance;

    public NodeRecord(GraphNode node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
