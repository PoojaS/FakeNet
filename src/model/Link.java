package model;

import protocol.Packet;

import static java.lang.Thread.sleep;

/**
 * Models the link between to nodes
 */
public class Link {

    /**
     * The number of bytes the link can transfer per unit of time
     */
    private Integer bandwidth = 100;
    /**
     * The time taken by a packet to reach the destination from the source
     */
    private Integer wireDelayInSeconds;
    private Node source;
    private Node destination;
    private final String id;

    public Link(Integer wireDelayInSeconds, Node source, Node destination) {
        this.wireDelayInSeconds = wireDelayInSeconds;
        this.source = source;
        this.destination = destination;
        this.id = new LinkId(source.getId(), destination.getId()).value();
    }

    public boolean isNeighbor(String destination) {
        return (source.getId().equals(destination) || this.destination.getId().equals(destination));
    }

    /**
     * Respects wire-delay when transferring the packet. Transparently decides the direction of transfer based on the
     * initiator of transfer
     *
     * @param packet      Packet to transfer
     * @param currentNode Id of the initiator of transfer
     */
    public void send(Packet packet, String currentNode) {
        try {
            sleep(wireDelayInSeconds * 1000);
            if (source.getId().equals(currentNode)) {
                destination.receive(packet, this);
            } else {
                source.receive(packet, this);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Node getDestination() {
        return destination;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public int getWireDelay() {
        return wireDelayInSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!id.equals(link.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
