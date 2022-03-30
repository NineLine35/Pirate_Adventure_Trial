import MapFiles.*;
import java.util.Arrays;

public class RunTime {

  public static void main(String[] args) {

    Landmass playMap[][] = MainMap.createMap();

    //Initiate start location
    MainMap.Coordinates startLocation = new MainMap.Coordinates(0,2);


   //DEBUG print out map to console
    System.out.println(Arrays.deepToString(playMap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

  }



}
