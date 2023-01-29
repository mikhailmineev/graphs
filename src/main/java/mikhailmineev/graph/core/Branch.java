package mikhailmineev.graph.core;

public record Branch(Node from, Node to, int length) {

    public Branch(Node from, Node to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
        from.getBranches().add(this);
    }
}
