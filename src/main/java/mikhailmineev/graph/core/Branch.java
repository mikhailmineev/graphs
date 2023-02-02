package mikhailmineev.graph.core;

public record Branch(Node from, Node to, int length) {

    @Override
    public String toString() {
        return "Branch from " + from.getName() + " to " + to.getName() + " with length " + length;
    }

}
