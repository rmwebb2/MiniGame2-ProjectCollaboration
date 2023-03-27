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

    public void getItems(String itemName) {
        for (int i = 0; i <itemName.length(); i++) {
            Item item = location.getItems().get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                inventory.add(item);
                location.removeItem(i);
                System.out.println(itemName + " has been added to player's inventory!");
                return;
            }
        }
        System.out.println("No item available.");
    }

    public void remove(String itemName) {
        for (int i = 0; i < itemName.length(); i++) {
            Item item = this.inventory.get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                location.addItem(item.getId(), item.getName(), item.getDescription());
                inventory.remove(i);
                System.out.println(itemName + " has been removed from player inventory and is in " + location.getRoomName() + ".");
                return;
            }
        }
        System.out.println("No item available.");
    }

    public void move(String direction) {  //method to set the direction to player location
//        ArrayList<Exit> exits = room.get(location).getExits();
//        direction = direction.toLowerCase();
//        //Rooms current = room.get(location);
//        for (Exit ex : exits) {
//            if (ex.getDirection().equalsIgnoreCase(direction)) {  //setting location to destination
//                setLocation(ex.getDestination());
//            }
//        }

        for (Exit room: location.getExits()) {

            if(direction.equalsIgnoreCase(room.getDirection())) {

                this.location = room.getRoom();

                this.location.setVisited(true);
                return;
            }
        }


    }

}