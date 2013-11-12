package protocol;

public class Packet {

    private byte[] data;
    private byte[] headers;
    private String destination;
    private String source;

    public Packet(byte[] data, byte[] headers) {
        this.data = data;
        this.headers = headers;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
