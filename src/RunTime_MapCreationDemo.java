import MapFiles.Direction;
import MapFiles.Landmass;
import MapFiles.MainMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RunTime_MapCreationDemo {

  //Basic code to read user input through InputStreamReader process
  private static String userInput(String console) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(console);
    System.out.flush();
    return input.readLine();

  }

  public static void main(String[] args) throws IOException {

    //Create random map
    Landmass playMap[][] = MainMap.createMap();


    //Initiate start location
    MainMap.Coordinates startLocation = new MainMap.Coordinates(2,2);
    //Initiate current player location
    MainMap.Coordinates currentLocation = startLocation;


   //DEBUG print out map to console
    System.out.println("______MAP______");
    System.out.println(Arrays.deepToString(playMap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    System.out.println("_______________");



    //********************************************************************************************************************
    //********************************************************************************************************************

    //Test of basic console interface
    System.out.println();
    System.out.println("Welcome to the Pirate Game Trial!\n" +
            "*********************************\n");

    String userSelection = (userInput("Please enter a direction to travel:\n")).toUpperCase();


    // Moving the player based on "userSelection" direction.  Try/Catch just in-case the player enters a non-direction
    try {
      currentLocation = MainMap.movePlayer(currentLocation.getRow(), currentLocation.getColumn(), Direction.valueOf(userSelection));
    }
    catch (Exception e)
    {
      System.out.println("Not a valid direction sailor!");
    }


    // Check the landmass location of the player after they moved North
    MainMap.mapCheck(currentLocation,playMap);

  }

}




