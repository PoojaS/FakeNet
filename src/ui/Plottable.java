package ui;


import ui.geometry.Point;

/**
 * Models a component like a node that could be plotted onto the canvas
 */
public abstract class Plottable {

    protected Point point;

    protected Plottable() {
        point = null;
    }

    protected Plottable(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
}
