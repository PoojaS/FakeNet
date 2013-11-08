package model;

import java.util.List;
import java.util.Observable;

public class Node extends Observable {

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
            byte[] read = buffer.read(neighbors.neighbor().getBandwidth());
            if (null != read && read.length > 0) {
                neighbors.neighbor().send(read);
                setChanged();
                notifyObservers(null);
            }
        }
    }
}
