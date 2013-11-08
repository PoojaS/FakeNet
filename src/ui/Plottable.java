package ui;


import ui.geometry.Point;

public abstract class Plottable implements Component {

    protected Point point;

    protected Plottable(Point point) {
        this.point = point;
    }
}
