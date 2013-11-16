package model;

import protocol.Packet;
import protocol.Packets;
import protocol.Sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Node extends Observable {

    private String id;
    private Sessions sessions;
    private Link defaultGateway;
    protected Neighbors neighbors;
    private boolean packetsOfEqualSize;

    public Node(String id) {
        this.id = id;
        sessions = new Sessions();
        neighbors = new Neighbors();
    }

    public String getId() {
        return id;
    }

    public void receive(Packet packet, Link wire) {
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
            Packets packets = sessions.read(getPacketSzie(actualDestination));
            packets.setSource(id);
            packets.computeChecksum();
            move(packets, actualDestination);
        }
    }

    private Integer getPacketSzie(Link actualDestination) {
        Integer dataSize = actualDestination.getBandwidth();
        if (!packetsOfEqualSize) {
            dataSize = new Random().nextInt(dataSize);
        }
        return dataSize;
    }

    protected void move(Packets packets, Link actualDestination) {
        for (Packet packet : packets.all()) {
            move(packet, actualDestination);
        }
    }

    protected void move(Packet packet, Link actualDestination) {
        actualDestination.send(packet, id);
        setChanged();
        notifyObservers(new InitiationOfTransfer(this, actualDestination));
    }

    public void setPacketsOfEqualSize(boolean packetsOfEqualSize) {
        this.packetsOfEqualSize = packetsOfEqualSize;
    }
}
