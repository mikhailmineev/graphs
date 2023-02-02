package mikhailmineev.graph.solution;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.store.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractRouteTest {

    private final Function<Node, Route> routeSupplier;

    private Map<String, Node> graph;

    private Route route;

    public AbstractRouteTest(Function<Node, Route> routeSupplier) {
        this.routeSupplier = routeSupplier;
    }

    @BeforeEach
    public void setup() {
        graph = new TwoNodeTree().returnGraph();
        route = routeSupplier.apply(graph.get("a"));
    }

    @Test
    public void testRoute() {
        var route = this.route;
        route = route.addNode(graph.get("b"), graph.get("a").getBranches().get(0));

        assertEquals(List.of("a", "b"), route.getNodeNames());
        assertEquals(2, route.depth());
        assertEquals(3, route.length());
    }
}
