package ui;


import ui.geometry.Point;

import java.awt.Graphics;

public class Box extends Plottable {

    protected int size = 50;

    public Box(Point point) {
        super(point);
    }

    public Box(int size) {
        this.size = size;
    }

    public Point midPointOnRightHandSide() {
        return new Point(point.getXpos() + size, point.getYpos() + (size / 2));
    }

    public Point midPointOnLeftHandSide() {
        return new Point(point.getXpos(), point.getYpos() + (size / 2));
    }

    public void paint(Graphics graphics) {
        graphics.drawRect(point.getXpos(), point.getYpos(), size, size);
    }
}
