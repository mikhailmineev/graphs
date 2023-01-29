package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Branch;
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
            Route routeToCurrent = current.right();

            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);

            for (Branch branch : node.getBranches()) {
                var nextNode = branch.to();
                var newRoute = routeToCurrent.addNode(nextNode);

                if (Objects.equals(nextNode, end)) {
                    return newRoute;
                }

                if (!visited.contains(nextNode)) {
                    toVisit.push(new Pair<>(nextNode, newRoute));
                }
            }
        }

        throw new RuntimeException("Failed to find route");
    }
}
