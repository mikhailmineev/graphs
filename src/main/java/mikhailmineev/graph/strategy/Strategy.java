package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.NoStatistics;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.Map;

public interface Strategy {

    /**
     * Calculate a route from location A to location B in a graph.
     * 
     * @see Strategy#findRoute(String, String, Map, StatisticsWriter) 
     *
     * @param from the beginning location
     * @param to destination
     * @param graph graph to perform search on
     * @return route from location A to location B
     */
    default Route findRoute(String from, String to, Map<String, Node> graph) {
        return findRoute(from, to, graph, new NoStatistics());
    }

    /**
     * Calculate a route from location A to location B in a graph.
     *
     * @param from the beginning location
     * @param to destination
     * @param graph graph to perform search on
     * @param statistics statistics collector
     * @return route from location A to location B
     */
    Route findRoute(String from, String to, Map<String, Node> graph, StatisticsWriter statistics);
}
