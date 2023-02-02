package mikhailmineev.graph.store;

public final class SampleGraphs {

    private SampleGraphs() {
        // utility class
    }

    /**
     * 3 Node graph with structure
     * <pre>
     * a - b
     *  \ /
     *   c
     * </pre>
     */
    public static Row[] threeNodeGraph() {
        return new Row[]{
                new Row("a", "b", 3),
                new Row("a", "c", 3),
                new Row("b", "c", 3)
        };
    }


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
    public static Row[] fourNodeDiamondGraph() {
        return new Row[]{
                new Row("a", "b", 3),
                new Row("a", "c", 3),
                new Row("b", "d", 3),
                new Row("c", "d", 3),
        };
    }

    /**
     * 5 Node graph with structure and different weights
     * <pre>
     *      a
     * /(2) |(1) \(2)
     * b    c     d
     * \(2) |(1) /(2)
     *      e
     * </pre>
     */
    public static Row[] fiveNodeWeightedGraph() {
        return new Row[]{
                new Row("a", "b", 2),
                new Row("a", "c", 1),
                new Row("a", "d", 2),
                new Row("b", "e", 2),
                new Row("c", "e", 1),
                new Row("d", "e", 2)
        };
    }

}
