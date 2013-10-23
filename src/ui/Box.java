package ui;


import java.awt.*;

public class Box extends Plottable {

    private static int SIZE = 10;

    @Override
    public void paint(Graphics graphics) {
        graphics.draw3DRect(point.getXpos(), point.getYpos(), point.getXpos() + SIZE, point.getYpos() + 10, true);
    }

}
