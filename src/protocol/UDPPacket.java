package protocol;

import model.BinaryRepresentation;

/**
 * Models a UDP packet
 */
public class UDPPacket {

    public static final int HEADER_SIZE = 2;
    public static final int MAXIMUM_PACKET_SIZE = 2 ^ 16;

    private int sourcePort;
    private int destinationPort;
    private int udpLength;
    private byte[] udpChecksum;
    private byte[] data;

    public UDPPacket(byte[] data) {
        this.data = data;
        this.udpLength = data.length;
    }

    public void computeChecksum(byte[] sourceAddress, byte[] destinationAddress) {
        BinaryRepresentation binaryRepresentation = new BinaryRepresentation();
        binaryRepresentation.add(sourceAddress);
        binaryRepresentation.add(destinationAddress);
        binaryRepresentation.add(0x0011); // Protocol type of udp at the IP layer
        binaryRepresentation.add(udpLength);
        binaryRepresentation.add(data);
        udpChecksum = binaryRepresentation.value();
    }

    public int size() {
        return HEADER_SIZE + data.length;
    }
}
