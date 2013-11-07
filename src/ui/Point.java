package ui;

public class Point {

    private int xpos;
    private int ypos;

    public Point(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public Point midwayOnTheSameLineAsThisPoint(Point otherPoint) {
        return new Point(xpos + ((otherPoint.getXpos() - xpos) / 2), ypos);
    }

    public Point midwayOnTheSameLineAsOtherPoint(Point otherPoint) {
        return new Point(xpos + ((otherPoint.getXpos() - xpos) / 2), otherPoint.getYpos());
    }
}
