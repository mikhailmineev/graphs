package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

import java.util.Collection;

public interface StatisticsReader {

    /**
     * Get all visited nodes in the order of visit.
     *
     * @return visited nodes
     */
    Collection<Node> getNodesVisited();

    /**
     * Get the names of nodes visited.
     *
     * @see StatisticsReader#getNodesVisited()
     *
     * @return names of visited nodes
     */
    Collection<String> getNodesNamesVisited();
}
