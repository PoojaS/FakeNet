package model;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private List<Link> neighbors;
    private String id;

    public Node(String id) {
        this.id = id;
        neighbors = new ArrayList<Link>();
    }

    public void receive(byte[] bytes) {
        System.out.println("Got " + bytes.length + " vadais from node");
    }

    public String getId() {
        return id;
    }

    public void addLink(Link link) {
        neighbors.add(link);
    }

    public List<Link> allNeighbors() {
        return neighbors;
    }
}
