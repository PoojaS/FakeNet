package ui.geometry;


import model.Direction;

/**
 * Models the position of a box moving along the line. Provides the ability to address pixels though an integer
 * based index
 */
public class SmallBoxIndex {

    private int length;
    private int scale;
    private int current = 1;
    private Direction directionOfTransfer;

    public SmallBoxIndex(int length, int scale, int size, Direction directionOfTransfer) {
        this.directionOfTransfer = directionOfTransfer;
        this.length = length - (size * 2); // Take away size pixels on each side
        this.scale = scale;
    }

    public int value() {
        if (Direction.FORWARD.equals(directionOfTransfer)) {
            return (int) ((double) (length / scale) * current);
        } else {
            return length - (int) ((double) (length / scale) * current);
        }
    }

    public void increment() {
        current++;
    }

    public boolean isMax() {
        return (int) ((double) (length / scale) * current) >= length;
    }
}
