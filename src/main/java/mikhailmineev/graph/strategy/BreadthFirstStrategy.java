package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.*;
import java.util.function.Function;

public class BreadthFirstStrategy implements Strategy {

    private final Function<Node, Route> newRouteSupplier;

    public BreadthFirstStrategy(Function<Node, Route> newRouteSupplier) {
        this.newRouteSupplier = newRouteSupplier;
    }

    @Override
    public Route findRoute(String from, String to, Map<String, Node> graph, StatisticsWriter statistics) {
        Node start = Validations.getNode(from, graph);
        Node end = Validations.getNode(to, graph);

        Set<Node> visited = new HashSet<>();

        Comparator<Pair<Node, Route>> depthFirst = Comparator
                .comparingInt((Pair<Node, Route> e) -> e.right().depth())
                .thenComparing((Pair<Node, Route> e) -> e.left().getName());
        TreeSet<Pair<Node, Route>> toVisit = new TreeSet<>(depthFirst);
        Route route = newRouteSupplier.apply(start);

        toVisit.add(new Pair<>(start, route));


        Pair<Node, Route> current;
        while ((current = toVisit.pollFirst()) != null) {
            Node node = current.left();

            if (Objects.equals(node, end)) {
                statistics.found(current.right());
                return current.right();
            }

            if (visited.contains(node)) {
                continue;
            }

            NodeScanner.scanNode(current, toVisit::add, visited);

            visited.add(node);
            statistics.visited(node);
        }

        throw new RuntimeException("Failed to find route");
    }

}
