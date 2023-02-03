package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;

public interface StatisticsWriter {

    /**
     * Define a score for specified node
     *
     * @param node scored node
     * @param score new score
     */
    void score(Node node, int score);

    /**
     * Register a node is visited.
     *
     * @param node visited node
     */
    void visited(Node node);

    /**
     * Register a route to destination
     *
     * @param route found route
     */
    void found(Route route);
}
