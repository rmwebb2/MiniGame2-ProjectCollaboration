public class Exit {
    private String direction;
    private int destination; //change to object Room? private Room room;
    private Room room;

    public Exit(String direction, int destination) {
        this.direction = direction;
        this.destination = destination;
    }

    public String getDirection() {
        return this.direction;
    }

    public int getDestination() {
        return this.destination;
    }

    public Room getRoom() {
        return this.room;
    }

}
