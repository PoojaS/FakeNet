package model;

/**
 * Models the event of initiation of the transfer of a unit of data from the node
 */
public class InitiationOfTransfer {

    /**
     * The node transferring data
     */
    private Node source;
    /**
     * The link that source node uses to tansfer data
     */
    private Link destination;

    public InitiationOfTransfer(Node source, Link destination) {
        this.source = source;
        this.destination = destination;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination.getDestination();
    }

    public Link getLink() {
        return destination;
    }
}
