package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

import java.util.Collection;
import java.util.Collections;

public class NoStatistics implements Statistics {

    @Override
    public void setNodesVisited(Collection<Node> nodesVisited) {
        // do nothing
    }

    @Override
    public Collection<Node> getNodesVisited() {
        return Collections.emptyList();
    }

    @Override
    public Collection<String> getNodesNamesVisited() {
        return Collections.emptyList();
    }
}