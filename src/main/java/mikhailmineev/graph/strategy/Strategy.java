package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.NoStatistics;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.function.Predicate;

public interface Strategy {

    /**
     * Calculate a route from location A to location B in a graph.
     * 
     * @see Strategy#findRoute(Node, Predicate, StatisticsWriter)
     *
     * @param from the beginning location
     * @param found predicate for the end node we search
     * @return route from location A to location B
     */
    default Route findRoute(Node from, Predicate<Node> found) {
        return findRoute(from, found, new NoStatistics());
    }

    /**
     * Calculate a route from location A to location B in a graph.
     *
     * @param from the beginning location
     * @param found predicate for the end node we search
     * @param statistics statistics collector
     * @return route from location A to location B
     */
    Route findRoute(Node from, Predicate<Node> found, StatisticsWriter statistics);
}
