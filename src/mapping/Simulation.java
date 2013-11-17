package mapping;

import model.InitiationOfTransfer;
import model.Link;
import model.Node;
import model.Router;
import ui.Box;
import ui.Line;
import ui.ViewPort;

import java.util.*;

/**
 * Represents the mapping of the model to the view
 */
public class Simulation implements Observer {

    /**
     * Rate at which the canvas will be redrawn in milli seconds
     */
    public static final int REFRESH_RATE = 50;
    /**
     * The view component of the network which will be constructed and updated through this class according to the model.
     */
    private ViewPort viewPort;
    /**
     * Maps a link from the model to a line on the canvas
     */
    private Map<Link, Line> linkToLine;
    /**
     * Represents the definition of the network created by the user
     */
    private NetworkDefinition definition;

    private int numberOfTicks = 0;

    /**
     * Constructs the view from the model and  starts the movement of boxes that represent the movement of data packets
     *
     * @param definition The network definition
     */
    public Simulation(NetworkDefinition definition) {
        this.definition = definition;
        linkToLine = new HashMap<Link, Line>();
        viewPort = new ViewPort(allNodes(), allLinks());
        moveBox();
    }

    /**
     * Creates a box view component that represents every node in the network
     *
     * @return A box view component for every node in the network
     */
    private List<Box> allNodes() {
        List<Box> components = new ArrayList<Box>();
        for (Node node : definition.network().allNodes()) {
            Box sourceBox = new Box(definition.positionOf(node));
            node.addObserver(this);
            components.add(sourceBox);
        }
        return components;
    }

    /**
     * Creates a line view component that represents every link in the network
     *
     * @return A line view component for every link in the network
     */
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

    /**
     * Paints the canvas
     */
    public void paint() {
        viewPort.paint();
    }

    /**
     * Receive a clock tick from time keeper and transmit it to the network
     */
    public void tick() {
        if (numberOfTicks == 50) {
            System.out.println("The avg service time is of router X is " + ((Router) definition.getbyId("X")).getTotalNumberOfPacketsHandled() / ((Router) definition.getbyId("X")).getTotalServiceTime());
            System.out.println("The avg service time is of router Y is " + ((Router) definition.getbyId("Y")).getTotalNumberOfPacketsHandled() / ((Router) definition.getbyId("Y")).getTotalServiceTime());
            System.out.println("The arrival rate at X is" + ((Router) definition.getbyId("X")).getTotalNumberOfPacketsHandled() / 50);
            System.out.println("The arrival rate at Y is" + ((Router) definition.getbyId("Y")).getTotalNumberOfPacketsHandled() / 50);
        }
        definition.network().moveUnitOfData();
        numberOfTicks++;
    }

    /**
     * Listens to changes in model that represent initiation of data transfer and updates the view
     *
     * @param o   The model component that changed
     * @param arg The Change in value
     */
    @Override
    public void update(Observable o, Object arg) {
        InitiationOfTransfer transfer = (InitiationOfTransfer) arg;
        Link link = transfer.getLink();
        viewPort.drawBox(linkToLine.get(link), definition.positionOf(transfer.getSource()), definition.positionOf(transfer.getDestination()));
    }

    /**
     * Move the boxes which represents packets by one unit and repaint the canvas. The actual number of pixels moved is
     * left to the discretion of the view
     */
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
