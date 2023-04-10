/**
 * AdventureGame.java
 *
 */
public class AdventureGame {
    public static void main(String[] args) {
        String roomsFilePath = "Rooms.txt";
        GameUI gameUI = new GameUI(roomsFilePath);

        if (gameUI.getRooms().isEmpty()) {
            System.out.println("No rooms found. Exiting the game.");
        } else {
            System.out.println("Rooms found: " + gameUI.getRooms().size());
            gameUI.playGame();
        }
    }
}
