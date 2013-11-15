package protocol;

public class UDPPacket {

    public static final int HEADER_SIZE = 2;
    public static final int MAXIMUM_PACKET_SIZE = 10;

    private int sourcePort;
    private int destinationPort;
    private int udpLength;
    private byte[] udpChecksum;
    private byte[] data;

    public UDPPacket(byte[] data) {
        this.data = data;
        this.udpLength = data.length;
        this.udpChecksum = computChecksum(data);
    }

    private byte[] computChecksum(byte[] data) {
        return new byte[2];
    }

    public int size() {
        return HEADER_SIZE + data.length;
    }
}
