package ui;


import java.util.Random;

public class Plot {

    private Random random;

    public Plot() {
        random = new Random();
    }

    public Point getNextVertex() {
        return new Point(Math.abs(random.nextInt()) % 200, Math.abs(random.nextInt()) % 200);
    }
}
