import mapping.Simulation;
import model.Node;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Simulator {

    private List<Node> nodes;
    private Simulation simulation;

    public Simulator() {
        nodes = asList(new Node(), new Node(), new Node());
        simulation = new Simulation();
        for (Node node : nodes) {
            simulation.add(node);
        }
    }

    public void start() {
        simulation.paint();
    }

    public static void main(String args[]) {
        new Simulator().start();
    }
}
