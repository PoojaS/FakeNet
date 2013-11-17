package protocol;


import model.Buffer;

/**
 * Represents a communication session. Nodes use a single session for connection-less protocols. This design choice
 * to use a session to handle both connection oriented and connection less communications helps to keep the
 * implementation of a node simple.
 */
public class Session {

    private Buffer buffer;
    private String neighbor;

    public Session(String neighbor) {
        this.neighbor = neighbor;
        buffer = new Buffer();
    }

    public Packets read(int bandwidth) {
        Packets result = new Packets();
        Packets dataPackets = buffer.read(bandwidth);
        if (dataPackets.isNotEmpty()) {
            result.addAll(dataPackets);
        }
        result.setDestination(neighbor);
        return result;
    }
}
