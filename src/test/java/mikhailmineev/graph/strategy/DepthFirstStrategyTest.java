package mikhailmineev.graph.strategy;

import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstStrategyTest extends AbstractStrategyTest {

    public DepthFirstStrategyTest() {
        super(() -> new DepthFirstStrategy(Route::newRoute));
    }

    @Override
    protected void verifyTest2NodePath(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(3, route.length());
        assertEquals(List.of("a"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest4NodePath(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c", "d"), route.getNodeNames());
        assertEquals(6, route.length());
        assertEquals(List.of("a", "c"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest3NodeTree(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(3, route.length());
        assertEquals(List.of("a"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest5NodeTree(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c", "e"), route.getNodeNames());
        assertEquals(6, route.length());
        assertEquals(List.of("a", "d", "c"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest5NodeWeightedTree(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "d", "e"), route.getNodeNames());
        assertEquals(4, route.length());
        assertEquals(List.of("a", "d"), statistics.getNodesNamesVisited());
    }
}
