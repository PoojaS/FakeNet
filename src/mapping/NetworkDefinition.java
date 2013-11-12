package mapping;

import model.Link;
import model.Network;
import model.Node;
import model.Router;
import ui.geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkDefinition {

    private Map<String, Node> idToNode;
    private Map<Node, Point> plotOfNodes;

    public NetworkDefinition() {
        idToNode = new HashMap<String, Node>();
        plotOfNodes = new HashMap<Node, Point>();
    }

    public NetworkDefinition plot(Node node, int x, int y) {
        idToNode.put(node.getId(), node);
        plotOfNodes.put(node, new Point(x, y));
        return this;
    }

    public NetworkDefinition defaultGateWay(String node, String router, Integer delay) {
        idToNode.get(node).setGateway(new Link(delay, idToNode.get(node), idToNode.get(router)));
        Router routingNode = (Router) idToNode.get(router);
        routingNode.addRoute(new Link(delay, idToNode.get(router), idToNode.get(node)));
        return this;
    }

    public NetworkDefinition link(String source, String destination, int delay) {
        idToNode.get(source).addLink(new Link(delay, idToNode.get(source), idToNode.get(destination)));
        return this;
    }

    public NetworkDefinition moveData(String source, String destination) {
        idToNode.get(source).addFlow(destination);
        return this;
    }

    public Network network() {
        return new Network(new ArrayList<Node>(plotOfNodes.keySet()));
    }

    public Point positionOf(Node node) {
        return plotOfNodes.get(node);
    }
}
