package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ViewPort extends JPanel {

    private List<Component> components;
    private final JFrame frame;
    private List<Component> redrawn;
    private Graphics graphics;

    public ViewPort(List<Component> plottedComponents, List<Component> redrawn) {
        this.redrawn = redrawn;
        frame = buildFrame();
        components = plottedComponents;
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
        this.graphics = graphics;
        ArrayList<Component> allComponents = new ArrayList<Component>(components);
        allComponents.addAll(redrawn);
        for (Component component : allComponents) {
            component.paint(graphics);
        }
    }

    public void paint() {
        frame.setVisible(true);
    }

    public void redraw() {
        if (graphics != null) {
            for (Component component : redrawn) {
                component.paint(graphics);
            }
        }
    }
}
