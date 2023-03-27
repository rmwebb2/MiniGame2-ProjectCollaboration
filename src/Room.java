import java.util.ArrayList;

public class Room {
    private String roomNum;
    private String roomName;
    ArrayList<String> roomDescription;
    private boolean isVisited;
    ArrayList<Exit> exits;
    ArrayList<Item> items;

    public Room() {

    }


    public Room(String roomNum, String roomName, ArrayList<String> roomDescription, ArrayList<Exit> exits, ArrayList<Item> items) {
        this.roomNum = roomNum;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.isVisited = false;
        this.exits = exits;
        this.items = items;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public ArrayList<String> getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(ArrayList<String> roomDescription) {
        this.roomDescription = roomDescription;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits() {
        this.exits = exits;
    }


    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        if (isVisited == true) {
            System.out.println("\nYou have been here before!");
        }
        this.isVisited = isVisited;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(String name, String description) {
        items.add(new Item(name, description));
    }

    public String getItemDescription() {
        if (items.isEmpty()) {
            return "There are no items available here!";
        } else {
            String itemDes = "";
            for (Item item : items) {
                if (!itemDes.isEmpty()) {
                    itemDes = itemDes + ",";
                }
                itemDes = itemDes + item.getName();
            }
            return "This room has the following item: " + itemDes;
        }
    }

    public void removeItem(int num) {
        items.remove(num);
    }

    public void setExits(ArrayList<Exit> exits) {
    }

    public void addExit(String direction, Room neighbor) {
    }
}