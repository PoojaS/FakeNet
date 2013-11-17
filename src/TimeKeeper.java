import mapping.Simulation;

/**
 * Sends clock tick events to the simulation
 * <p/>
 * This class helps to control the flow of time. All data movements take place only at a tick
 */
public class TimeKeeper {

    private Simulation simulation;

    public TimeKeeper(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Sends a tick to the simulation every second
     */
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        simulation.tick();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
