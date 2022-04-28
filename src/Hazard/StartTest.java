package Hazard;

import java.awt.*;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import MapFiles.Landmass;
import MapFiles.MainMap;
import org.apache.derby.jdbc.EmbeddedDataSource;

public class StartTest {
    //public static <ListArray> void main(String[] args)
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "jdbc:derby:gameResponses;create=true";
        //Connection conn = DriverManager.getConnection(url);
        try {

            EmbeddedDataSource ds = new EmbeddedDataSource();
            ds.setDatabaseName("gameResponses");

            Connection conn = DriverManager.getConnection(url);

            Statement stmt = conn.createStatement();

            System.out.println(conn);

            //stmt.executeUpdate("DROP TABLE gameResponses");

            stmt.executeUpdate("CREATE TABLE gameResponses ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255))");
            stmt.executeUpdate("INSERT INTO gameResponses VALUES (1, 'my response 1')");
            stmt.executeUpdate("INSERT INTO gameResponses VALUES (2, 'my response 2')");
            stmt.executeUpdate("INSERT INTO gameResponses VALUES (3, 'my response 3')");
            stmt.executeUpdate("INSERT INTO gameResponses VALUES (4, 'my response 4')");
            stmt.executeUpdate("INSERT INTO gameResponses VALUES (5, 'my response 5')");

            int responseRandom = getRandom(1,5);

            ResultSet rs = stmt.executeQuery("select id, "
                    + "						  name "
                    + "						  from gameResponses "
            + "                       where id =  "+ responseRandom +" ");


            while(rs.next()) {
                System.out.println("The Response: " + rs.getString("name"));
            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally {

        }

        System.exit(0);
        //PLAYING WITH ACTIONS
        //display a message to the user
        System.out.println("What do you want to do?");
        //set up scanner to read
        Scanner userInput = new Scanner(System.in);

        //default variables to track how many of ENUM entries there are
        int countActions = 0;
        int countDirections = 0;
        int countItem = 0;

        //default variables to get the values back from the ENUMs
        String directionValue = "";
        String actionValue = "";
        String itemValue = "";

        //default boolean to see if we have a match, valid option for entery to do something
        boolean match = false;
        //note if you sent the user a message to fix their input
        boolean messageSent = false;

        //get the user input next line
        String s=userInput.nextLine();

        //tokenize the input using a space delimiter
        StringTokenizer st=new StringTokenizer(s," ");//" " is the delimiter here.

        //set up loop to cycle through each item in the entry string
        while (st.hasMoreTokens() ) {
            //grab next
            String s1 = st.nextToken();
            //try to see if there is a valid action
            try{
                //have a valid action
                //FindAction actions = new FindAction(Actions.valueOf(s1.toUpperCase()));
                //actionValue = actions.actionIsLike();
                Actions getAction = Actions.valueOf(s1.toUpperCase());
                actionValue = getAction.getSendAction();
                //increase the action count by 1
                countActions++;
            }
            //not a valid action, maybe it is a direction
            catch(Exception e){
                try{
                    //have a valid direction
                    //FindDirection direction = new FindDirection(Direction.valueOf(s1.toUpperCase()));
                    //direction.directionIsLike();
                    //directValue = direction.directionIsLike();
                    Direction getDirection = Direction.valueOf(s1.toUpperCase());
                    directionValue = getDirection.getSendDirection();

                    //increase the direction count by 1
                    countDirections++;
                }
                //we do not have an action or direction, maybe it is an item
                catch(Exception d){
                    try{
                        //we have an item
                        Item getItem = Item.valueOf(s1.toUpperCase());
                        itemValue = getItem.getSendItem();

                        //increase the item count by 1
                        countItem++;
                    }
                    //we do not have anything here
                    catch(Exception i){
                        System.out.println("not found");
                    }
                    System.out.println("not found");
                }
                System.out.println("not found");
            }
            System.out.println(s1);
        }

        //System.out.println(countActions);
        //iof the count of actions is greater than 1, tell the user that they have too many actions
        if(countActions > 1){
            //The magic eightball says try again.
            //You do not speak pirate, so I do not understand, please try again
            System.out.println("you have too many actions");
            messageSent = true;
        }
        //if the actions is less than 1, there is no action, we need an action
        else if (countActions < 1){
            System.out.println("you do not have an action");
            messageSent = true;
        }
        else if (actionValue == "move"){
            if(countDirections > 1){
                //Are you trying to naviage the Bermuda Triangle?
                //Whoa there sailor, one direction at a time
                //Where do you think you are going, spinning in circles?  Pick one direction.
                System.out.println("you have too many directions");
                messageSent = true;
            }
            else if (countDirections < 1) {
                //Are you lost? You need to pick a direction to move
                System.out.println("you have to pick a directions");
                messageSent = true;
            }
            else{
                //this just means you have a valid entry
            }
        }
        else if (actionValue == "pickup") {
            if(countItem > 1) {
                //Only a land-lubber would pick that
                //Greedy pirate, you can only use an item at a time.
                System.out.println("you have too many items");
                messageSent = true;
            }
            else if(countItem < 1) {
                //What is it you want to pick up?
                System.out.println("you have to pick an item");
                messageSent = true;
            }
            else{
                //this just means you have a valid entry
            }
        }
        else if (actionValue == "look") {
            //place holder as an example for what is next
        }

        if(!messageSent) {
            System.out.println("VALIDATE THINGS");
            //string it together to see if we have a match in our options
            //string all possible entries
            String validString = actionValue + directionValue + itemValue;
            //create a stream list so we can compare the user entry to valid options
            Stream<String> checkIt = Stream.of("movenorth", "movesouth", "pickupcoin");
            //try to match the user entry with our valid options
            match = checkIt.anyMatch(c -> c.equals(validString));
            System.out.println(validString);
            System.out.println(match);
        }



        //if more than 1 direction, tell the user they have too many directions
  /*      else if(countDirections > 1){
            //Are you trying to naviage the Bermuda Triangle?
            //Whoa there sailor, one direction at a time
            //Where do you think you are going, spinning in circles?  Pick one direction.
            System.out.println("you have too many directions");
        }
        //if there is more than 1 item, tell the user they have too many items
        else if (countItem > 1){
            System.out.println("you have too many items");
        }*/
        //otherwise we have valid entries
/*        else{
            System.out.println("VALID THINGS");
            //string it together to see if we have a match in our options
            //string all possible entries
            String validString = actionValue + directionValue + itemValue;
            //create a stream list so we can compare the user entry to valid options
            Stream<String> checkIt = Stream.of("movenorth","movesouth","pickupcoin");
            //try to match the user entry with our valid options
            match = checkIt.anyMatch(c -> c.equals(validString));
            System.out.println(validString);
            System.out.println(match);
        }*/

        //if match is true, we can do something with their entries
        if(match){
            System.out.println("good value");
        }
        //not a good value, we have nothing for that entry
        else{
            System.out.println("bad value");
        }
        //counter
      /*  int countActions = 0;
        while(userInput.hasNext()){
            //System.out.println(userInput.next());

            //set up try catch to see if the word is in our list of actions
            try{
                FindAction actions = new FindAction(Actions.valueOf(userInput.next().toUpperCase()));
                actions.actionIsLike();
                countActions = countActions + 1;
            }
            catch(Exception e){
                System.out.println("not found");
            }
        }
        System.out.println(countActions);
        userInput.close();*/

        //this is the Hazard code
        boolean land = false;
        int hazardFirstKey;
        int hazardSecondKey;
        String fullHazardKey;
        Environmental environmental = new Environmental();
        Human human = new Human();
        double totalCoin = 100.00;
        double takeCoin = 0.00;

        //make the list of hazards
        //in port or near/environmental damage
        environmental.addHazard("00", """
                 Finally gettin' to port!  It been a long 'aul gettin' to this
                 here destination, but it looks like a storm be brewin' off in the distance. The smell o' rain in the 
                 air an' cracks o' lightnin' 'eard loud an' clear, it be gettin' closer. What's that there? Either ye 
                 'ad one too many rums last night, or there be a twister 'eadin' right fer yer ship. Ye better take 
                 cover, it looks like there flyin' cows!  Shiver me timbers! One just clipped yer sail an' damaged 
                 it a bit. Now ye will need a sail repair kit if ye do not want yer  ship to sail any slower!\n"""
                , "1", "0");
        environmental.addHazard("01", """
                 Such a 'ot day at port, sand burnin' yer feet as ye walk along the beach. A nice rain to cool it down 
                 would be jolly, but that there be not goin' to 'appen. Ye even 'ope fer a slight breeze, to at least 
                 'ave some air movement. Yer clothes be stickin' to ye an' yer beard be just too 'ot. Ye decide to get 
                 yer toes wet in the sea an' it be so refreshin'. Ye see people runnin' about in the distance, almost 
                 frantic lookin'. Ye remove yer toes from the water an' 'ead aft to the ship. People be closin' their 
                 'uts an' establishments. There be no one left wanderin' the street but yerself. Ye 'earrr someone tell 
                 ye to take cover. Ye do not know why, it be a beautify day!  No sooner do ye think this here, ye see a 
                 cloud o' tan in the air, it looks like a sand storm be 'eadin' fer ye. Ye run to the ship an' manage to 
                 get there just in time. The storm only lasts a short while, but the damage been done. Sand got all the 
                 way to yer ship an' sadly, the cannon been not covered. Now ye need to find someone to clean the cannon 
                 so it can be used. That there will cost ye at least 100 coin!\n
                 """
                , "2", "1");
        environmental.addHazard("02", """
                It be cool an' cloudy out, quite dark actually. There be rain in the air an' a little bit o' wind. As ye
                move closer to port, the wind starts to pick to the sky. It gets a little chilly so ye grab some rum 
                to warm yer insides. After a few swigs, ye take a few more, a little extra rum ne'er 'urt any gentleman 
                o' fortune. The wind picks to the sky a bit more an' now ye get a little dizzy. Ye don't want the crew 
                to think poorly on ye, so instead o' askin' fer 'elp, ye man the ship yerself, ye be determined to port 
                the ship. Ye try with all yer might, to brin' in the ship straight, but the wind an' waves make it too 
                'ard, not to mention, ye might be seein' 2 docks from ye buzz ye 'ave. Ye feel a jar an' then 'earrr a 
                boom, oh lad, ye 'it the dock!  Now yer ship 'as a 'uge 'ole that there will need fixin' an' ye 'ave 
                to pay 200 coins to repair the dock!  Not a jolly mornin' fer this here gentleman o' fortune.\n"""
                , "3", "3");
        //in port or near/human damage
        human.addHazard("10", """
                It be a nice sunny day, no breeze in the air. Ye decide to take a little trip into the island to find 
                yerself yer own private area to cool down in the water an' take a bath. Ye see a beautiful oasis ahead 
                o' ye an' just yer luck, no one be there!  'Ow be no one grabbin' advantage o' this here blue green water
                surrounded by sand an' greenery?  As ye take a step closer to the water, yer foot sinks!  It be strange
                feeling, a bit gooey an' cold. Ye try to pull yer foot up an' it be stuck. The worse part be, ye lost 
                yer balance an' fell to the ground. Now ye be on all fours an' not able to move. Ye start to sink. Ye 
                yell fer 'elp at the top o' yer lungs an' finally a small blond 'aired lad emerges from the greenery. 
                Ye think ye be goin' to die, 'ow be this here little lad goin' to pull ye out?  Then ye spot the donkey 
                off to the side, eat a little green snack. 'E offers to 'elp pull ye out, but it will cost ye 50 coins. 
                It be do or die at this here point, so ye take the loss o' 50 coins an' get freed.\n""", "10");
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
                Ye wander around the port, lookin' fer somethin' to do. Ye an' yer crew be know to wreak 'avoc an'
                'ave a jolly time!  Ye 'ead into the local establishement. It be dark, dingy an' smells o' musk from the
                dampness in the air. Ye order to the sky some whiskeies an' rums, an' take a seat. Ye was so excited to 'ave
                yer drink, ye failed to notice that there every man in the place been eyein' ye to the sky. Ye sit in a dark corner,
                facin' the people, so ye can watch yer aft. One tall, built man stands up an' points at ye an' then points outside.
                Ye sigh, looks such as yerself will 'ave to fight to 'old yer place in port. Ye walk out o' the barrr an' 'e
                already be grabbin' a swin' at ye, but ye be much smaller an' much quicker. As soon as ye escape, ye
                take a swin' an' get 'im right in the jaw. 'E falls o'er an' be knocked out. Everyone be cheerin' ye on,
                even the men in the barrr. Ye now 'ave earned yer place to drink fer the night!  As ye an' yer crew stagger
                aft to the ship, ye notice somethin' be wrong. Why be the sails open an' why do they look like a shredded
                mess?  It looks like the tall built man been not 'appy about the fight an' while ye celebrated, 'e been busy
                at work. Ye only 'ave one main sail that there be good, but all the others need to be replaced. Ye be
                delayed to yer next port, now that there ye must sail very slow.\n"""
                , "50");
        environmental.addHazard("20", """
                Just sailin' a way at sea, the waves calm fer this here time o' day.  Sunset o' orange, reds an' yellows
                fill this here sky. As ye look off into the distance, admirin' the beauty o' sea, ye notice an odd 
                lookin' movin' toward the boat. Well blow me o'er, where did that there whale come from? Ye almost 'it
                it!  Jolly thin' ye 'ave cat like reflexes an' did a sharp turn to avoid it, but too bad ye ended to 
                the sky 'ittin' a reef!  Looks like ye 'ave a 'ole in yer ship, ye be goin' to 'ave to patch that 
                there 'ole before ye sink!\n""", "1", "0");
        environmental.addHazard("21", """
                Up an' down, the rollin' o' the waves, almost makes ye sea sick. There be somethin' brewin' off into 
                the distance. This here ain't just an ordinary storm, but a storm o' the ages. Ye can see from below it,
                it 'as a massive center, almost like an me good eye lookin' straight into yer soul. Around it, solid 
                white walls an' beyond that there, darkness. The seven seas must be comin' to an end! ye continue on, 
                like a true gentleman o' fortune, nothin' be goin' to avast ye. As the storm gets closer an' closer, 
                the waves become almost unmanageable, pullin' ye wherever they want to take ye. Ye 'ead to the lower 
                level with the crew an' pray ye survive what be next. Ye sit fer what seems like eternity an' soon 
                calmness comes. As ye emerge to the deck, ye can see all the damage 'ad been done. Ye lost at least 
                'alf o' yer sails, an' what be left o' them, flappin' in the wind. it will take ye days, maybe weeks, 
                to get to a port.\n""", "0", "1");
        environmental.addHazard("22", """
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
                port?\n""", "1", "0");
        human.addHazard("30", """
                Ye get up on deck to the sound o' voices, manly voices. Their voices be a bit muffled, an' it be 'ard 
                fer ye to understand, but ye think ye 'eard them say gold. Ye quickly rush to the sky to the deck above,
                leavin' yer crew asleep, an' look o'er the side o' yer boat to find an empty dingy boat floatin' right 
                next to the ship. There be a rope attached to a grapplin' 'ook, which 'as down to Davy Jones' Locker 
                its sharp points into yer boat. There be No men attached to the rope, so where be the men?  As ye turn 
                around ye find a tall, gladiator o' a man standin' before ye. 'E punches ye in the me jolly me good 
                eye an' knocks ye off yer feet. Ye know ye ain't a good fighter, so ye stay down. 'E an' 'is men take 
                off with a quarter o' yer gold. As the crew awake an' come up on deck, they notice yer black me good 
                eye. Ye explain what 'as 'appened, an' the look o' disappointment across their faces. Ye fearrr fer the 
                day they take o'er the ship, such as yerself did. What good be a ship with a Cap'n that there can't fight?\n"""
                , "10");
        human.addHazard("31", """
                Shiver me timbers!  Just as you are looking into the birght blue sky, adminring the beauty of the sea,
                a pirate ship, lookin' like it been through one to many cannon wars, runs ye down. Fifty men, smellin' 
                like a they went on a rum bender an' lookin' like they 'ave not bathed in months, aboard yer ship an' 
                the stench o' sweat fills the air. They each demand ye 'and o'er one item, forcin' ye to lose fifty 
                percent o' yer treasures. Ye feel poorer an' poorer by the day, it be rough bein' the new leader o' a
                crew that there can't fight.""", "25");
        human.addHazard("32", """
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
                sink, but with one sail, it be slow movin'.\n""", "50");

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
        //hazardFirstKey = 0;
        //hazardSecondKey = 2;
        //fullHazardKey = "02";

        //find the right key and its values
        //get the hazard list
        if (hazardFirstKey == 0 || hazardFirstKey == 2) {
            ArrayList<Environmental> environmentalList = environmental.getEnvironmentalList();
            //loop through to find your lookup value
            for(Environmental environmentItems : environmentalList){
                //get the lookup
                Supplier<String> onLine = () -> environmentItems.getLookUp();
                //check if it is our look up
                if(onLine.get().equals(fullHazardKey)){
                    //it is our lookup, so get the message to display to the user
                    Supplier<String> description = () -> environmentItems.getStoryMessage();
                    System.out.println(description.get());
                }
            }
/*            for (int x = 0; x < environmentalList.size(); x++) {
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
            }*/
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
        //create a random number with the range
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random;
    }
}

