package ui;


import ui.geometry.Point;

import java.awt.Graphics;

public class Box extends Plottable {

    private static int SIZE = 50;

    public Box(Point point) {
        super(point);
    }

    public Point midPointOnRightHandSide() {
        return new Point(point.getXpos() + SIZE, point.getYpos() + (SIZE / 2));
    }

    public Point midPointOnLeftHandSide() {
        return new Point(point.getXpos(), point.getYpos() + (SIZE / 2));
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawRect(point.getXpos(), point.getYpos(), SIZE, SIZE);
    }
}
