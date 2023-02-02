package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

import java.util.Collection;

public interface Statistics {

    void setNodesVisited(Collection<Node> nodesVisited);

    Collection<Node> getNodesVisited();

    Collection<String> getNodesNamesVisited();
}
