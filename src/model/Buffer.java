package model;

import protocol.Packet;
import protocol.Packets;
import protocol.UDPPacket;

public class Buffer {

    private byte[] data = new byte[10000];
    private int current = 0;

    public synchronized Packets read(int bytes) {
        Packets packets = new Packets();
        int bytesToRead = bytes;
        while (bytesToRead > UDPPacket.HEADER_SIZE) {
            int currentPacketSize = Math.min(bytesToRead, UDPPacket.MAXIMUM_PACKET_SIZE);
            byte[] result = new byte[currentPacketSize];
            for (int i = current, j = 0; j < currentPacketSize; i++, j++) {
                result[j] = data[i];
                current = (current + 1) % data.length;
            }
            packets.add(new Packet(new UDPPacket(result)));
            bytesToRead -= currentPacketSize;
        }
        return packets;
    }
}
