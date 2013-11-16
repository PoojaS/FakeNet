package model;

import protocol.Packet;

import java.util.LinkedList;
import java.util.Queue;

public class Router extends Node {

    public static final int ROUTER_DELAY = 2000;
    private Queue<Packet> packets;
    private Neighbors routes;

    public Router(String id) {
        super(id);
        routes = new Neighbors();
        packets = new LinkedList<Packet>();
    }

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
