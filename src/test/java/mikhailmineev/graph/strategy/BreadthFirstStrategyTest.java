package mikhailmineev.graph.strategy;

import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstStrategyTest extends AbstractStrategyTest {

    public BreadthFirstStrategyTest() {
        super(() -> new BreadthFirstStrategy(Route::newRoute));
    }

    @Override
    protected void verify3NodeGraph(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(3, route.length());
        assertEquals(List.of("a", "b"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verify4NodeDiamondGraph(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "b", "d"), route.getNodeNames());
        assertEquals(6, route.length());
        assertEquals(List.of("a", "b", "c"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verify3NodeTree(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(3, route.length());
        assertEquals(List.of("a", "b"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verify5NodeTree(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "c", "e"), route.getNodeNames());
        assertEquals(6, route.length());
        assertEquals(List.of("a", "b", "c", "d"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verify5NodeWeightedGraph(Route route, StatisticsReader statistics) {
        assertEquals(List.of("a", "b", "e"), route.getNodeNames());
        assertEquals(4, route.length());
        assertEquals(List.of("a", "b", "c", "d"), statistics.getNodesNamesVisited());
    }
}
