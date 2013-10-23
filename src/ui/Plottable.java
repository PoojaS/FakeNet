package ui;


import javax.swing.*;

public abstract class Plottable implements Component {

    protected Point point;

    public void setVertex(Point point) {
        this.point = point;
    }
}
