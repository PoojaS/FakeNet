package model;


import java.util.ArrayList;
import java.util.List;

public class Network {

    private List<Node> nodes;

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> allNodes() {
        return new ArrayList<Node>(nodes);
    }
}
