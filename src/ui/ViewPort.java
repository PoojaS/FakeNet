package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.List;

public class ViewPort extends JPanel {

    private Plot plot;
    private List<Plottable> plottables;
    private final JFrame frame;

    public ViewPort(List<? extends Plottable> boxes) {
        frame = buildFrame();
        plot = new Plot(frame.getWidth(), frame.getHeight());
        plottables = plot.plotAll(boxes);
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
        for (Plottable plottable : this.plottables) {
            plottable.paint(graphics);
        }
    }

    public void paint() {

        frame.setVisible(true);
    }
}
