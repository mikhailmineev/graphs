package mikhailmineev.graph.strategy;

import mikhailmineev.graph.store.BoringGraphStore;
import mikhailmineev.graph.store.BoringGraphStore2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BruteForceStrategyTest {

    private Strategy strategy;

    @BeforeEach
    public void setup() {
        strategy = new BruteforceStrategy();
    }

    @Test
    public void test2NodePath() {
        var store = new BoringGraphStore();
        var graph = store.returnGraph();

        var route = strategy.findRoute("a", "c", graph);

        assertEquals(Arrays.asList("a", "c"), route.getNodeNames());
    }

    @Test
    public void test4NodePath() {
        var store = new BoringGraphStore2();
        var graph = store.returnGraph();

        var route = strategy.findRoute("a", "c", graph);

        assertEquals(Arrays.asList("a", "d", "c"), route.getNodeNames());
    }
}
