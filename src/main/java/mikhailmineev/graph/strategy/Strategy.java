package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Route;
import mikhailmineev.graph.stats.Statistics;

import java.util.Map;

public interface Strategy {

    Route findRoute(String from, String to, Map<String, Node> graph, Statistics statistics);
}
