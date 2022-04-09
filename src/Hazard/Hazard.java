package Hazard;

import java.util.ArrayList;
import java.util.List;

public class Hazard {
    private String lookUp;
    private String storyMessage;
    private String pointsToTake;
    private String percentageLoss;
    private String sailDamage;  //Setting general health of sails as a percentage of full health
    private ArrayList<Hazard> hazardList;

    /**
     * This is the constructor for hazard
     */
    public Hazard(){

    }

    /**
     * This is a constructor to pass values
     * @param lookUp
     * @param storyMessage
     * @param pointsToTake
     * @param sailDamage
     */
    public Hazard(String lookUp, String storyMessage, String pointsToTake, String sailDamage, String percentageLoss){
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.pointsToTake = pointsToTake;
        this.sailDamage = sailDamage;
        this.percentageLoss = percentageLoss;
    }

    {
        this.hazardList = new ArrayList<Hazard>();
    }

    public String getStoryMessage(){return storyMessage;}

    public String getPointsToTake(){return pointsToTake;}

    public String getSailDamage(){return sailDamage;}

    public String getLookUp(){return lookUp;}

    public String getPercentageLoss(){return percentageLoss;}

    public ArrayList<Hazard> getHazardList() {
        return hazardList;
    }


    public void addHazard(String lookUp, String storyMessage, String pointsToTake, String sailDamage, String percentageLoss) {
        //create and load clients list
        Hazard hazard;

        //create the client
        hazard = new Hazard(lookUp, storyMessage, pointsToTake, sailDamage, percentageLoss);

        //add the client to the list
        addHazardList(hazard);
    }

    /**
     * add a hazard to the list
     * @param hazard
     */
    public void addHazardList(Hazard hazard) {
        //add the client
        this.hazardList.add(hazard);
    }


    /*public String getHazardInfo(int index){
        return hazardList.get();
    };*/

    /*public int findIndex(int lookUp){
        int x;
        for(x =0; x<hazardList.size(); x++){
            String a = hazardList.get(x);
            int findLookup = hazardList(x);        }
        return 0;
    };*/

    //not sure if I need this part yet
    /*{
        this.stormList = new ArrayList<Storm>();
    }*/

    //get the random number of 2 options to determine if we are doing storm or human
    public void callForHazard(){
       int hazardType = getRandom(0,1);
    }

    //need to generate a random number to know which type of hazard to go to and collect that hazards list
    public int getRandom(int min, int max){
        //System.out.println("Random value of type int between "+min+" to "+max+ ":");
        int random = (int)(Math.random()*(max-min+1)+min);
        //System.out.println(random);
        return random;
    };

    /*public void goToHazard(int hazardType){
        if (hazardType == 0){
            //if 0 then we will go to storm
            Storm storm = new Storm();
            //hazardIndex = storm.stormLevel;
            System.out.println("the storm " + storm.stormLevel);
        }
        else{
            //if 1 then we will go to human
            Human human = new Human();
            System.out.println("the human " +human.humanLevel);
        }
    }*/
}

