package ui;


import model.Node;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ViewPort extends JPanel {

    private Plot plot;
    private List<Plottable> plottables;
    private List<Component> components;

    public ViewPort(List<Box> boxes) {
        plot = new Plot();
        plottables = new ArrayList<Plottable>();
        components = new ArrayList<Component>();
        buildViewPort(boxes);
    }

    private void buildViewPort(List<Box> boxes) {
        for (Box box : boxes) {
            add(box);
        }
    }

    public void add(Plottable plottable) {
        Point vertex = plot.getNextVertex();
        plottable.setVertex(vertex);
        plottables.add(plottable);
    }

    public void add(Component component) {
        components.add(component);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        for (Plottable plottable : this.plottables) {
            plottable.paint(graphics);
        }
    }

    public void paint() {
        JFrame frame = new JFrame("Network simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(this);
        frame.setVisible(true);
    }
}
