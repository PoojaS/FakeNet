package model;

import protocol.Packet;
import protocol.Packets;

public class Buffer {

    private byte[] data = new byte[10000];
    private int current = 0;

    public synchronized Packets read(int bytes) {
        int dataSize = bytes - new Packet().defaultHeaderSize();
        byte[] result = new byte[dataSize];
        Packets packets = new Packets();
        for (int i = current, j = 0; j < dataSize; i++, j++) {
            result[i] = data[i];
            current = (current + 1) % data.length;
        }
        packets.add(new Packet(result, new byte[0]));
        return packets;
    }
}
