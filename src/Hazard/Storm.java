package Hazard;

/*abstract public class Storm {
    public abstract int calculateMoneyLost(int inches, int money);
    }*/

import java.awt.*;
import java.util.ArrayList;

public class Storm {
    private String storyMessage;
    private int pointsToTake;
    private int sailDamage;  //Setting general health of sails as a percentage of full health
    private boolean isDestroyed = false;
    private ArrayList<Storm> stormList;

    /*public Storm(String storyMessage, int pointsToTake, int sailDamage, boolean isDestroyed){
        this.storyMessage = storyMessage;
        this.pointsToTake = pointsToTake;
        this.sailDamage = sailDamage;
        this.isDestroyed = isDestroyed;
    }*/
    /*public Storm(){}

    public String getStoryMessage(){return storyMessage;}
    public void setStoryMessage(){this.storyMessage = storyMessage;}

    public int pointsToTake(){return pointsToTake;}
    public void setPointsToTake(){this.pointsToTake = pointsToTake;}

    public int getSailDamage(){return sailDamage;}
    public void setSailDamage(){this.sailDamage = sailDamage;}

    public boolean getIsDestroyed(){return isDestroyed;}
    public void setIsDestroyed(){this.isDestroyed = isDestroyed;}

    /*int stormLevel = getRandom(0,4);

    public void stormList(int stormLevel) {
        //add the enrollment
        if (stormLevel == 0){

        }
    }*/
}
