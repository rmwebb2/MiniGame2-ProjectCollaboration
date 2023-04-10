import java.util.ArrayList;

public class Room {
    private String roomNum;
    private String roomName;
    private ArrayList<String> roomDescription;
    private ArrayList<Exit> exits;
    private ArrayList<Item> items;
    private boolean visited;

    public Room(String roomNum, String roomName, ArrayList<String> roomDescription, ArrayList<Exit> exits, ArrayList<Item> items) {
        this.roomNum = roomNum;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.exits = exits;
        this.items = items;
        this.visited = false;
    }

    public Room() {
        
    }

    public String getRoomNum() {
        return this.roomNum;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public ArrayList<String> getRoomDescription() {
        return this.roomDescription;
    }

    public ArrayList<Exit> getExits() {
        return this.exits;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public boolean hasVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public Item getItemByName(String name) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Item removeItemByName(String name) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                this.items.remove(item);
                return item;
            }
        }
        return null;
    }

    public Room getRoomByExit(String direction) {
        for (Exit exit : this.exits) {
            if (exit.getDirection().equalsIgnoreCase(direction)) {
                return exit.getDestination();
            }
        }
        return null;
    }

    public String getItemDescription() {
        StringBuilder sb = new StringBuilder();
        if (items.isEmpty()) {
            sb.append("There are no items available in this room.");
        } else {
            sb.append("This room has the following items:\n");
            for (Item item : items) {
                sb.append("- ");
                sb.append(item.getName());
                sb.append(": ");
                sb.append(item.getDescription());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
