package GameLoop;

import MapFiles.Landmass;
import Player.Player;

import java.util.Scanner;

public class OpeningLoop extends Loop {

    @Override
    public void launch(Landmass[][] x) {

        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();

        System.out.println();
        System.out.println("Welcome to the Pirate Game Trial!\n" +
                "*********************************\n");

        System.out.println(
                """
                        You awaken to the sound of distant gulls and the creaking of worn planks as the ship slowly sways in the 
                        rolling ocean. Your head throbs after a night of rum and celebration of a mutiny that was long overdue.  
                        Oh well, tis the life of a pirate.  But, now what to do?  You didn't think that far ahead, eyes always on the plunder
                        not the planning.  But now as you walk up to the bridge, with the eyes of your crew bearing down on you
                        you realize that it is time to take charge.
                                                
                        What is yar name pirate??\n"""

        );

        // Receive user input and parse it
        Scanner userInput = new Scanner(System.in);

        // Game loop open while input stream is active
        while (userInput.hasNext()) {

            // Store user input in variable
            String enteredText = userInput.nextLine();

            Player.getInstance().setName(enteredText);

            System.out.println("\nNice to meet ya, " + Player.getInstance().getName());
            System.out.println("And where shall we go??");

            userInput.nextLine();
        }
    }
}
