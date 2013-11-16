package model;


import java.util.ArrayList;
import java.util.List;

/**
 * A graph of nodes and their links
 */
public class Network {

    private List<Node> nodes;

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> allNodes() {
        return new ArrayList<Node>(nodes);
    }

    /**
     * Forces every node to move a unit of data in a separate thread so that, individual threads could be forced to sleep
     * to honor wire delays.
     */
    public void moveUnitOfData() {
        for (final Node node : nodes) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    node.moveUnitOfData();
                }
            }).start();
        }
    }
}
