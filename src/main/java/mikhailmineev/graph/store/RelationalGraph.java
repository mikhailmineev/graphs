package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.LazyImmutableNode;
import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelationalGraph implements GraphStore {

    private final Row[] data;

    public RelationalGraph(Row[] data) {
        this.data = data;
    }


    @Override
    public Map<String, Node> buildGraph() {
        Map<String, Pair<LazyImmutableNode, List<Branch>>> graph = new HashMap<>();
        for (Row row : data) {
            createBidirectionalLink(graph, row);
        }

        return graph
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, RelationalGraph::setupNode));
    }

    private static void createBidirectionalLink(Map<String, Pair<LazyImmutableNode, List<Branch>>> graph, Row row) {
        int length = row.length();
        var from = getNodeListPair(graph, row.from());
        var to = getNodeListPair(graph, row.to());
        var fromNode = from.left();
        var toNode = to.left();
        var fromList = from.right();
        var toList = to.right();
        fromList.add(new Branch(fromNode, toNode, length));
        toList.add(new Branch(toNode, fromNode, length));
    }

    private static Pair<LazyImmutableNode, List<Branch>> getNodeListPair(Map<String, Pair<LazyImmutableNode, List<Branch>>> graph, String row) {
        return graph.computeIfAbsent(row, e -> new Pair<>(new LazyImmutableNode(e), new ArrayList<>()));
    }

    private static Node setupNode(Map.Entry<String, Pair<LazyImmutableNode, List<Branch>>> e) {
        var node = e.getValue().left();
        var branches = e.getValue().right();
        node.setBranches(branches);
        return node;
    }
}
