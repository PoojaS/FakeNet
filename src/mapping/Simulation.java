package mapping;

import model.Link;
import model.Node;
import ui.Box;
import ui.Component;
import ui.Line;
import ui.ViewPort;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private ViewPort viewPort;

    public Simulation(NetworkDefinition definition) {
        viewPort = new ViewPort(constructModel(definition));
    }

    private List<Component> constructModel(NetworkDefinition definition) {
        List<Component> components = new ArrayList<Component>();
        for (Node node : definition.network().allNodes()) {
            Box sourceBox = new Box(definition.positionOf(node));
            components.add(sourceBox);
            for (Link link : node.allNeighbors()) {
                components.add(new Line(sourceBox, new Box(definition.positionOf(link.getDestination()))));
            }
        }
        return components;
    }

    public void paint() {
        viewPort.paint();
    }
}
