package mikhailmineev.graph.core;

import java.util.*;

public class Route {

    private final List<Node> path;

    public Route(List<Node> path) {
        this.path = Collections.unmodifiableList(path);
    }

    public Route(Node node) {
        this.path = Collections.singletonList(node);
    }

    public List<String> getNodeNames() {
        return path.stream().map(Node::getName).toList();
    }

    public Route addNode(Node node) {
        List<Node> copy = new ArrayList<>(path);
        copy.add(node);
        return new Route(copy);
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
