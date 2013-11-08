package ui.geometry;


public class SmallBoxIndex {

    private int length;
    private int scale;
    private int current = 1;

    public SmallBoxIndex(int length, int scale, int size) {
        this.length = length - (size * 2); // Take away size pixels on each side
        this.scale = scale;
    }

    public int value() {
        return (int) ((double) (length / scale) * current);
    }

    public void increment() {
        current++;
    }

    public boolean isMax() {
        return (int) ((double) (length / scale) * current) >= length;
    }
}
