package model;

import static java.lang.Thread.sleep;

public class Link {

    private Integer bandwidth = 2;
    private Integer wireDelayInSeconds;
    private Node destination;

    public Link(Integer wireDelayInSeconds, Node destination) {
        this.wireDelayInSeconds = wireDelayInSeconds;
        this.destination = destination;
    }

    public void send(byte[] bytes) {
        try {
            sleep(wireDelayInSeconds * 1000);
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

    public Integer getDelay() {
        return wireDelayInSeconds;
    }
}
