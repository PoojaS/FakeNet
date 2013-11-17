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
    public static final double ROUTER_DELAY = 0.005;
    private Queue<Packet> packets;
    private Neighbors routes;
    private double totalServiceTime;
    private double totalNumberOfPacketsHandled;

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
                double serviceTime = ROUTER_DELAY * Math.pow(2.132, (double) ((packet.size()) % 10));
                totalServiceTime += serviceTime;
                Thread.sleep((long) (serviceTime * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
        totalNumberOfPacketsHandled++;
        if (!getId().equals(packet.getDestination())) {
            packets.add(packet);
        }
    }

    public double getTotalServiceTime() {
        return totalServiceTime;
    }

    /**
     * Return the total number of packets handled
     *
     * @return
     */
    public double getTotalNumberOfPacketsHandled() {
        return totalNumberOfPacketsHandled;
    }

    public void addRoute(Link link) {
        routes.add(link);
    }
}
