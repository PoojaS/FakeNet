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

    public List<Plottable> plotAll(List<? extends Plottable> sourceBoxes) {
        List<Plottable> allPlottedBoxes = new ArrayList<Plottable>();

        int initialX = 10;
        int initialY = 10;

        int offset = 0;

        for (Plottable sourceBox : sourceBoxes) {
            sourceBox.setVertex(new Point(initialX + offset, initialY));
            sourceBox.getNeighbor().setVertex(new Point((initialX + offset) + 100, initialY));
            allPlottedBoxes.add(sourceBox);
            allPlottedBoxes.add(sourceBox.getNeighbor());
            offset += 300;
        }

        return allPlottedBoxes;
    }
}
