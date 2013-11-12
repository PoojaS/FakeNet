package protocol;

public class Packet {

    private byte[] data;
    private byte[] headers;
    private String destination;

    public Packet(byte[] data, byte[] headers) {
        this.data = data;
        this.headers = headers;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int size() {
        return data.length + headers.length;
    }

    public byte[] data() {
        return data;
    }
}
