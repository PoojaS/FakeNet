package model;

import protocol.Packet;
import protocol.Packets;
import protocol.UDPPacket;

/**
 * A circular buffer that holds the data in the node
 */
public class Buffer {

    /**
     * Indexed in a circular fashion
     */
    private byte[] data = new byte[10000];
    /**
     * The current circular index into data.
     */
    private int current = 0;

    /**
     * Chunks the data into multiple packets if the number of bytes to read is greater than the packet size
     *
     * @param bytes The number of bytes of data to read
     * @return Packets whose total size is limited by the number of bytes to read
     */
    public synchronized Packets read(int bytes) {
        Packets packets = new Packets();
        int bytesToRead = bytes;
        while (bytesToRead > UDPPacket.HEADER_SIZE) {
            int currentPacketSize = Math.min(bytesToRead, UDPPacket.MAXIMUM_PACKET_SIZE);
            byte[] result = new byte[currentPacketSize];
            for (int i = current, j = 0; j < currentPacketSize; j++) {
                result[j] = data[i];
                current = (current + 1) % data.length;
                i = current;
            }
            packets.add(new Packet(new UDPPacket(result)));
            bytesToRead -= currentPacketSize;
        }
        return packets;
    }
}
