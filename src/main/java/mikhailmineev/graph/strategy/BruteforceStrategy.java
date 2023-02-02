package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.core.Route;
import mikhailmineev.graph.stats.NoStatistics;
import mikhailmineev.graph.stats.Statistics;

import java.util.*;

public class BruteforceStrategy implements Strategy {

    public Route findRoute(String from, String to, Map<String, Node> graph) {
        return findRoute(from, to, graph, new NoStatistics());
    }

    @Override
    public Route findRoute(String from, String to, Map<String, Node> graph, Statistics statistics) {
        Node start = Validations.getNode(from, graph);
        Node end = Validations.getNode(to, graph);

        Set<Node> visited = new HashSet<>();
        statistics.setNodesVisited(visited);

        LinkedList<Pair<Node, Route>> toVisit = new LinkedList<>();
        Route route = new Route(start);

        toVisit.add(new Pair<>(start, route));

        Pair<Node, Route> current;
        while ((current = toVisit.pop()) != null) {
            Node node = current.left();

            if (Objects.equals(node, end)) {
                return current.right();
            }

            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);

            NodeScanner.scanNode(current, toVisit::push, visited);
        }

        throw new RuntimeException("Failed to find route");
    }

}
