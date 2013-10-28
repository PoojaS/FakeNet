package model;


import java.util.ArrayList;
import java.util.List;

public class Network {

    private List<Node> nodes;

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> allSourceNodes() {
        List<Node> sourceNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            sourceNodes.add(node);
        }
        for (int i = 0; i < sourceNodes.size(); ) {
            boolean removed = sourceNodes.remove(sourceNodes.get(i).getNeighbor());
            if (!removed) {
                i++;
            }
        }
        return sourceNodes;
    }
}
