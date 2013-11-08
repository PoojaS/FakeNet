package ui.geometry;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private List<LineSegment> segments;
    private LineSegment segmentUnderConstruction;

    public Line(List<Point> checkPoints) {
        segments = new ArrayList<LineSegment>();
        segmentUnderConstruction = new LineSegment();
        for (Point checkPoint : checkPoints) {
            addPoint(checkPoint);
        }
    }

    private void addPoint(Point point) {
        segmentUnderConstruction.add(point);
        if (segmentUnderConstruction.isComplete()) {
            reinitialize();
        }
    }

    private void reinitialize() {
        segments.add(segmentUnderConstruction);
        segmentUnderConstruction = new LineSegment();
    }

    public Point pointAt(int length) {
        for (LineSegment segment : segments) {
            if (segment.scalarDistance() <= length) {
                return segment.pointAt(length);
            } else {
                length -= segment.scalarDistance();
            }
        }
        return null;
    }

    public int length() {
        int distance = 0;
        for (LineSegment segment : segments) {
            distance += segment.scalarDistance();
        }
        return distance;
    }
}
