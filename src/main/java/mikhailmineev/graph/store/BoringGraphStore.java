package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Branch;

import java.util.HashMap;
import java.util.Map;

public class BoringGraphStore implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");

        Branch ab = new Branch(a, b, 3);
        Branch ba = new Branch(b, a, 3);
        Branch ac = new Branch(a, c, 3);
        Branch ca = new Branch(c, a, 3);
        Branch bc = new Branch(b, c, 3);
        Branch cb = new Branch(c, b, 3);

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
            put(c.getName(), c);
        }};
    }
}
