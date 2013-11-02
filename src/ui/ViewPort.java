package ui;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewPort extends JPanel {

    private Plot plot;
    private List<Component> components;
    private final JFrame frame;

    public ViewPort(List<? extends Plottable> boxes) {
        frame = buildFrame();
        plot = new Plot();
        components = plot.plotAll(boxes);
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
        for (Component component : this.components) {
            component.paint(graphics);
        }
    }

    public void paint() {
        frame.setVisible(true);
    }
}
