package ui;


public abstract class Plottable implements Component {

    protected Point point;

    protected Plottable(Point point) {
        this.point = point;
    }
}
