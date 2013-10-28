package model;

public class Link {

    private Integer bandwidth;
    private Node destination;

    public Link(Node destination) {
        this.destination = destination;
    }

    public void send(byte[] bytes) {
        destination.receive(bytes);
    }

    public Node getDestination() {
        return destination;
    }
}
