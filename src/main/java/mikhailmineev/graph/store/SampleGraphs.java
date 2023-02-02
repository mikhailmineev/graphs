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

    /**
     * German cities graph.
     */
    public static Row[] germanCitiesGraph() {
        return new Row[]{
                new Row("Frankfurt", "Mannheim", 85),
                new Row("Frankfurt", "Wurzburg", 217),
                new Row("Frankfurt", "Kassel", 173),
                new Row("Mannheim", "Karlsruhe", 80),
                new Row("Wurzburg", "Erfurt", 186),
                new Row("Wurzburg", "Numberg", 103),
                new Row("Numberg", "Stuttgart", 183),
                new Row("Kassel", "Munchen", 502),
                new Row("Karlsruhe", "Augsburg", 250),
                new Row("Numberg", "Munchen", 167),
                new Row("Augsburg", "Munchen", 84)
        };
    }

}
