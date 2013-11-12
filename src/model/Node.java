package model;

import protocol.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Node extends Observable {

    private Neighbors neighbors;
    private String id;
    private Buffer buffer;
    private List<String> destinations;

    public Node(String id) {
        this.id = id;
        buffer = new Buffer();
        destinations = new ArrayList<String>();
        neighbors = new Neighbors();
    }

    public void receive(Packet packet) {
        System.out.println("Received " + packet.size() + " bytes of data");
        buffer.append(packet);
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
        for (String destination : destinations) {
            Link neighbor = neighbors.neighbor(destination);
            Packet packet = buffer.read(neighbor.getBandwidth());
            packet.setDestination(destination);
            if (packet.size() > 0) {
                neighbor.send(packet);
                setChanged();
                notifyObservers(new InitiationOfTransfer(Link.FORWARD, neighbor));
            }
        }
    }

    public void addFlow(String destination) {
        destinations.add(destination);
    }
}
