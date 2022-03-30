import MapFiles.*;
import java.util.Arrays;

public class RunTime {

  public static void main(String[] args) {

    Landmass playMap[][] = MainMap.createMap();

    //Initiate start location
    MainMap.Coordinates startLocation = new MainMap.Coordinates(1,2);
    //Initiate current player location
    MainMap.Coordinates currentLocation = startLocation;


   //DEBUG print out map to console
    System.out.println(Arrays.deepToString(playMap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    //Move player North - TEST
    currentLocation = MainMap.movePlayer(1,2,Direction.NORTH);

    // Check the landmass location of the player after they moved North
    MainMap.mapCheck(currentLocation,playMap);


  }



}
