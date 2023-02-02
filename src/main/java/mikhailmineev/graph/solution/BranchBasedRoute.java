package mikhailmineev.graph.solution;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class BranchBasedRoute implements Route {

    private final List<Branch> path;

    public BranchBasedRoute() {
        this.path = Collections.emptyList();
    }

    private BranchBasedRoute(List<Branch> path) {
        this.path = Collections.unmodifiableList(path);
    }

    @Override
    public List<String> getNodeNames() {
        var nodes = path.stream().map(Branch::to);
        if (path.size() > 0) {
            var firstNode = path.get(0).from();
            nodes = Stream.concat(Stream.of(firstNode), nodes);
        }
        return nodes.map(Node::getName).toList();
    }

    @Override
    public int depth() {
        return path.size() + 1;
    }

    @Override
    public int length() {
        return path.stream().mapToInt(Branch::length).sum();
    }

    @Override
    public Route addNode(Node node, Branch branchToNode) {
        var copy = new ArrayList<>(path);
        copy.add(branchToNode);
        return new BranchBasedRoute(copy);
    }

    @Override
    public String toString() {
        return String.join(" -> ", path.stream().map(Branch::toString).toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchBasedRoute route = (BranchBasedRoute) o;
        return path.equals(route.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
