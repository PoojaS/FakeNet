package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the set of neighbors of the node. This set is addressable by the id of the destination.
 */
public class Neighbors {

    private List<Link> neighbors = new ArrayList<Link>();

    public void add(Link neighbor) {
        neighbors.add(neighbor);
    }

    public Link neighbor(String destination) {
        for (Link neighbor : neighbors) {
            if (neighbor.isNeighbor(destination)) {
                return neighbor;
            }
        }
        return null;
    }

    public List<Link> all() {
        return new ArrayList<Link>(neighbors);
    }
}
