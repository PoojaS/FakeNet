package ui;


import ui.geometry.Point;

public abstract class Plottable {

    protected Point point;

    protected Plottable(Point point) {
        this.point = point;
    }
}
