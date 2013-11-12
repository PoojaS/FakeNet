package model;

import java.util.ArrayList;
import java.util.List;

public class Neighbors {

    private List<Link> neighbors = new ArrayList<Link>();

    public void add(Link neighbor) {
        neighbors.add(neighbor);
    }

    public Link neighbor(String destination) {
        for (Link neighbor : neighbors) {
            if (neighbor.is(destination)) {
                return neighbor;
            }
        }
        return null;
    }

    public List<Link> all() {
        return new ArrayList<Link>(neighbors);
    }
}
