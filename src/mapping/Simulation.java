package mapping;

import model.Network;
import model.Node;
import ui.Box;
import ui.ViewPort;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private ViewPort viewPort;

    public Simulation(Network network) {
        viewPort = new ViewPort(constructModel(network));
    }

    private List<Box> constructModel(Network network) {
        List<Box> boxes = new ArrayList<Box>();
        for (Node node : network.allSourceNodes()) {
            if (node.hasNeighbor()) {
                boxes.add(new Box().withNeighbor(new Box()));
            } else {
                boxes.add(new Box());
            }
        }
        return boxes;
    }

    public void paint() {
        viewPort.paint();
    }
}
