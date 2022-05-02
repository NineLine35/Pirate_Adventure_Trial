package GameLoop;

import Inventory.ItemTypes;
import MapFiles.*;
import Player.Player;

import java.util.*;

public class AtSeaLoop {


    public static void launch() {

        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();

        System.out.println();
        System.out.println("Placeholder for At Sea gameplay loop\n");
        System.out.println(
                """
                        A long day and exceedingly long night of sailing in an endless sea of ocean and sky.  Nothing but
                        ocean all around you.  What next captain??\n"""
        );

        // Receive user input and parse it
        Scanner userInput = new Scanner(System.in);


    }
}

