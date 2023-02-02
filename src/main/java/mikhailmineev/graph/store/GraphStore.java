package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Node;

import java.util.Map;

public interface GraphStore {

    /**
     * Creates a new graph.
     *
     * @return All nodes in graph with references to them
     */
    Map<String, Node> buildGraph();
}
