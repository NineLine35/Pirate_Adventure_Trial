package GameLoop;

import MapFiles.*;
import Player.Player;

import java.util.Arrays;
import java.util.Scanner;

public class OpeningLoop {


    public static void launch(Landmass[][] playmap) {

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

        // Store user input in variable
        String enteredText = userInput.nextLine();

        Player.getInstance().setName(enteredText);

        System.out.println("\nNice to meet ya, " + Player.getInstance().getName());
        System.out.println("And where shall we go??");


        // Game loop open while input stream is active
        while (userInput.hasNext()) {

            enteredText = userInput.nextLine();

            if(enteredText.toLowerCase().contains("exit")){
                System.out.println("Good bye!");
                System.exit(0);
            }
            else if(enteredText.toLowerCase().contains("map")){

                //DEBUG print out map to console
                System.out.println("______MAP______");
                System.out.println(Arrays.deepToString(playmap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
                System.out.println("_______________");

            }
            else if(enteredText.toLowerCase().contains("where am i")){
                int x = Player.getInstance().getLocation().getRow();
                int y = Player.getInstance().getLocation().getColumn();

                if (playmap[x][y] instanceof OpenWater){
                    System.out.println("Ye in the open water captain!!");
                }
                else if(playmap[x][y] instanceof Island){
                    System.out.println("Seems we be on land....yuck");
                }

            }
            else if(enteredText.toLowerCase().contains("sail")){

                System.out.println("Which direction shall we sail captain??");

                String userSelection = userInput.nextLine().toUpperCase();

                try {
                    Player.getInstance().setLocation(MainMap.movePlayer(Player.getInstance().getLocation().getRow(),
                            Player.getInstance().getLocation().getColumn(), Direction.valueOf(userSelection),playmap));
                }
                catch (Exception e)
                {
                    System.out.println("Not a valid direction sailor!");
                }

            }
            else {
                System.out.println("Not recognized!  We have not implemented commands.  Try 'EXIT'....\n");
            }


        }
    }
}
