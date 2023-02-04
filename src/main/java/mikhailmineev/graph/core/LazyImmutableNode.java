package mikhailmineev.graph.core;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class LazyImmutableNode implements Node {

    private final String name;

    private final LazyImmutable<List<Branch>> branches = new LazyImmutable<>(Collections::unmodifiableList);

    public LazyImmutableNode(String name) {
        this.name = name;
    }

    public void setBranches(List<Branch> branches) {
        this.branches.setDelegate(branches);
    }

    @Override
    public List<Branch> getBranches() {
        return branches.getDelegate();
    }

    @Override
    public String toString() {
        return "Node " + name + ", branches " + branches;
    }
}
