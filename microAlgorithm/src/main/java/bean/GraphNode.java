package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 22:21
 */
public class GraphNode {

    public int in;

    public int out;

    public int val;

    public List<GraphNode> nexts;

    public List<GraphEdge> edges;

    public GraphNode(int val){
        this.val = val;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
