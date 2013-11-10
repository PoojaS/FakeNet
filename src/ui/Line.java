package ui;


import ui.geometry.Point;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Line {

    private Box source;
    private int scale;
    private List<Point> checkpoints;

    public Line(Box sourceBox, Box destination, int scale) {
        source = sourceBox;
        this.scale = scale;
        this.checkpoints = prettyPlot(source.midPointOnRightHandSide(), destination.midPointOnLeftHandSide());
    }

    private List<Point> prettyPlot(Point source, Point destination) {
        List<Point> points = new ArrayList<Point>();
        points.addAll(asList(source, source.midwayOnTheSameLineAsThisPoint(destination), source.midwayOnTheSameLineAsOtherPoint(destination), destination));
        return points;
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < checkpoints.size() - 1; i++) {
            Point from = checkpoints.get(i);
            Point to = checkpoints.get(i + 1);
            graphics.drawLine(from.getXpos(), from.getYpos(), to.getXpos(), to.getYpos());
        }
    }

    public MovingBox getMovingBox(Integer directionOfTransfer) {
        ui.geometry.Line line = new ui.geometry.Line(checkpoints);
        return new MovingBox(line, scale, directionOfTransfer);
    }
}
