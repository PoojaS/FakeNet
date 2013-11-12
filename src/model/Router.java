package model;

import protocol.Packet;

import java.util.LinkedList;
import java.util.Queue;

public class Router extends Node {

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
