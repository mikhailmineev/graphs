package mikhailmineev.graph.solution;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;

import java.util.*;

public class NodeBasedRoute implements Route {

    private final List<Pair<Node, Branch>> path;

    public NodeBasedRoute(Node path) {
        this(new Pair<>(path, null));
    }

    private NodeBasedRoute(List<Pair<Node, Branch>> path) {
        this.path = Collections.unmodifiableList(path);
    }

    private NodeBasedRoute(Pair<Node, Branch> node) {
        this.path = Collections.singletonList(node);
    }

    @Override
    public List<String> getNodeNames() {
        return path.stream().map(Pair::left).map(Node::getName).toList();
    }

    @Override
    public int depth() {
        return path.size();
    }

    @Override
    public int length() {
        return path.stream().map(Pair::right).filter(Objects::nonNull).mapToInt(Branch::length).sum();
    }

    @Override
    public Route addNode(Node node, Branch branchToNode) {
        List<Pair<Node, Branch>> copy = new ArrayList<>(path);
        copy.add(new Pair<>(node, branchToNode));
        return new NodeBasedRoute(copy);
    }

    @Override
    public String toString() {
        return String.join(" -> ", path.stream().map(Pair::left).map(Node::getName)
                .map(e -> "Node " + e).toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeBasedRoute route = (NodeBasedRoute) o;
        return path.equals(route.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
