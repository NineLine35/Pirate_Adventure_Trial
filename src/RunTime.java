import GameLoop.OpeningLoop;
import Inventory.ItemDatabase;
import MapFiles.Landmass;
import MapFiles.MainMap;
import Player.Player;
import Player.HighScoreTracker;
import org.apache.derby.jdbc.EmbeddedDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class RunTime {


    public static void main(String[] args) throws IOException, SQLException {

        //need to move this later where we populate the database
        //populate the database
        ItemDatabase db = new ItemDatabase();
        db.populateNewItemIslandData();

        Locale us = new Locale("en","US");
        Locale mx = new Locale("es","MX");

        WelcomeLocalization.introMessage(LocalTime.now(),us);

        System.out.println("New Game\n" + "High Scores\n\n" +
                "Make yar selection!\n\n");


        Scanner userInput = new Scanner(System.in);


        while (userInput.hasNext()) {

            String enteredText = userInput.nextLine();

            if (enteredText.toLowerCase().contains("new game")) {
                //Create random map
                Landmass playMap[][] = MainMap.createMap();

                // Assign the player the starting ship, a Sloop using a builder method
                Player.getInstance().assignShip("sloop", "The Jolly Rodger");

                // Launch opening game play loop
                OpeningLoop.launch(playMap);

            } else if (enteredText.toLowerCase().contains("high score")) {
                HighScoreTracker.readHighScores();
            } else if (enteredText.toLowerCase().contains("exit")) {
                System.out.println("Good bye!");
                System.exit(0);
            } else {
                System.out.println("What are ya talking about?  Do you want to EXIT instead??");

            }


        }

    }
}






