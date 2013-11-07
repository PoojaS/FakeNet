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
    private NetworkDefinition definition;

    public Simulation(NetworkDefinition definition) {
        this.definition = definition;
        viewPort = new ViewPort(allNodes(), allLinks());
    }

    private List<Component> allNodes() {
        List<Component> components = new ArrayList<Component>();
        for (Node node : definition.network().allNodes()) {
            Box sourceBox = new Box(definition.positionOf(node));
            components.add(sourceBox);
        }
        return components;
    }

    private List<Component> allLinks() {
        List<Component> components = new ArrayList<Component>();
        for (Node node : definition.network().allNodes()) {
            Box sourceBox = new Box(definition.positionOf(node));
            for (Link link : node.allNeighbors()) {
                components.add(new Line(sourceBox, new Box(definition.positionOf(link.getDestination())), link));
            }
        }
        return components;
    }

    public void paint() {
        viewPort.paint();
    }

    public void tick() {
        definition.network().moveUnitOfData();
        viewPort.redraw();
    }
}
