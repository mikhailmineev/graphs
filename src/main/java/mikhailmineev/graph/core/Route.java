package mikhailmineev.graph.core;

import java.util.*;
import java.util.stream.Collectors;

public class Route {

    private final List<Pair<Node, Branch>> path;

    private Route(Node path) {
        this(new Pair<>(path, null));
    }

    private Route(List<Pair<Node, Branch>> path) {
        this.path = Collections.unmodifiableList(path);
    }

    private Route(Pair<Node, Branch> node) {
        this.path = Collections.singletonList(node);
    }

    public static Route newRoute(Node firstNode) {
        return new Route(firstNode);
    }

    public List<String> getNodeNames() {
        return path.stream().map(Pair::left).map(Node::getName).toList();
    }

    public int depth() {
        return path.size();
    }

    public int length() {
        return path.stream().map(Pair::right).filter(Objects::nonNull).mapToInt(Branch::length).sum();
    }

    public Route addNode(Node node, Branch branchToNode) {
        List<Pair<Node, Branch>> copy = new ArrayList<>(path);
        copy.add(new Pair<>(node, branchToNode));
        return new Route(copy);
    }

    @Override
    public String toString() {
        return "Path" + path.stream()
                .map(e -> " node " + e.left().getName() + " with branch to it " + e.right())
                .collect(Collectors.joining());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return path.equals(route.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
