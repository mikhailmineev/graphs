package mikhailmineev.graph.core;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Node {

    private final String name;

    private final LazyImmutable<List<Branch>> branches = new LazyImmutable<>(Collections::unmodifiableList);

    public Node(String name) {
        this.name = name;
    }

    public void setBranches(List<Branch> branches) {
        this.branches.setDelegate(branches);
    }

    public List<Branch> getBranches() {
        return branches.getDelegate();
    }
}
