package ui;

import ui.geometry.Point;
import ui.geometry.SmallBoxIndex;

import java.awt.Graphics;

public class MovingBox extends Box {

    private SmallBoxIndex smallBoxIndex;
    private ui.geometry.Line line;

    public MovingBox(ui.geometry.Line line, int scale, Integer directionOfTransfer) {
        super(10);
        this.line = line;
        smallBoxIndex = new SmallBoxIndex(line.length(), scale, 10, directionOfTransfer);
        point = getPosition();
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawRect(point.getXpos() - (size / 2), point.getYpos() - (size / 2), size, size);
    }

    private Point getPosition() {
        return line.pointAt(smallBoxIndex.value());
    }

    public void increment() {
        smallBoxIndex.increment();
        point = getPosition();
    }

    public boolean canMove() {
        return !smallBoxIndex.isMax();
    }
}
