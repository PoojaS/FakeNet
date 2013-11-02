package ui;


import java.awt.*;

public class Box extends Plottable {

    private static int SIZE = 50;

    public Box(Point point) {
        super(point);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawRect(point.getXpos(), point.getYpos(), SIZE, SIZE);
    }
}
