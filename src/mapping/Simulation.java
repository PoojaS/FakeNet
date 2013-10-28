package mapping;

import model.Network;
import model.Node;
import ui.Box;
import ui.ViewPort;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Node> sourceNodes;
    private ViewPort viewPort;

    public Simulation(Network network) {
        sourceNodes = network.allSourceNodes();
        viewPort = new ViewPort(constructModel(network));
    }

    private List<Box> constructModel(Network network) {
        List<Box> boxes = new ArrayList<Box>();
        for (Node node : network.allNodes()) {
            boxes.add(new Box());
        }
        return boxes;
    }

    public void paint() {
        viewPort.paint();
    }
}
