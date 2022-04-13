package MapFiles;

import java.util.Arrays;
import java.util.Random;

public class Island extends Landmass {

    boolean hasPort;

    public Island(int size, String name) {
        super(size); this.setName(name);
    }


    protected void refreshBooty(){
        System.out.println("Booty has been refreshed!");
    }

    /**
     * Method to randomly select an island name from the IslandNames enum file and return the toString method.
     *
     */
    public static String randomEnum(){
        return IslandNames.values()[new Random().nextInt(IslandNames.values().length)].toString();

    }

}
