package ui;


import java.awt.Graphics;

public class Box extends Plottable {

    private static int SIZE = 50;

    @Override
    public void paint(Graphics graphics) {
        graphics.drawRect(point.getXpos(), point.getYpos(), SIZE, SIZE);
    }
}
