package model;

public class InitiationOfTransfer {

    private Node source;
    private Link destination;

    public InitiationOfTransfer(Node source, Link destination) {
        this.source = source;
        this.destination = destination;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination.getDestination();
    }

    public Link getLink() {
        return destination;
    }
}
