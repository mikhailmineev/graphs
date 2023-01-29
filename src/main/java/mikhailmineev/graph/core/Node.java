package mikhailmineev.graph.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Node {

    private final String name;

    private final List<Branch> branches = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }
}
