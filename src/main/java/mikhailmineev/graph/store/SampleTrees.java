package mikhailmineev.graph.store;

public final class SampleTrees {

    private SampleTrees() {
        // utility class
    }

    /**
     * 2 Node tree with structure
     * <pre>
     * a
     * |
     * b
     * </pre>
     */
    public static Row[] twoNodeTree() {
        return new Row[]{
                new Row("a", "b", 3)
        };
    }

    /**
     * 3 Node tree with structure
     * <pre>
     *   a
     *  / \
     * b   c
     * </pre>
     */
    public static Row[] threeNodeTree() {
        return new Row[]{
                new Row("a", "b", 3),
                new Row("a", "c", 3)
        };
    }

    /**
     * 5 Node tree with structure
     * <pre>
     *    a
     *  / | \
     * b  c  d
     *    |
     *    e
     * </pre>
     */
    public static Row[] fiveNodeTree() {
        return new Row[]{
                new Row("a", "b", 3),
                new Row("a", "c", 3),
                new Row("a", "d", 3),
                new Row("c", "e", 3)
        };
    }
}
