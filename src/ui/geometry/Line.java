package ui.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a geometric line
 */
public class Line {

    /**
     * Set of line segments which form this line
     */
    private List<LineSegment> segments;

    /**
     * @param checkPoints The points through which the line should pass through
     */
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

    /**
     * Returns the point at canvas at given length from the starting point of the line
     *
     * @param length
     * @return
     */
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

    /**
     * Total length of the line the sum of all line segments
     *
     * @return
     */
    public int length() {
        int distance = 0;
        for (LineSegment segment : segments) {
            distance += segment.scalarDistance();
        }
        return distance;
    }
}
