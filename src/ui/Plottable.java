package ui;


public abstract class Plottable implements Component {

    protected Plottable spatialNeighbor;
    protected Point point;

    public void setVertex(Point point) {
        this.point = point;
    }

    public Plottable getNeighbor() {
        return spatialNeighbor;
    }

    public <T extends Plottable> T withNeighbor(T plottable) {
        this.spatialNeighbor = plottable;
        return (T) this;
    }
}
