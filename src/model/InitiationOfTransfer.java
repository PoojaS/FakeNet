package model;

public class InitiationOfTransfer {

    private Integer directionOfTransfer;
    private Link link;

    public InitiationOfTransfer(Integer directionOfTransfer, Link link) {
        this.directionOfTransfer = directionOfTransfer;
        this.link = link;
    }

    public Integer getDirectionOfTransfer() {
        return directionOfTransfer;
    }

    public Link getLink() {
        return link;
    }
}
