package protocol;

public class Packet {

    public static final int HEADER_SIZE = 2;

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
        return HEADER_SIZE + packet.size();
    }
}
