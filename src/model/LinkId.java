package model;


public class LinkId {

    private String id;

    public LinkId(String sourceId, String destinationId) {
        if (sourceId.compareTo(destinationId) < 0) {
            id = sourceId + destinationId;
        } else {
            id = destinationId + sourceId;
        }
    }

    public String value() {
        return id;
    }
}
