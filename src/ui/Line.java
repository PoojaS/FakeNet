package ui;


import java.awt.*;

public class Line implements Component {

    private Box source;
    private Box destination;

    public Line(Box sourceBox, Box destination) {
        source = sourceBox;
        this.destination = destination;
    }

    @Override
    public void paint(Graphics graphics) {
        Point from = source.getPosition();
        Point to = destination.getPosition();
        graphics.drawLine(from.getXpos(), from.getYpos(), to.getXpos(), to.getYpos());
    }

}
