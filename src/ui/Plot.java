package ui;


import java.util.ArrayList;
import java.util.List;

public class Plot {

    private int canvasWidth;
    private int canvasHeight;


    public Plot(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public List<Component> plotAll(List<? extends Plottable> sourceBoxes) {
        List<Component> allPlottedComponents = new ArrayList<Component>();
        int initialX = 10;
        int initialY = 10;
        int offset = 0;
        for (Plottable sourceBox : sourceBoxes) {
            sourceBox.setVertex(new Point(initialX + offset, initialY));
            sourceBox.getNeighbor().setVertex(new Point((initialX + offset) + 100, initialY));
            allPlottedComponents.add(sourceBox);
            allPlottedComponents.add(sourceBox.getNeighbor());
            offset += 300;
        }
        return allPlottedComponents;
    }
}
