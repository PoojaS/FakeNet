package model;

public class Link {

    private Integer bandwidth = 2;
    private Integer wireDelayInSeconds = 2;
    private Node destination;

    public Link(Node destination) {
        this.destination = destination;
    }

    public void send(byte[] bytes) {
        try {
            Thread.sleep(wireDelayInSeconds * 1000);
            destination.receive(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Node getDestination() {
        return destination;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }
}
