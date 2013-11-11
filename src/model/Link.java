package model;

import static java.lang.Thread.sleep;

public class Link {

    public static final Integer FORWARD = 0;
    public static final Integer REVERSE = 1;

    private Integer bandwidth = 2;
    private Integer wireDelayInSeconds;
    private Node source;
    private Node destination;

    public Link(Integer wireDelayInSeconds, Node source, Node destination) {
        this.wireDelayInSeconds = wireDelayInSeconds;
        this.source = source;
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

    public int getWireDelay() {
        return wireDelayInSeconds;
    }
}
