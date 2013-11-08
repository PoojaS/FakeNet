package ui.geometry;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private List<LineSegment> segments;

    public Line(List<Point> checkPoints) {
        segments = construct(checkPoints);
    }

    private List<LineSegment> construct(List<Point> checkPoint) {
        List<LineSegment> result = new ArrayList<LineSegment>();
        int i = 0;
        while (i < checkPoint.size() - 1) {
            Point point = checkPoint.get(i);
            result.add(new LineSegment(point, checkPoint.get(i + 1)));
            i++;
        }
        return result;
    }

    public Point pointAt(int length) {
        for (LineSegment segment : segments) {
            if (length <= segment.scalarDistance()) {
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
