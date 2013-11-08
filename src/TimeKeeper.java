import mapping.Simulation;

public class TimeKeeper {

    private Simulation simulation;

    public TimeKeeper(Simulation simulation) {
        this.simulation = simulation;
    }

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
