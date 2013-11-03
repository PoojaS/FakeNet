package model;

public class Link {

    private Integer bandwidth = 2;
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

    public Integer getBandwidth() {
        return bandwidth;
    }
}
