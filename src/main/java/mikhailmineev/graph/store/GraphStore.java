package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Node;

import java.util.Map;

public interface GraphStore {

    Map<String, Node> returnGraph();
}
