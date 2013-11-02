package model;

public class Node {

    private Link neighbor;
    private Link link;
    private String id;

    public Node(String id) {
        this.id = id;
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
}
