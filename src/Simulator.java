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
                .plot(new Router("X"), 300, 100)
                .plot(new Node("B"), 500, 100)
                .plot(new Node("L"), 100, 300)
                .plot(new Router("Y"), 300, 300)
                .plot(new Node("M"), 500, 300)
                .generateUnequalSizePackets("L")
                .generateUnequalSizePackets("M")
                .defaultGateWay("A", "X", CONSTANT_DELAY)
                .defaultGateWay("B", "X", CONSTANT_DELAY)
                .defaultGateWay("L", "Y", CONSTANT_DELAY)
                .defaultGateWay("M", "Y", CONSTANT_DELAY)
                .moveData("A", "B")
                .moveData("B", "A")
                .moveData("L", "M")
                .moveData("M", "L");
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
