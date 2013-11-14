package protocol;


import model.Buffer;

import java.util.LinkedList;
import java.util.Queue;

public class Session {

    private Buffer buffer;
    private Queue<Packet> acks;
    private String neighbor;

    public Session(String neighbor) {
        this.neighbor = neighbor;
        buffer = new Buffer();
        acks = new LinkedList<Packet>();
    }

    public Packets read(int bandwidth) {
        Packets result = new Packets();
        Packets acks = readAcks(bandwidth);
        if (acks.size() < bandwidth) {
            Packets dataPackets = buffer.read(bandwidth - acks.size());
            if (dataPackets.isNotEmpty()) {
                result.addAll(dataPackets);
            }
        }
        result.addAll(acks);
        result.setDestination(neighbor);
        return result;
    }


    public void receive(Packet packet) {
        acks.add(new Packet());
    }

    private Packets readAcks(int bandwidth) {
        int totalRead = 0;
        Packets result = new Packets();
        while (totalRead < bandwidth && !acks.isEmpty()) {
            if (totalRead + acks.peek().size() <= bandwidth) {
                Packet packet = acks.remove();
                totalRead += packet.size();
                result.add(packet);
            } else {
                break;
            }
        }
        return result;
    }
}
