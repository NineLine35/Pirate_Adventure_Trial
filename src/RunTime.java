import MapFiles.Direction;
import MapFiles.Landmass;
import MapFiles.MainMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class RunTime {

 /* //Basic code to read user input through InputStreamReader process
  private static String userInput(String console) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(console);
    System.out.flush();
    return input.readLine();
  }*/

  public static void main(String[] args) throws IOException {

    //Create random map
    Landmass playMap[][] = MainMap.createMap();


    //********************************************************************************************************************
    //********************************************************************************************************************

    //Test of basic console interface
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
                    
                    This ship will fly under a new flag and a new name.  But what could that be?\n"""
    );

    // Receive user input and parse it
      Scanner userInput = new Scanner(System.in);

    // Game loop open while input stream is active
      while(userInput.hasNext()){

        // Store user input in variable
        String enteredText = userInput.nextLine();

        // Verify if user typed "exit".  If so, close the program
        if(enteredText.toLowerCase().contains("exit")){
          System.out.println("Good bye!");
          System.exit(0);
        }
        if(enteredText.toLowerCase().contains("map")){

          MainMap.Coordinates startLocation = new MainMap.Coordinates(2,2);
          //Initiate current player location
          MainMap.Coordinates currentLocation = startLocation;


          //DEBUG print out map to console
          System.out.println("______MAP______");
          System.out.println(Arrays.deepToString(playMap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
          System.out.println("_______________");

        }
        else {
          System.out.println("Not recognized!  We have not implemented commands.  Try 'EXIT'....");
        }


      }

   /* String userSelection = (userInput("What to name the ship? :\n"));


    //String userSelection = (userInput("Please enter a direction to travel:\n")).toUpperCase();


    // Moving the player based on "userSelection" direction.  Try/Catch just in-case the player enters a non-direction
    try {
      currentLocation = MainMap.movePlayer(currentLocation.getRow(), currentLocation.getColumn(), Direction.valueOf(userSelection));
    }
    catch (Exception e)
    {
      System.out.println("Not a valid direction sailor!");
    }


    // Check the landmass location of the player after they moved North
    MainMap.mapCheck(currentLocation,playMap);*/

  }

}




