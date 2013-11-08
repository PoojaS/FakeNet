package ui.geometry;

public class LineSegment {

    private Point startingPoint;
    private Point endPoint;

    public LineSegment() {
        startingPoint = endPoint = null;
    }

    public int axesOfChange() {
        if (startingPoint.getXpos() != endPoint.getXpos()) {
            return 0;
        } else {
            return 1;
        }
    }

    public int scalarDistance() {
        if (0 == axesOfChange()) {
            return endPoint.getXpos() - startingPoint.getXpos();
        } else {
            return endPoint.getYpos() - startingPoint.getYpos();
        }
    }

    public boolean isComplete() {
        return null != endPoint;
    }

    public void add(Point point) {
        if (null == startingPoint) {
            startingPoint = point;
        } else {
            endPoint = point;
        }
    }

    public Point pointAt(int length) {
        if (0 == axesOfChange()) {
            return new Point(startingPoint.getXpos() + length, startingPoint.getYpos());
        } else {
            return new Point(startingPoint.getXpos(), startingPoint.getYpos() + length);
        }
    }
}
