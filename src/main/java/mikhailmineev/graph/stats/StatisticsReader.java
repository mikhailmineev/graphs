package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

import java.util.Collection;

public interface StatisticsReader {

    Collection<Node> getNodesVisited();

    Collection<String> getNodesNamesVisited();
}
