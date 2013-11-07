import mapping.Simulation;

public class TimeKeeper {

    private Simulation simulation;

    public TimeKeeper(Simulation simulation) {
        this.simulation = simulation;
    }

    public void start() {
        simulation.tick();
    }
}
