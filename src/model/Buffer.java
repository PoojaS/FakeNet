package model;

import protocol.Packet;
import protocol.Packets;
import protocol.UDPPacket;

public class Buffer {

    private byte[] data = new byte[10000];
    private int current = 0;

    /*TODO: Add chunking*/
    public synchronized Packets read(int bytes) {
        int dataSize = bytes - Packet.HEADER_SIZE;
        byte[] result = new byte[dataSize];
        Packets packets = new Packets();
        for (int i = current, j = 0; j < dataSize; i++, j++) {
            result[i] = data[i];
            current = (current + 1) % data.length;
        }
        packets.add(new Packet(new UDPPacket(result)));
        return packets;
    }
}
