package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;

import java.util.Collection;
import java.util.Collections;

public class NoStatistics implements Statistics {

    @Override
    public void score(Node node, int score) {
        // do nothing
    }

    @Override
    public void visited(Node node) {
        // do nothing
    }

    @Override
    public void found(Route route) {
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

    @Override
    public StatisticsState getState() {
        return StatisticsState.COMPLETE;
    }
}
