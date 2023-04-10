public class Exit {
    private String direction;
    private Room destination;

    public Exit(String direction, Room destination) {
        this.direction = direction;
        this.destination = destination;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setDestination(Room destination) {
        this.destination = destination;
    }

    public String getDirection() {
        return direction;
    }

    public Room getDestination() {
        return destination;
    }
}
