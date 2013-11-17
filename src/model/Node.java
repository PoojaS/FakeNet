package model;

import protocol.Packet;
import protocol.Packets;
import protocol.Sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Models a node in the network
 */
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

    /**
     * Receive the packet of data that was transmitted. Currently does nothing, but could be extended to pass on the data
     * to buffers that will be processed higher up the protocol stack
     *
     * @param packet
     * @param wire
     */
    public void receive(Packet packet, Link wire) {
    }

    /**
     * Used to command the node to start transferring data to the destination
     *
     * @param destination
     */
    public void addFlow(String destination) {
        sessions.create(destination);
    }

    /**
     * Used to add a neighbor to this node
     *
     * @param link
     */
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

    /**
     * Move a unit of data to all the nodes that are marked out as destinations of flows from this node. If immediate
     * route to the destination is found, use the default gateway to transmit the packet.
     */
    public void moveUnitOfData() {
        for (String destination : sessions.allBuddies()) {
            Link neighbor = neighbors.neighbor(destination);
            Link actualDestination = actualDestination(neighbor);
            Packets packets = sessions.read(getPacketSize(actualDestination));
            packets.setSource(id);
            packets.computeChecksum();
            move(packets, actualDestination);
        }
    }

    /**
     * Since the buffer to read data is circular, trying to read bandwidth amount of data results in packets of equal size.
     *
     * @param actualDestination
     * @return
     */
    private Integer getPacketSize(Link actualDestination) {
        Integer dataSize = actualDestination.getBandwidth();
        if (!packetsOfEqualSize) {
            dataSize = new Random().nextInt(dataSize);
            System.out.println(dataSize);
        }
        return dataSize;
    }

    protected void move(Packets packets, Link actualDestination) {
        for (Packet packet : packets.all()) {
            move(packet, actualDestination);
        }
    }

    protected Link actualDestination(Link neighbor) {
        return (null == neighbor) ? defaultGateway : neighbor;
    }

    protected void move(Packet packet, Link neighbour) {
        Link actualDestination = actualDestination(neighbour);
        actualDestination.send(packet, id);
        setChanged();
        notifyObservers(new InitiationOfTransfer(this, actualDestination));
    }

    public void setPacketsOfEqualSize(boolean packetsOfEqualSize) {
        this.packetsOfEqualSize = packetsOfEqualSize;
    }
}
