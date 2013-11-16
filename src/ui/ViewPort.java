package ui;


import ui.geometry.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ViewPort extends JPanel {

    private List<Box> components;
    private List<Line> redrawn;
    private final JFrame frame;
    private List<MovingBox> smallBoxes;

    public ViewPort(List<Box> plottedComponents, List<Line> redrawn) {
        smallBoxes = new ArrayList<MovingBox>();
        this.redrawn = redrawn;
        components = plottedComponents;
        frame = buildFrame();
    }

    private JFrame buildFrame() {
        JFrame frame = new JFrame("Network simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(this);
        return frame;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, getWidth(), getHeight());
        for (Box box : components) {
            box.paint(graphics);
        }
        for (Line line : redrawn) {
            line.paint(graphics);
        }
        for (Box smallBox : smallBoxes) {
            smallBox.paint(graphics);
        }
    }

    public void paint() {
        frame.setVisible(true);
    }

    public synchronized void drawBox(Line line, Point source, Point destination) {
        MovingBox movingBox = line.getMovingBox(source.compareTo(destination));
        smallBoxes.add(movingBox);
    }


    public synchronized void increment() {
        for (int i = 0; i < smallBoxes.size(); ) {
            MovingBox smallBox = smallBoxes.get(i);
            if (smallBox.canMove()) {
                smallBox.increment();
                i++;
            } else {
                smallBoxes.remove(i);
            }
        }
    }
}
