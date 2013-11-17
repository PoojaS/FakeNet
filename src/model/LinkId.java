package model;


/**
 * Models the id of a link. Since every link is bi-directional, inverse links added to the model, if any, will take the
 * same id as the forward link
 */
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
