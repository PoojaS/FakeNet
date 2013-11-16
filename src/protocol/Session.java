package protocol;


import model.Buffer;

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
