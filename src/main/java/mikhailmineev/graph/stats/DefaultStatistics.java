package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class DefaultStatistics implements Statistics {

    private final Collection<Node> nodesVisited = new LinkedList<>();

    private StatisticsState state = StatisticsState.READY;

    private Route finalRoute;

    @Override
    public void visited(Node node) {
        allowedState(StatisticsState.READY, StatisticsState.RECORDING);
        state = StatisticsState.RECORDING;

        System.out.println("Visited " + node);
        nodesVisited.add(node);
    }

    @Override
    public void found(Route route) {
        allowedState(StatisticsState.RECORDING);
        state = StatisticsState.COMPLETE;

        System.out.println("Found route " + route);
        finalRoute = route;
    }

    @Override
    public Collection<Node> getNodesVisited() {
        allowedState(StatisticsState.COMPLETE);

        return nodesVisited;
    }

    @Override
    public Collection<String> getNodesNamesVisited() {
        allowedState(StatisticsState.COMPLETE);

        return getNodesVisited()
                .stream()
                .map(Node::getName)
                .collect(Collectors.toList());
    }

    @Override
    public StatisticsState getState() {
        return state;
    }

    private void allowedState(StatisticsState... allowedStates) {
        for (StatisticsState state : allowedStates) {
            if (this.state == state) {
                return;
            }
        }
        throw new IllegalStateException("Statistics is in state " + this.state + ", allowed " + Arrays.toString(allowedStates));
    }
}
