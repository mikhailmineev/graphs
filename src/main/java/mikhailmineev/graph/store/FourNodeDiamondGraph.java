package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Branch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 4 Node graph with structure
 * <pre>
 *   a
 *  / \
 * b   c
 *  \ /
 *   d
 * </pre>
 */
public class FourNodeDiamondGraph implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");

        Branch ab = new Branch(a, b, 3);
        Branch ba = new Branch(b, a, 3);
        Branch ac = new Branch(a, c, 3);
        Branch ca = new Branch(c, a, 3);
        Branch bd = new Branch(b, d, 3);
        Branch db = new Branch(d, b, 3);
        Branch dc = new Branch(d, c, 3);
        Branch cd = new Branch(c, d, 3);

        a.setBranches(Arrays.asList(ab, ac));
        b.setBranches(Arrays.asList(ba, bd));
        c.setBranches(Arrays.asList(ca, cd));
        d.setBranches(Arrays.asList(db, dc));

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
            put(c.getName(), c);
            put(d.getName(), d);
        }};
    }
}
