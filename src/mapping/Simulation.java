package mapping;

import model.InitiationOfTransfer;
import model.Link;
import model.Node;
import ui.Box;
import ui.Line;
import ui.ViewPort;

import java.util.*;

public class Simulation implements Observer {

    public static final int REFRESH_RATE = 50;
    private ViewPort viewPort;
    private Map<Link, Line> linkToLine;
    private NetworkDefinition definition;

    public Simulation(NetworkDefinition definition) {
        this.definition = definition;
        linkToLine = new HashMap<Link, Line>();
        viewPort = new ViewPort(allNodes(), allLinks());
        moveBox();
    }

    private List<Box> allNodes() {
        List<Box> components = new ArrayList<Box>();
        for (Node node : definition.network().allNodes()) {
            Box sourceBox = new Box(definition.positionOf(node));
            node.addObserver(this);
            components.add(sourceBox);
        }
        return components;
    }

    private List<Line> allLinks() {
        List<Line> components = new ArrayList<Line>();
        for (Node node : definition.network().allNodes()) {
            Box sourceBox = new Box(definition.positionOf(node));
            for (Link link : node.allNeighbors()) {
                Line line = new Line(sourceBox, new Box(definition.positionOf(link.getDestination())), (link.getWireDelay() * 1000) / REFRESH_RATE);
                linkToLine.put(link, line);
                components.add(line);
            }
        }
        return components;
    }

    public void paint() {
        viewPort.paint();
    }

    public void tick() {
        definition.network().moveUnitOfData();
    }

    @Override
    public void update(Observable o, Object arg) {
        InitiationOfTransfer transfer = (InitiationOfTransfer) arg;
        Link link = transfer.getLink();
        viewPort.drawBox(linkToLine.get(link), definition.positionOf(transfer.getSource()), definition.positionOf(transfer.getDestination()));
    }

    private void moveBox() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(REFRESH_RATE);
                        viewPort.increment();
                        viewPort.invalidate();
                        viewPort.repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
