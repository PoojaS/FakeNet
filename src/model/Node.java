package model;

import java.util.UUID;

public class Node {

    private Link neighbor;
    private String id;
    private Link link;

    public Node() {
        id = UUID.randomUUID().toString();
    }

    public void receive(byte[] bytes) {
        System.out.println("Got " + bytes.length + " vadais from node");
    }

    public void transmit() {
        neighbor.send(new byte[]{});
    }

    public String getId() {
        return id;
    }

    public void addLink(Link link) {
        this.link = link;
    }

    public Node getNeighbor() {
        return link.getDestination();
    }
}
