package mapping;

import model.Link;
import model.Network;
import model.Node;
import model.Router;
import ui.geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides APIs that represents the language used to define a network
 */
public class NetworkDefinition {

    /**
     * Maps a string of node ids to nodes to enable convenient retrieval
     */
    private Map<String, Node> idToNode;
    /**
     * Tracks the position of every node on the canvas
     */
    private Map<Node, Point> plotOfNodes;

    public NetworkDefinition() {
        idToNode = new HashMap<String, Node>();
        plotOfNodes = new HashMap<Node, Point>();
    }

    /**
     * Captures the position on the canvas where a node is to be plotted
     *
     * @param node The node to be plotted
     * @param x    The x coordinate
     * @param y    The y coordinate
     * @return Self
     */
    public NetworkDefinition plot(Node node, int x, int y) {
        idToNode.put(node.getId(), node);
        plotOfNodes.put(node, new Point(x, y));
        return this;
    }

    /**
     * Marks a node that is already added to generate packets of unequal sizes. All nodes generate
     * packets of equal sizes by default
     *
     * @param node The id of the node
     * @return Self
     */
    public NetworkDefinition generateUnequalSizePackets(String node) {
        idToNode.get(node).setPacketsOfEqualSize(false);
        return this;
    }

    /**
     * Marks a router as the default gateway for the node
     *
     * @param node   The id of the node
     * @param router The id of the router that acts of the default gateway
     * @param delay  Wire delay in seconds
     * @return Self
     */
    public NetworkDefinition defaultGateWay(String node, String router, Integer delay) {
        idToNode.get(node).setGateway(new Link(delay, idToNode.get(node), idToNode.get(router)));
        Router routingNode = (Router) idToNode.get(router);
        routingNode.addRoute(new Link(delay, idToNode.get(router), idToNode.get(node)));
        return this;
    }

    /**
     * Creates a two way link between the nodes
     *
     * @param source      The id of the node that is the source
     * @param destination The id of the node that is the destination
     * @param delay       Wire delay in seconds
     * @return Self
     */
    public NetworkDefinition link(String source, String destination, int delay) {
        idToNode.get(source).addLink(new Link(delay, idToNode.get(source), idToNode.get(destination)));
        return this;
    }

    /**
     * Marks a directed data flow from the source to the destination
     *
     * @param source      The id of the source node for the data flow
     * @param destination The id of the destination node for the data flow
     * @return Self
     */
    public NetworkDefinition moveData(String source, String destination) {
        idToNode.get(source).addFlow(destination);
        return this;
    }

    /**
     * Returns a network built from the nodes and links that were added to self
     *
     * @return
     */
    public Network network() {
        return new Network(new ArrayList<Node>(plotOfNodes.keySet()));
    }

    /**
     * Returns the position of the node on the canvas if it were plotted already
     *
     * @param node The node to get retrieve the position for
     * @return The position of the node on the canvas
     */
    public Point positionOf(Node node) {
        return plotOfNodes.get(node);
    }

    public Node getbyId(String id) {
        return idToNode.get(id);
    }
}
