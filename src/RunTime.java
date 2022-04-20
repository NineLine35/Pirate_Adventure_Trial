import GameLoop.OpeningLoop;
import MapFiles.Landmass;
import MapFiles.MainMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RunTime {

  public static void main(String[] args) throws IOException {

    //Create random map
    Landmass playMap[][] = MainMap.createMap();

    // Launch opening game play loop
    OpeningLoop.launch(playMap);

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
        else if(enteredText.toLowerCase().contains("map")){

          //Initiate current player location
          MainMap.Coordinates startLocation = new MainMap.Coordinates(2,2);
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

  }

}




