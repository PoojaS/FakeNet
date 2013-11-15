package protocol;

public class Packet {

    private UDPPacket packet;
    private String destination;
    private String source;

    public Packet(UDPPacket packet) {
        this.packet = packet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int size() {
        return packet.size();
    }

    public void computeChecksum() {
        packet.computeChecksum(source.getBytes(), destination.getBytes());
    }
}
