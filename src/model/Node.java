package model;

import protocol.Packet;
import protocol.Sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Node extends Observable {

    private String id;
    private Buffer buffer;
    private Sessions sessions;
    private Link defaultGateway;
    protected Neighbors neighbors;

    public Node(String id) {
        this.id = id;
        buffer = new Buffer();
        sessions = new Sessions();
        neighbors = new Neighbors();
    }

    public String getId() {
        return id;
    }

    public void receive(Packet packet, Link wire) {
        sessions.accept(packet.getSource()).receive(packet);
    }

    public void addFlow(String destination) {
        sessions.create(destination);
    }

    public void addLink(Link link) {
        neighbors.add(link);
    }

    public List<Link> allNeighbors() {
        List<Link> result = new ArrayList<Link>(neighbors.all());
        if (null != defaultGateway) {
            result.add(defaultGateway);
        }
        return result;
    }

    public void setGateway(Link defaultGateway) {
        this.defaultGateway = defaultGateway;
    }

    public void moveUnitOfData() {
        for (String destination : sessions.allBuddies()) {
            Link neighbor = neighbors.neighbor(destination);
            Link actualDestination = (null == neighbor) ? defaultGateway : neighbor;
            Packet packet = buffer.read(actualDestination.getBandwidth());
            packet.setDestination(destination);
            packet.setSource(id);
            move(packet, actualDestination);
        }
    }

    protected void move(Packet packet, Link actualDestination) {
        if (packet.size() > 0) {
            actualDestination.send(packet, id);
            setChanged();
            notifyObservers(new InitiationOfTransfer(this, actualDestination));
        }
    }
}
