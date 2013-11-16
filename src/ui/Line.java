package ui;


import model.Direction;
import ui.geometry.Point;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Models a line that is drawn on the canvas. Network simulator has not skewed lines. Such skewed lines if necessary,
 * will be broken down into horizontal and vertical line segments.
 */
public class Line {

    private Box source;
    private int scale;
    private List<Point> checkpoints;

    public Line(Box sourceBox, Box destination, int scale) {
        source = sourceBox;
        this.scale = scale;
        Box lesser = lesser(source, destination);
        Box greater = greater(source, destination);
        this.checkpoints = prettyPlot(lesser.facing(greater), greater.facing(lesser));
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < checkpoints.size() - 1; i++) {
            Point from = checkpoints.get(i);
            Point to = checkpoints.get(i + 1);
            graphics.drawLine(from.getXpos(), from.getYpos(), to.getXpos(), to.getYpos());
        }
    }

    public MovingBox getMovingBox(Direction directionOfTransfer) {
        ui.geometry.Line line = new ui.geometry.Line(checkpoints);
        return new MovingBox(line, scale, directionOfTransfer);
    }

    private Box lesser(Box source, Box destination) {
        return (source.getPoint().compareTo(destination.getPoint()) == Direction.FORWARD) ? source : destination;
    }

    private Box greater(Box source, Box destination) {
        return (source.getPoint().compareTo(destination.getPoint()) == Direction.FORWARD) ? destination : source;
    }

    private List<Point> prettyPlot(Point source, Point destination) {
        List<Point> points = new ArrayList<Point>();
        points.addAll(asList(source, source.midwayOnTheSameLineAsThisPoint(destination), source.midwayOnTheSameLineAsOtherPoint(destination), destination));
        return points;
    }
}
