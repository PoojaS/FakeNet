import mapping.NetworkDefinition;
import mapping.Simulation;
import model.Node;
import model.Router;

public class Simulator {

    public static final Integer CONSTANT_DELAY = 2;

    private Simulation simulation;
    private TimeKeeper timeKeeper;

    private NetworkDefinition network() {
        NetworkDefinition definition = new NetworkDefinition();
        definition
                .plot(new Node("A"), 100, 100)
                .plot(new Router("X"), 450, 500)
                .plot(new Node("B"), 1000, 100)
                .defaultGateWay("A", "X", CONSTANT_DELAY)
                .defaultGateWay("B", "X", CONSTANT_DELAY)
                .moveData("A", "B");
        return definition;
    }

    public Simulator() {
        simulation = new Simulation(network());
        timeKeeper = new TimeKeeper(simulation);
    }

    public void start() {
        simulation.paint();
        timeKeeper.start();
    }

    public static void main(String args[]) {
        new Simulator().start();
    }

}
