package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

public interface StatisticsWriter {

    /**
     * Register a node is visited.
     *
     * @param node visited node
     */
    void visited(Node node);
}
