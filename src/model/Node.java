package model;

import java.util.List;

public class Node {

    private Neighbors neighbors;
    private String id;
    private Buffer buffer;

    public Node(String id) {
        this.id = id;
        buffer = new Buffer();
        neighbors = new Neighbors();
    }

    public void receive(byte[] bytes) {
        System.out.println("Received " + bytes.length + " bytes of data");
        buffer.append(bytes);
    }

    public String getId() {
        return id;
    }

    public void addLink(Link link) {
        neighbors.add(link);
    }

    public List<Link> allNeighbors() {
        return neighbors.all();
    }

    public void moveUnitOfData() {
        if (neighbors.hadNeighbor()) {
            neighbors.neighbor().send(buffer.read(neighbors.neighbor().getBandwidth()));
        }
    }
}
