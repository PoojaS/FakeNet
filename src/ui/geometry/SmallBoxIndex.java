package ui.geometry;


import model.Link;

public class SmallBoxIndex {

    private int length;
    private int scale;
    private int current = 1;
    private Integer directionOfTransfer;

    public SmallBoxIndex(int length, int scale, int size, Integer directionOfTransfer) {
        this.directionOfTransfer = directionOfTransfer;
        this.length = length - (size * 2); // Take away size pixels on each side
        this.scale = scale;
    }

    public int value() {
        if (Link.FORWARD.equals(directionOfTransfer)) {
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
