package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;

public interface StatisticsWriter {

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
