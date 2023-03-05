package bean;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 22:21
 */
public class GraphEdge {

    public GraphNode from;

    public GraphNode to;

    public int weight;

    public GraphEdge(GraphNode from, GraphNode to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
