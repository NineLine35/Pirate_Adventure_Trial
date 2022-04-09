package Hazard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import MapFiles.Landmass;
import MapFiles.MainMap;

public class StartTest {
    public static <ListArray> void main(String[] args) {
        // TODO Auto-generated method stub
        boolean land = false;
        int hazardFirstKey;
        int hazardSecondKey;
        String fullHazardKey;
        Hazard hazard = new Hazard();
        //make the list of hazards
        hazard.addHazard("00"," Land Storm 1","1","0","0");
        hazard.addHazard("01"," Land Storm 2","2","1","0");
        hazard.addHazard("02"," Land Storm 3","3","3","0");
        hazard.addHazard("10"," Land Human 1","0","0","10");
        hazard.addHazard("11"," Land Human 2","1","0", "25");
        hazard.addHazard("12"," Land Human 3","0","0","50");
        hazard.addHazard("20"," Sea Storm/Rock 1","1","0","0");
        hazard.addHazard("21"," Sea Storm/Rock 2","0","1","0");
        hazard.addHazard("22"," Sea Storm/Rock 3","1","0","0");
        hazard.addHazard("30"," Sea Human 1","0","1","25");
        hazard.addHazard("31"," Sea Human 2","1","0","50");
        hazard.addHazard("32"," Sea Human 3","0","1","75");

        //generate a random number to start the hazards
        if(land){
            //we are on land, the random number should be either 0 or 1, Since the descriptions will be different
            //on land vs water. 0 = land environmental (storms), 1 = human hazard on land
            hazardFirstKey = getRandom(0,1);
        }
        else {
            //we are in water, the random number should be 2 or 3.  2 = human hazard on land, 3 = water, could be storms
            //rocks and so on
            hazardFirstKey = getRandom(2, 3);
        }

        System.out.println("the hazard " + hazardFirstKey);

        //get the second key, there will be 3 options for each first random
        hazardSecondKey = getRandom(0,2);

        System.out.println("the hazard " + hazardSecondKey);

        //make a full key to look up in the hazard list
        fullHazardKey = Integer.toString(hazardFirstKey) + Integer.toString(hazardSecondKey);

        //find the right key and its values
        //get the hazard list
        ArrayList<Hazard> hazardList = hazard.getHazardList();

        //loop through the list to find the hazard
        for (int x = 0; x < hazardList.size(); x++) {
            //get the hazard information
            Hazard a = hazardList.get(x);

            //get the value for the lookup
            String onLineValue = a.getLookUp();

            //if the value we are on is the value we are looking for, get the information for that value
            if(onLineValue.equals(fullHazardKey)){
                //get the message
                String theMessage = a.getStoryMessage();
                //get the hit value
                String theHit = a.getPointsToTake();
                //get the sail damage value
                String theSail = a.getSailDamage();
                //get the percent that will be used to remove money
                String thePercent = a.getPercentageLoss();
                System.out.println("Look up value: " + fullHazardKey + " The message " + theMessage + " The hit " + theHit + " The sail " + theSail + " Percentage Lost " + thePercent);
            }
        }

        //Hazard hazardList = new Hazard(fullHazardKey, Arrays.asList("0","1","testing"));

        //Hazard hazard = new Hazard(00,"this is the story", 1,0,Arryas.asList);
        //build a list to access information

        /*if(hazardType == 0){

        }
        else{

        }

        hazard.goToHazard(hazardType);
        System.out.println("the combo " + hazardType );

       Thunder thunder = new Thunder();

        //generate random number here to send if it is raining or not, if the random number is 0, then check hurricane
        int amount = 3;
        String message = thunder.raining(amount);
        System.out.println(message);
        try {
            int theAmount = thunder.getRainAmount();
            System.out.println(theAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Money lost from Thunder: " + thunder.calculateMoneyLost(10, 20));

        Hurricane hurricane = new Hurricane();
        System.out.println("Money lost from Hurricane: " + hurricane.calculateMoneyLost(10, 10));

        SandStorm sandStorm = new SandStorm();
        System.out.println("Money lost from Sand Storm: " + sandStorm.calculateMoneyLost(5, 10));

        sandStorm.SandBlowing(1);

        Damages damages = new Damages();
        damages.stormDamage(2);

         */
    }
    public static int getRandom(int min, int max){
        //System.out.println("Random value of type int between "+min+" to "+max+ ":");
        int random = (int)(Math.random()*(max-min+1)+min);
        //System.out.println(random);
        return random;
    };
}
