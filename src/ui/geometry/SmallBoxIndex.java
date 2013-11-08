package ui.geometry;


public class SmallBoxIndex {

    private int length;
    private int delay;
    private int curPos;

    public SmallBoxIndex(int length, int delay, int curPos) {
        this.length = length;
        this.delay = delay;
        this.curPos = curPos;
    }

    public int value() {
        return length + ((length / delay) * curPos);
    }

}
