import java.util.ArrayList;

public class Commands {
    GameUI gui = new GameUI();
    ArrayList<Room> rooms = gui.getRooms();
    Room firstRoom = rooms.get(0);
    private Player player = new Player(firstRoom);
    private Room location = player.getLocation();
    private String command;


    public void look(String itemName) {
        for (Item item : location.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getDescription());
                return;
            }
            System.out.println("No item available");
        }

        switch(command.toLowerCase()) {
            case "look":
                location = player.getLocation();
                System.out.println("\n" + location.getRoomName() + "\n" + location.getItemDescription() + " " + location.getItemDescription());
                break;
            case "backpack":
                for (Item item : player.getInventory()) {
                    System.out.println(item.getName());
                }
                break;
            default:
                if (command.toLowerCase().startsWith("inspect")) {
                    String item = command.split(" ")[1];
                    player.look(item);
                }
                else if (command.toLowerCase().startsWith("get")) {
                    String item = command.split(" ")[1];
                    player.getItems(item);
                }
                else if (command.toLowerCase().startsWith("remove")) {
                    String item = command.split(" ")[1];
                }
                else {
                    System.out.println("I do not know what " + command + "means. Try again.");
                }
        }
    }


}
