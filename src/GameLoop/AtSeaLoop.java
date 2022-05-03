package GameLoop;

import Hazard.Hazard;
import Inventory.ItemTypes;
import MapFiles.*;
import Player.Player;

import java.util.*;
import java.util.function.Supplier;

public class AtSeaLoop extends OpeningLoop{


    public static void launch() {

        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();

        //int random = (int) (Math.random() * (1 - 0 + 1) + 0);
        int randomHazard = getRandom(0, 1);
        System.out.println("Hazard or no? " + randomHazard);
        if (randomHazard == 1) {
            //create the hazard object
            Hazard hazard = new Hazard();

            //get a random hazard
            randomHazard = getRandom(0, 5);

            //make the list of hazards
            hazard.addHazard(0, """
                    Just sailin' a way at sea, the waves calm fer this here time o' day.  Sunset o' orange, reds an' yellows
                    fill this here sky. As ye look off into the distance, admirin' the beauty o' sea, ye notice an odd 
                    lookin' movin' toward the boat. Well blow me o'er, where did that there whale come from? Ye almost 'it
                    it!  Jolly thin' ye 'ave cat like reflexes an' did a sharp turn to avoid it, but too bad ye ended to 
                    the sky 'ittin' a reef!  Looks like ye 'ave a 'ole in yer ship, ye be goin' to 'ave to patch that 
                    there 'ole before ye sink!\n""", 1, 0,0);
            hazard.addHazard(1, """
                    Up an' down, the rollin' o' the waves, almost makes ye sea sick. There be somethin' brewin' off into 
                    the distance. This here ain't just an ordinary storm, but a storm o' the ages. Ye can see from below it,
                    it 'as a massive center, almost like an me good eye lookin' straight into yer soul. Around it, solid 
                    white walls an' beyond that there, darkness. The seven seas must be comin' to an end! ye continue on, 
                    like a true gentleman o' fortune, nothin' be goin' to avast ye. As the storm gets closer an' closer, 
                    the waves become almost unmanageable, pullin' ye wherever they want to take ye. Ye 'ead to the lower 
                    level with the crew an' pray ye survive what be next. Ye sit fer what seems like eternity an' soon 
                    calmness comes. As ye emerge to the deck, ye can see all the damage 'ad been done. Ye lost at least 
                    'alf o' yer sails, an' what be left o' them, flappin' in the wind. it will take ye days, maybe weeks, 
                    to get to a port.\n""", 0, 50,0);
            hazard.addHazard(2, """
                    Fer days ye 'ave been sailing, gettin' closer an' closer to yer destination. The waters be deep blue an' 
                    cold. Ye ain't sure 'ow ye got so farrr off course, but these waters be unfamiliarrr an' give ye a 
                    feelin' o' uneasiness. One o' the crew starts to yell an' point to the fore o' the ship. Ye climb the 
                    tower to get a better look from above. What be that there?  ye see somethin' with a ridged aft, like 
                    spikes, an' it 'as scales. It be a green color, shimmery an' bright. Like a snake, it moves about the 
                    water, divin' in an' out an' then slitherin' left an' right. It be 'uge, somethin' prehistoric. Ne'er 
                    spied anythin' like it, so ye try to steer clearrr. Ye climb aft down to the deck an' try to navigate 
                    away from it, but it keeps followin' ye. Ye fire a cannon at it, an' ye immediately realize ye made a 
                    terrible mistake. It be light purple eyes turn a bright red, like fire. Ye made it angry. Before ye 'ave 
                    time to react, it be massive tail swings an' makes a direct 'it to the side o' the ship. That there be a 
                    big 'ole an' ye need to find a port fast, but ye be in unfamiliarrr waters, will ye be able to find a 
                    port?\n""", 1, 0,0);
            hazard.addHazard(3, """
                    Ye get up on deck to the sound o' voices, manly voices. Their voices be a bit muffled, an' it be 'ard 
                    fer ye to understand, but ye think ye 'eard them say gold. Ye quickly rush to the sky to the deck above,
                    leavin' yer crew asleep, an' look o'er the side o' yer boat to find an empty dingy boat floatin' right 
                    next to the ship. There be a rope attached to a grapplin' 'ook, which 'as down to Davy Jones' Locker 
                    its sharp points into yer boat. There be No men attached to the rope, so where be the men?  As ye turn 
                    around ye find a tall, gladiator o' a man standin' before ye. 'E punches ye in the me jolly me good 
                    eye an' knocks ye off yer feet. Ye know ye ain't a good fighter, so ye stay down. 'E an' 'is men take 
                    off with 200 o' yer gold. As the crew awake an' come up on deck, they notice yer black me good 
                    eye. Ye explain what 'as 'appened, an' the look o' disappointment across their faces. Ye fearrr fer the 
                    day they take o'er the ship, such as yerself did. What good be a ship with a Cap'n that there can't fight?\n"""
                    , 0,0,200);
            hazard.addHazard(4, """
                    Shiver me timbers!  Just as you are looking into the birght blue sky, adminring the beauty of the sea,
                    a pirate ship, lookin' like it been through one to many cannon wars, runs ye down. Fifty men, smellin' 
                    like a they went on a rum bender an' lookin' like they 'ave not bathed in months, aboard yer ship an' 
                    the stench o' sweat fills the air. They each demand ye 'and o'er one item, forcin' ye to lose 150 o' yer treasures. 
                    Ye feel poorer an' poorer by the day, it be rough bein' the new leader o' a crew that there can't fight.""",
                    0,0,150);
            hazard.addHazard(5, """
                    It been a jolly day at sea. Clearrr blue skies, calm waters, catchin' some fish fer dinner, rum in yer 
                    belly, just an amazin' day. Ye an' yer crew be relaxin' on deck, gettin' some sun, enjoyin' the mild, 
                    but warm, temperature. Off in the distance ye 'earrr a thar she blows, blast away!  been that there 
                    thunder?  no, it can't be, it be too nice out an' not a cloud in sight. Thar she blows, blast away!  
                    thar she blows, blast away!  what be 'appening?  then another thar she blows, blast away! followed by 
                    a "splash". Ye look o'er to where the splash occurred an' realize what be 'appening, ye be under attack!  
                    cannons be bein' launched directly at yer ship. Ye an' the crew get yer cannons ready an' start to fire 
                    aft. Some o' the cannons be gettin' awfully close to yer ship. Ye now see the ship, it be close enough 
                    to make out. They now 'ave archers pointin' arrows with flame. Ye tell the crew to take cover an' 
                    shortly after, arrows flyin' through the air. They set yer sails on fire, ye need to get these fires 
                    under control before yer 'ole ship sets fire. Yer crew sets off two cannons an' one be a direct it to 
                    the other boat. The direct 'it been a moment too late, as ye also received a direct 'it. The other ship 
                    starts to 'ead in the other direction, probably to 'ead into a port fer repairs before they sink. Ye 
                    assess yer damage an' 'ave one sail left an' a big 'ole in the ship. Ye need to get to a port before ye 
                    sink, but with one sail, it be slow movin'.\n""", 2,1,0);

            //place holder to display the random hazard number
            System.out.println("the second hazard key" + randomHazard);

            //get the hazard list
            ArrayList<Hazard> hazardList = hazard.getHazardList();
            //loop through to find your lookup value
            for (Hazard hazardItems : hazardList) {
                //get the lookup
                Supplier<Integer> onLine = () -> hazardItems.getLookUp();
                //check if it is our look up
                if (onLine.get() == randomHazard) {
                    //get the message
                    Supplier<String> description = () -> hazardItems.getStoryMessage();

                    //display the message to the player
                    System.out.println(description.get());

                    //get the hull points
                    Supplier<Integer> hitPoints = () -> hazardItems.getHullPoints();

                    //get the coin
                    Supplier<Integer> coinLoss = () -> hazardItems.getCoinToTake();

                    //get the sail damage
                    Supplier<Integer> sailDamage = () -> hazardItems.getSailDamage();

                    //go to the calculation to do the losses
                    hazard.hazardCalc(hitPoints, coinLoss,sailDamage);

/*                    if(Player.getInstance().getShip().getHullHitPoints() <= 0){
                        System.out.println("Looks like yer ship been down to Davy Jones' Locker!  Better luck next time!");
                        System.exit(0);
                    }*/
                }
            }
        }
        else
        {
            System.out.println("no hazard");
        }

        System.out.println(
                """
                        A long day and exceedingly long night of sailing in an endless sea of ocean and sky.  Nothing but
                        ocean all around you.  What next captain??\n"""
        );

        // Receive user input and parse it
        Scanner userInput = new Scanner(System.in);


    }
}

