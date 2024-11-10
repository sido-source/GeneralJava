package bigCompanies.interview1;

import java.util.List;
import java.util.Map;

public class GraphDfs {

    // below it should be cyclic graph
    // you can start whenever you want
    // the goal is to print all the letters/nodes of the graph

    List<Map<Character, List<Character>>> graph = List.of(
          Map.of('A', List.of('B', 'C')),
          Map.of('B', List.of('E', 'B')),
          Map.of('C', List.of('E', 'D', 'A')),
          Map.of('D', List.of('A', 'C')),
          Map.of('E', List.of('B', 'A', 'D'))
    );

}
