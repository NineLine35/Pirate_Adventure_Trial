package Hazard;

public class Damages {
    public boolean stormDamage(int costOfStorm) {
        HitAccount hitAccount = HitAccount.getInstance();

        boolean remove = false;

        //the if means there has been some damage and we need to check if the ship has exceeded the max allowable damage
        if(costOfStorm != 0){
            //add the hit
            hitAccount.addHit(costOfStorm);

            //if it was greater than 3, the ship has sunk
            if(remove){
                System.out.println("Your ship has been sunk, GAME OVER");
            }
            else
            {
                //get the number of hits
                int balance = hitAccount.getBalance();

                //get the hits left
                int hitsLeft = 4 - balance;
                System.out.println("Your ship has been hit, you have " + hitsLeft + "left until being sunk.");
            }
        }
        //this section is for a 0 return, which will remove hits, meaning the ship has been repaired.  This does need
        //modification depending on what we decide to do here with items and fixes.  If we track the items and fixes,
        //maybe only on thing can be fixed like a rip in the sail, but the hole in the boat cannot be fixed?
        else{
            if (hitAccount.getBalance() > costOfStorm) {
                //there is not enough money
                remove = hitAccount.removeHit(costOfStorm);
            }

            if (remove) {
                System.out.println("Your damages have been repaired");
            } else {
                System.out.println("Not enough money to cover damages.");
            }
        }
        return remove;
    }
}
