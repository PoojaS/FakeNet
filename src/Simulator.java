import mapping.NetworkDefinition;
import mapping.Simulation;
import model.Node;
import model.Router;

public class Simulator {

    private Simulation simulation;

    private NetworkDefinition network() {
        NetworkDefinition definition = new NetworkDefinition();
        definition
                .plot(new Node("A"), 10, 10)
                .plot(new Router("X"), 20, 20)
                .plot(new Node("B"), 30, 30)
                .plot(new Router("Y"), 40, 40)
                .plot(new Node("C"), 50, 50)
                .link("A", "X")
                .link("X", "B")
                .link("X", "C")
                .link("C", "Y")
                .link("Y", "B");
        return definition;
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
