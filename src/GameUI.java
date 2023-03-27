import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameUI {
    private ArrayList<Room> rooms;
    public static void main(String[] args) {
        GameUI gui = new GameUI();
        Scanner input = new Scanner(System.in);

        //invoking method to read text file

    }


    public ArrayList<Room> readFile() {
        //method to read the file
        //String fileName = "Rooms.txt";
        String readItems = "Items.txt";
        ArrayList<Room> rooms = new ArrayList<>();

        File roomFile = new File("Rooms.txt");
        String roomNum;
        Room room = null;

        try {
            Scanner readFile = new Scanner(roomFile);
            ArrayList<Item> items = null;

            while (readFile.hasNext()) {


                roomNum = readFile.nextLine();
                String roomName = readFile.nextLine();
                ArrayList<String> roomDescription = new ArrayList<>();
                ArrayList<Exit> exits = new ArrayList<>();
                items = new ArrayList<>();

                room = new Room(roomNum, roomName, roomDescription, exits, items);

                String line = readFile.nextLine();  //reading the File to read the room and adding the descriptions into roomDescription arraylist
                while (!line.equals("----------")) {
                    room.roomDescription.add(line);
                    line = readFile.nextLine();
                }

                while (readFile.hasNextLine() && !(line = readFile.nextLine()).equals("----------")) {
                    //String[] info = line.split(" ");
                    String direction = line.split(" ")[0];             //splitting the direction (e.g."SOUTH") & destination
                    int destination = Integer.parseInt(line.split(" ")[1]);
                    exits.add(new Exit(direction, destination));            //adding direction and destination to exits arraylist

                }

                room.setExits(exits);  //setting exits to exits arraylist
                // rooms.add(room); //adding each description into rooms arraylist
            }

            File itemFile = new File(readItems);

            try {
                Scanner itemScan = new Scanner(itemFile);
//                String id = itemScan.nextLine();
//                String name = itemScan.nextLine();
//                String description = itemScan.nextLine();

                while (itemScan.hasNext()) {
                    String id = itemScan.nextLine();
                    String name = itemScan.nextLine();
                    String description = itemScan.nextLine();
                    //String[] itemInfo = itemLine.split(",");
//                    String id = itemLine.split(" ")[0];
//                    String name = itemLine.split(" ")[1];
//                    String description = itemLine.split(" ")[2];

                    items.add(new Item(id, name, description));


                }
                room.setItems(items);

            } catch (FileNotFoundException ex) {
                System.out.println("File not found");

            }


            readFile.close();

        } catch (
                FileNotFoundException e) {
            System.err.println("File not found. Try another file name.");
            e.printStackTrace();
        }
        return rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }


    public ArrayList<String> getNavigation() {      //method to add directions to an arraylist
        ArrayList<String> navigation = new ArrayList<>();

        navigation.add("north");
        navigation.add("south");
        navigation.add("east");
        navigation.add("west");


        return navigation;

    }
}