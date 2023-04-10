import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameUI {
    private ArrayList<Room> rooms;
    private Room currentRoom;

    public GameUI(String roomsFilePath) {
        rooms = readFile(roomsFilePath);
        if (!rooms.isEmpty()) {
            currentRoom = rooms.get(0);
        }
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(currentRoom.getRoomName());
            System.out.println(currentRoom.getRoomDescription());

            if (currentRoom.getItems().isEmpty()) {
                System.out.println("There are no items available in this room.");
            } else {
                System.out.println("This room has the following items:");
                for (Item item : currentRoom.getItems()) {
                    System.out.println("- " + item.getName());
                }
            }

            System.out.print("\nEnter a command (type 'quit' to exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("quit")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }

            switch (command) {
                case "look":
                    System.out.println(currentRoom.getRoomName());
                    System.out.println(currentRoom.getRoomDescription());
                    break;
                case "backpack":
                    if (currentRoom.getItems().isEmpty()) {
                        System.out.println("Your backpack is empty.");
                    } else {
                        System.out.println("Your backpack contains the following items:");
                        for (Item item : currentRoom.getItems()) {
                            System.out.println("- " + item.getName());
                        }
                    }
                    break;
                default:
                    if (command.startsWith("inspect ")) {
                        String itemName = command.substring(8);
                        Item item = currentRoom.getItemByName(itemName);
                        if (item != null) {
                            System.out.println(item.getDescription());
                        } else {
                            System.out.println("Item not found.");
                        }
                    } else if (command.startsWith("get ")) {
                        String itemName = command.substring(4);
                        Item item = currentRoom.removeItemByName(itemName);
                        if (item != null) {
                            currentRoom.setVisited(true);
                            currentRoom = currentRoom.getRoomByExit("get");
                            currentRoom.addItem(item);
                            System.out.println("You picked up the " + item.getName() + ".");
                        } else {
                            System.out.println("Item not found.");
                        }
                    } else if (command.startsWith("drop ")) {
                        String itemName = command.substring(5);
                        Item item = currentRoom.getItemByName(itemName);
                        if (item != null) {
                            currentRoom.removeItem(item);
                            currentRoom.setVisited(true);
                            currentRoom = currentRoom.getRoomByExit("drop");
                            currentRoom.addItem(item);
                            System.out.println("You dropped the " + item.getName() + ".");
                        } else {
                            System.out.println("Item not found.");
                        }
                    } else if (command.startsWith("go ")) {
                        String direction = command.substring(3);
                        Room nextRoom = currentRoom.getRoomByExit(direction);
                        if (nextRoom != null) {
                            currentRoom = nextRoom;
                            currentRoom.setVisited(true);
                        } else {
                            System.out.println("Invalid direction.");
                        }
                    } else {
                        System.out.println("Invalid command.");
                    }
                    break;
            }
        }

        scanner.close();
    }



        public ArrayList<Room> readFile(String roomsFilePath) {
        ArrayList<Room> rooms = new ArrayList<>();

        File roomFile = new File(roomsFilePath);

        try {
            Scanner readFile = new Scanner(roomFile);

            int numRooms = Integer.parseInt(readFile.nextLine());
            rooms = new ArrayList<>(numRooms);

            // create the rooms and add them to the rooms ArrayList
            for (int i = 0; i < numRooms; i++) {
                String roomNum = Integer.toString(i);
                String roomName = readFile.nextLine();
                ArrayList<String> roomDescription = new ArrayList<>();
                ArrayList<Exit> exits = new ArrayList<>();
                ArrayList<Item> items = new ArrayList<>();

                String line = readFile.nextLine();
                while (!line.equals("----------")) {
                    roomDescription.add(line);
                    if (readFile.hasNext()) {
                        line = readFile.nextLine();
                    } else {
                        break;
                    }
                }

                Room room = new Room(roomNum, roomName, roomDescription, exits, items);
                rooms.add(room);
            }

            // set the exits to the already created rooms
            for (Room room : rooms) {
                String roomNum = room.getRoomNum();
                String line = readFile.nextLine();
                while (!line.equals("----------")) {
                    String[] parts = line.split(" ");
                    if (parts.length == 2 && parts[1].matches("\\d+")) {
                        String direction = parts[0];
                        int destinationIndex = Integer.parseInt(parts[1]);
                        if (destinationIndex >= numRooms) {
                            throw new RuntimeException("Invalid exit destination: room " + destinationIndex + " does not exist");
                        }
                        Room destination = rooms.get(destinationIndex);
                        Exit exit = new Exit(direction, destination);
                        room.addExit(exit);

                    }
                    if (readFile.hasNext()) {
                        line = readFile.nextLine();
                    } else {
                        break;
                    }
                }
            }

            // add items to rooms
            File itemFile = new File("Items.txt");
            if (itemFile.exists()) {
                Scanner itemScan = new Scanner(itemFile);

                while (itemScan.hasNextLine()) {
                    String id = itemScan.nextLine();
                    String name = itemScan.nextLine();
                    String description = itemScan.nextLine();
                    Item item = new Item(id, name, description);

                    if (itemScan.hasNextLine()) {
                        String indexString = itemScan.nextLine();
                        if (indexString.matches("\\d+")) {
                            int roomIndex = Integer.parseInt(indexString);
                            if (!rooms.isEmpty() && roomIndex < rooms.size()) {
                                Room room = rooms.get(roomIndex);
                                room.addItem(item);
                            }
                        }
                    } else {
                        break;
                    }
                }

                itemScan.close();
            }

            readFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found. Try another file name.");
            e.printStackTrace();
        }

        return rooms;
    }


}