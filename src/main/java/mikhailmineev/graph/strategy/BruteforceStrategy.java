package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.core.Route;

import java.util.*;

public class BruteforceStrategy implements Strategy {

    @Override
    public Route findRoute(String from, String to, Map<String, Node> graph) {
        Set<Node> visited = new HashSet<>();
        LinkedList<Pair<Node, Route>> toVisit = new LinkedList<>();
        Node start = graph.get(from);
        Node end = graph.get(to);
        Route route = new Route(start);

        toVisit.add(new Pair<>(start, route));

        Pair<Node, Route> current;
        while ((current = toVisit.pop()) != null) {
            Node node = current.left();

            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);

            Optional<Route> found = NodeScanner.scanNode(current, toVisit::push, visited, end);
            if (found.isPresent()) {
                return found.get();
            }
        }

        throw new RuntimeException("Failed to find route");
    }

}
