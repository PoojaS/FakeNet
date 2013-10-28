import mapping.Simulation;
import model.Link;
import model.Network;
import model.Node;
import model.Router;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Simulator {

    private Simulation simulation;

    private Node component(Node source, Node destination) {
        Link link = new Link(destination);
        source.addLink(link);
        return source;
    }

    private Network network() {
        Node constantRouter = new Router();
        Node variableRouter = new Router();
        List<Node> nodes = asList(
                component(new Node(), constantRouter),
                component(constantRouter, new Node()),
                component(new Node(), variableRouter),
                component(variableRouter, new Node())
        );
        return new Network(nodes);
    }

    public Simulator() {
        simulation = new Simulation(network());
    }

    public void start() {
        simulation.paint();
    }

    public static void main(String args[]) {
        new Simulator().start();
    }

}
