package model;

import java.util.ArrayList;
import java.util.List;

public class Neighbors {

    private List<Link> neighbors = new ArrayList<Link>();

    public void add(Link neighbor) {
        neighbors.add(neighbor);
    }

    public boolean hadNeighbor() {
        return !neighbors.isEmpty();
    }

    public Link neighbor() {
        return neighbors.get(0);
    }

    public List<Link> all() {
        return new ArrayList<Link>(neighbors);
    }
}
