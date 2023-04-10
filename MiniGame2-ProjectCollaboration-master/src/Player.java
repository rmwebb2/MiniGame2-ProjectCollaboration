import java.util.ArrayList;

public class Player {
    private Room location;
    private ArrayList<Item> inventory;

    public Player() {
        location = new Room();
        inventory = new ArrayList<>();
    }

    public Player(Room location) {
        this.location = location;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Room getLocation() {
        return this.location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public void look(String itemName) {
        for (Item item : location.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getDescription());
                return;
            }
        }
        System.out.println("No item found");
    }

    public void getItems(String itemName, String itemDescription) {
        for (Item item : location.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                Item newItem = new Item(itemName, itemDescription);
                inventory.add(newItem);
                location.getItems().remove(item);
                System.out.println(itemName + " has been added to player's inventory!");
                return;
            }
        }
        System.out.println("No item available.");
    }


    public void removeItem(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
            System.out.println(item.getName() + " has been removed from player's inventory!");
        } else {
            System.out.println("Error: Item not found in player's inventory.");
        }
    }

    public void remove(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                location.addItem(item);
                inventory.remove(i);
                System.out.println(itemName + " has been removed from player inventory and is in "
                        + location.getRoomName() + ".");
                return;
            }
        }
        System.out.println("No item available.");
    }


    public void move(String direction) {
        ArrayList<Exit> exits = location.getExits();
        for (Exit exit : exits) {
            if (exit.getDirection().equalsIgnoreCase(direction)) {
                location = exit.getDestination();
                location.setVisited(true);
                System.out.println(location.getRoomName() + "\n"
                        + location.getRoomDescription() + " " + location.getItemDescription());
                return;
            }
        }
        System.out.println("Error: Room does not exist in that direction.");
    }
}
