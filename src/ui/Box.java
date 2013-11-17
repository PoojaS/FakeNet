package ui;


import ui.geometry.Point;

import java.awt.Graphics;

/**
 * Models any box that is stationary on the canvas
 */
public class Box extends Plottable {

    protected int size = 50;

    public Box(Point point) {
        super(point);
    }

    public Box(int size) {
        this.size = size;
    }

    /**
     * Return point on this box facing the given box
     *
     * @param anotherBox
     * @return
     */
    public Point facing(Box anotherBox) {
        int xGradient = this.getPoint().getXpos() - anotherBox.getPoint().getXpos();
        int yGradient = this.getPoint().getYpos() - anotherBox.getPoint().getYpos();
        if (xGradient != 0) {
            return (xGradient < 0) ? midPointOnRightHandSide() : midPointOnLeftHandSide();
        } else {
            return (yGradient < 0) ? midPointDown() : midPointOnTop();
        }

    }

    private Point midPointOnTop() {
        return new Point(point.getXpos() + (size / 2), point.getYpos());
    }

    private Point midPointDown() {
        return new Point(point.getXpos() + (size / 2), point.getYpos() + size);
    }

    private Point midPointOnRightHandSide() {
        return new Point(point.getXpos() + size, point.getYpos() + (size / 2));
    }

    private Point midPointOnLeftHandSide() {
        return new Point(point.getXpos(), point.getYpos() + (size / 2));
    }

    public void paint(Graphics graphics) {
        graphics.drawRect(point.getXpos(), point.getYpos(), size, size);
    }
}
