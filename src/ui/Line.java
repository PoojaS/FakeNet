package ui;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Line implements Component {

    private Box source;
    private Box destination;
    private List<Point> checkpoints;

    public Line(Box sourceBox, Box destination) {
        source = sourceBox;
        this.destination = destination;
        this.checkpoints = prettyPlot(source.midPointOnRightHandSide(), destination.midPointOnLeftHandSide());
    }

    private List<Point> prettyPlot(Point source, Point destination) {
        List<Point> points = new ArrayList<Point>();
        points.addAll(asList(source, source.midwayOnTheSameLineAsThisPoint(destination), source.midwayOnTheSameLineAsOtherPoint(destination), destination));
        return points;
    }

    @Override
    public void paint(Graphics graphics) {
        for (int i = 0; i < checkpoints.size() - 1; i++) {
            Point from = checkpoints.get(i);
            Point to = checkpoints.get(i + 1);
            graphics.drawLine(from.getXpos(), from.getYpos(), to.getXpos(), to.getYpos());
        }
    }

}
