package ui;


import ui.geometry.Point;

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
