package protocol;

public class UDPPacket {

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
        return data.length;
    }
}
