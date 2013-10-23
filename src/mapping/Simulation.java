package mapping;

import model.Node;
import ui.Box;
import ui.ViewPort;

public class Simulation {

    private ViewPort viewPort;

    public Simulation() {
        viewPort = new ViewPort();
    }

    public void add(Node node) {
        viewPort.add(new Box());
    }

    public void paint() {
        viewPort.paint();
    }
}
