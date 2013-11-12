package model;

import protocol.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Node extends Observable {

    private String id;
    private Buffer buffer;
    private List<String> destinations;
    private Neighbors neighbors;
    private Link defaultGateway;

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

    public void setGateway(Link defaultGateway) {
        this.defaultGateway = defaultGateway;
        this.addLink(defaultGateway);
    }

    public void moveUnitOfData() {
        for (String destination : destinations) {
            modeDataToDestination(destination);
        }
    }

    protected void modeDataToDestination(String destination) {
        Link neighbor = neighbors.neighbor(destination);
        Link actualDestination = (null == neighbor) ? defaultGateway : neighbor;
        Packet packet = buffer.read(actualDestination.getBandwidth());
        packet.setDestination(destination);
        if (packet.size() > 0) {
            actualDestination.send(packet);
            setChanged();
            notifyObservers(new InitiationOfTransfer(Link.FORWARD, actualDestination));
        }
    }

    public void addFlow(String destination) {
        destinations.add(destination);
    }
}
