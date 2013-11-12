package model;

import protocol.Packet;

import java.util.LinkedList;
import java.util.Queue;

public class Router extends Node {

    private Queue<Packet> packets;

    public Router(String id) {
        super(id);
        packets = new LinkedList<Packet>();
    }

    @Override
    public void moveUnitOfData() {
        if (!packets.isEmpty()) {
            modeDataToDestination(packets.remove().getDestination());
        }
    }

    @Override
    public void receive(Packet packet) {
        packets.add(packet);
    }
}
