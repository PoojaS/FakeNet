package model;

import protocol.Packet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Extend a node to implement routing
 */
public class Router extends Node {

    /**
     * Constant time delay in milli seconds taken by the router to process packets.
     */
    public static final int ROUTER_DELAY = 2000;
    private Queue<Packet> packets;
    private Neighbors routes;

    public Router(String id) {
        super(id);
        routes = new Neighbors();
        packets = new LinkedList<Packet>();
    }

    /**
     * Respects router delay in processing packets
     */
    @Override
    public void moveUnitOfData() {
        if (!packets.isEmpty()) {
            Packet packet = packets.remove();
            try {
                Thread.sleep(ROUTER_DELAY + packet.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The size of " + getId() + " is " + packets.size());
            move(packet, routes.neighbor(packet.getDestination()));
        }
    }

    /**
     * Every packet received will be processed to route it to the correct destination. Assumes that no packet will be
     * destined for the router itself
     *
     * @param packet
     * @param wire
     */
    @Override
    public void receive(Packet packet, Link wire) {
        if (!getId().equals(packet.getDestination())) {
            packets.add(packet);
        }
    }

    public void addRoute(Link link) {
        routes.add(link);
    }
}
