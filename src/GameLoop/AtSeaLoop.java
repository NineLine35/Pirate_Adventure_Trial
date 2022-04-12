package GameLoop;

import MapFiles.Landmass;

public class AtSeaLoop extends Loop {

    @Override
    public void launch(Landmass[][] x){

        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();

        System.out.println();
        System.out.println("Placeholder for At Sea gameplay loop\n");

    }
}
