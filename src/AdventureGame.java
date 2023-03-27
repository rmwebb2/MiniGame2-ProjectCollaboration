import java.util.ArrayList;
import java.util.Scanner;

public class AdventureGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameUI gui = new GameUI();
        AdventureGame game = new AdventureGame();
        ArrayList<Room> rooms = gui.getRooms();

        System.out.println("Welcome to my adventure game! You may navigate by typing directions into the console" +
                "Valid directions are north, south, east, and west. Type 'Start' to begin the game! :-)");

        String first = input.nextLine();

        if (first.equalsIgnoreCase("start")) {
            gui.readFile();
            rooms = gui.getRooms();

            System.out.println("\nHere are the rooms you will be going through.");
            for (Room room : rooms) {
                System.out.println(room.getRoomName());
            }

            Room firstRoom = rooms.get(0);
            Player player = new Player(firstRoom);
            Room location = player.getLocation();

            String roomNum = location.getRoomNum();
            String roomName = location.getRoomName();
            String roomDescription = String.valueOf(location.getRoomDescription());
            System.out.println(roomNum + " " + roomName + "\n" +roomDescription);

            while (true) {
                String command = input.nextLine();

                if (command.equalsIgnoreCase("look")) {
                    System.out.println(location.getRoomName() + "\n" + location.getRoomDescription() +
                            " " + location.getItemDescription());

                }

                else if (command.toLowerCase().startsWith("inspect")) {
                    String item = command.split(" ")[1];
                    player.look(item);
                }
                else if (command.toLowerCase().startsWith("get")) {
                    String item = command.split(" ")[1];
                    player.getItems(item);
                }
                else if (command.toLowerCase().startsWith("remove")) {
                    String item = command.split(" ")[1];
                    player.remove(item);
                }
                else if (command.equalsIgnoreCase("backpack")) {
                    for (Item item : player.getInventory()) {
                        System.out.println(item.getName());
                    }
                }
                else {
                    player.move(command);
                    location = player.getLocation();
                    roomName = location.getRoomName();
                    roomDescription = String.valueOf(location.getRoomDescription());
                    System.out.println(roomName + "\n" + roomDescription);
                }
            }

        }
    }
}
