package Hazard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import MapFiles.Landmass;
import MapFiles.MainMap;

public class StartTest {
    public static <ListArray> void main(String[] args) {
        // TODO Auto-generated method stub




        boolean land = true;
        int hazardFirstKey;
        int hazardSecondKey;
        String fullHazardKey;
        Environmental environmental = new Environmental();
        Human human = new Human();

        //make the list of hazards
        environmental.addHazard("00", " Land Storm 1", "1", "0");
        environmental.addHazard("01", " Land Storm 2", "2", "1");
        environmental.addHazard("02", " Land Storm 3", "3", "3");
        human.addHazard("10", " Land Human 1", "10");
        human.addHazard("11", """
                It be a 'ot 'umid day as ye roll into port. The smells o' jasmine 
                an' salt in the air. Off in the distance ye 'earrr a woman's voice screamin' 'ysterically.  
                Could it be? A damsel in distress in need o' yer assistance?  Upon approach, ye see the damsel. 
                She 'as the longest dark locks ye 'ave e'er spied, the most beautiful brown doe eyes an' skin that 
                there been kissed by the sun. She be 'angin' from a long sturdy vine, swayin' in the breeze, below 'er 
                an alligator, waitin' fer 'is next meal. Ye scare the alligator off an' rescue the damsel. The next 
                mornin' ye find the damsel stole twenty-five percent o' yer coin!  no jolly deed goes unpunished.\n"""
                , "25");
        human.addHazard("12", """
                Shiver me timbers!  Just as ye enter in the bay to port, a gentleman
                o' fortune ship, lookin' like it been through one to many cannon wars, runs ye down. Fifty men, smellin' 
                like a they went on a rum bender an' lookin' like they 'ave not bathed in months, aboard yer ship an' 
                the stench o' sweat fills the air. They each demand ye 'and o'er one item, forcin' ye to lose fifty 
                percent o' yer treasures. Ye feel poorer an' poorer by the day, it be rough bein' the new leader o' a 
                crew that there can't fight.\n"""
                , "50");
        environmental.addHazard("20", " Sea Storm/Rock 1", "1", "0");
        environmental.addHazard("21", " Sea Storm/Rock 2", "0", "1");
        environmental.addHazard("22", " Sea Storm/Rock 3", "1", "0");
        human.addHazard("30", " Sea Human 1", "10");
        human.addHazard("31", " Sea Human 2", "25");
        human.addHazard("32", " Sea Human 3", "50");

        //generate a random number to start the hazards
        if (land) {
            //we are on land, the random number should be either 0 or 1, Since the descriptions will be different
            //on land vs water. 0 = land environmental (storms), 1 = human hazard on land
            hazardFirstKey = getRandom(0, 1);
        } else {
            //we are in water, the random number should be 2 or 3.  2 = human hazard on land, 3 = water, could be storms
            //rocks and so on
            hazardFirstKey = getRandom(2, 3);
        }

        System.out.println("the hazard " + hazardFirstKey);

        //get the second key, there will be 3 options for each first random
        hazardSecondKey = getRandom(0, 2);

        System.out.println("the hazard " + hazardSecondKey);

        //make a full key to look up in the hazard list
        fullHazardKey = Integer.toString(hazardFirstKey) + Integer.toString(hazardSecondKey);

        //these 3 variabels are hard coded for testing
        hazardFirstKey = 1;
        hazardSecondKey = 1;
        fullHazardKey = "12";

        //find the right key and its values
        //get the hazard list
        if (hazardFirstKey == 0 || hazardFirstKey == 2) {
            ArrayList<Environmental> environmentalList = environmental.getEnvironmentalList();
            for (int x = 0; x < environmentalList.size(); x++) {
                //get the hazard information
                Environmental a = environmentalList.get(x);

                //get the value for the lookup
                String onLineValue = a.getLookUp();

                //if the value we are on is the value we are looking for, get the information for that value
                if (onLineValue.equals(fullHazardKey)) {
                    //get the message
                    String theMessage = a.getStoryMessage();
                    //get the hit value
                    String theHit = a.getPointsToTake();
                    //get the sail damage value
                    String theSail = a.getSailDamage();

                    System.out.println("Look up value: " + fullHazardKey + " The message " + theMessage + " The hit " + theHit + " The sail " + theSail);
                }
            }
        } else {
            ArrayList<Human> humanList = human.getHumanList();
            for (int x = 0; x < humanList.size(); x++) {
                //get the hazard information
                Human a = humanList.get(x);

                //get the value for the lookup
                String onLineValue = a.getLookUp();

                //if the value we are on is the value we are looking for, get the information for that value
                if (onLineValue.equals(fullHazardKey)) {
                    //get the message
                    String theMessage = a.getStoryMessage();
                    //get the percent that will be used to remove money
                    String thePercent = a.getPercentToTake();
                    System.out.println("Look up value: " + fullHazardKey + " The message " + theMessage + " Percentage Lost " + thePercent);
                }
            }
        }
    }

    public static int getRandom(int min, int max) {
        //create a randome number with the range
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random;
    }
}

