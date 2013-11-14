package protocol;

import java.util.ArrayList;
import java.util.List;

public class Packets {

    private List<Packet> packets;

    public Packets(List<Packet> result) {
        packets = new ArrayList<Packet>(result);
    }

    public Packets() {
        packets = new ArrayList<Packet>();
    }

    public void add(Packet packet) {
        packets.add(packet);
    }

    public int size() {
        int result = 0;
        for (Packet packet : packets) {
            result += packet.size();
        }
        return result;
    }

    public List<Packet> all() {
        return new ArrayList<Packet>(packets);
    }

    public void addAll(Packets packets) {
        this.packets.addAll(packets.all());
    }

    public boolean isNotEmpty() {
        return !packets.isEmpty();
    }

    public void setSource(String id) {
        for (Packet packet : packets) {
            packet.setSource(id);
        }
    }

    public void setDestination(String destination) {
        for (Packet packet : packets) {
            packet.setDestination(destination);
        }
    }
}
