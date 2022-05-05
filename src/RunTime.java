import GameLoop.OpeningLoop;
import MapFiles.Landmass;
import MapFiles.MainMap;
import Player.Player;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class RunTime {

    public static void main(String[] args) throws IOException {

        System.out.println("Ahoy there!  Welcome to the Generic Pirate Adventure Game!\n" +
                "__________________________\n\n"+ "New Game\n" + "High Scores\n\n" +
                "Make yar selection!\n\n");

        Scanner userInput = new Scanner(System.in);


        while(userInput.hasNext()) {

            String enteredText = userInput.nextLine();

            if (enteredText.toLowerCase().contains("new game")) {
                //Create random map
                Landmass playMap[][] = MainMap.createMap();

                // Assign the player the starting ship, a Sloop using a builder method
                Player.getInstance().assignShip("sloop", "The Jolly Rodger");

                // Launch opening game play loop
                OpeningLoop.launch(playMap);

            } else if (enteredText.toLowerCase().contains("high score")) {
                //High score read file
            } else if (enteredText.toLowerCase().contains("exit")) {
                System.out.println("Good bye!");
                System.exit(0);
            } else {
                System.out.println("What are ya talking about?  Do you want to EXIT instead??");

            }
        }

    }
}






