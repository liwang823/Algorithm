package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/5 22:20
 */

public class Graph {

   public HashMap<Integer, GraphNode> nodes;

   public List<GraphEdge> edges;

   public Graph(){
       nodes = new HashMap<>();
       edges = new ArrayList<>();
   }
}
