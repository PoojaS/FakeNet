package mapping;

import model.Node;
import ui.Box;
import ui.ViewPort;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private ViewPort viewPort;

    public Simulation(NetworkDefinition definition) {
        viewPort = new ViewPort(constructModel(definition));
    }

    private List<Box> constructModel(NetworkDefinition definition) {
        List<Box> boxes = new ArrayList<Box>();
        for (Node node : definition.network().allNodes()) {
            boxes.add(new Box(definition.positionOf(node)));
        }
        return boxes;
    }

    public void paint() {
        viewPort.paint();
    }
}
