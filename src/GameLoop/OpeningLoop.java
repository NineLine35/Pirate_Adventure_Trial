package GameLoop;

import Hazard.Actions;
import Hazard.Item;
import Hazard.StartTest;
import Inventory.ItemDatabase;
import Inventory.ItemTypes;
import MapFiles.*;
import Player.Player;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public class OpeningLoop {


    public static void launch(Landmass[][] playmap) throws SQLException {

        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();

        System.out.println();
        System.out.println("Welcome to the Pirate Game Trial!\n" +
                "*********************************\n");

        System.out.println(
                """
                        You awaken to the sound of distant gulls and the creaking of worn planks as the ship slowly sways in the 
                        rolling ocean. Your head throbs after a night of rum and celebration of a mutiny that was long overdue.  
                        Oh well, tis the life of a pirate.  But, now what to do?  You didn't think that far ahead, eyes always on the plunder
                        not the planning.  But now as you walk up to the bridge, with the eyes of your crew bearing down on you
                        you realize that it is time to take charge.
                                                
                        What is yar name pirate??\n"""

        );

        // Receive user input and parse it
        Scanner userInput = new Scanner(System.in);

        // Store user input in variable
        String enteredText = userInput.nextLine();

        Player.getInstance().setName(enteredText);

        System.out.println("\nNice to meet ya, " + Player.getInstance().getName());
        System.out.println("This rickety ship isn't much, in fact it is just a basic Sloop.  But we trust you know" +
                " how to make us rich.  Or if not......maybe you end up like the last captain.  In fact!  We will" +
                "give you 10 days, argh, 10 to make us some coin captain.\n");

        Inventory.ItemDatabase.retrieveIslandItemData(2,2);
        System.out.println("So Captain " + Player.getInstance().getName() + " what now?");
        boolean goodValue = false;
        while (!goodValue) {
            //my adds
            //set up scanner to read
            Scanner userInput2 = new Scanner(System.in);

            //go to method to tokenize
            StringTokenizer st = getToken(userInput2);

            //default variables to track how many of ENUM entries there are
            int countEntries = 0;

            //default variables to get the values back from the ENUMs
            String entryValue = "";

            //set up loop to cycle through each item in the entry string
            while (st.hasMoreTokens()) {
                //grab next
                String s1 = st.nextToken();
                //try to see if there is a valid action
                try {
                    //have a valid action
                    //FindAction actions = new FindAction(Actions.valueOf(s1.toUpperCase()));
                    //actionValue = actions.actionIsLike();
                    OpenLoopOptions getEntry = OpenLoopOptions.valueOf(s1.toUpperCase());
                    if(entryValue != ""){
                        entryValue = entryValue + " " + getEntry.getSendEntry();
                    }
                    else
                    {
                        entryValue = getEntry.getSendEntry();
                    }
                    //increase the action count by 1
                    countEntries++;
                }
                //not a valid action, maybe it is a direction
                catch (Exception e) {
                }
            }

            //System.out.println(countActions);
            //iof the count of actions is greater than 1, tell the user that they have too many actions
            if (countEntries > 1 && !entryValue.equals("where am i")) {
                //System.out.println("you have too many entries");
                responses();
                goodValue = false;
            }
            else {

                // Game loop open while input stream is active
                //while (userInput.hasNext()) {
                //while (!goodValue) {
                //enteredText = userInput.nextLine();
                //System.out.println(entryValue);
                //if (enteredText.toLowerCase().contains("exit")) {
                if (entryValue.equals("exit")) {
                    System.out.println("Good bye!");
                    ItemDatabase.closeDatabaseConnection();
                    System.exit(0);
                } //else if (enteredText.toLowerCase().contains("map")) {
                else if (entryValue.equals("map")) {
                    //goodValue = true;
                    //DEBUG print out map to console
                    System.out.println("______MAP______");
                    System.out.println(Arrays.deepToString(playmap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
                    System.out.println("_______________");
                    Player.getInstance().displayLocation();
                }// else if (enteredText.toLowerCase().contains("where am i")) {
                else if (entryValue.equals("where am i")) {
                    //goodValue = true;
                    int x = Player.getInstance().getLocation().getRow();
                    int y = Player.getInstance().getLocation().getColumn();

                    if (playmap[x][y] instanceof OpenWater) {
                        System.out.println("Ye in the open water captain!!");
                    } else if (playmap[x][y] instanceof Island) {
                        System.out.println("Seems we be on land....yuck");
                    }
                } //else if (enteredText.toLowerCase().contains("sail")) {
                else if (entryValue.equals("sail")) {
                    //goodValue = true;
                    System.out.println("Which direction shall we sail captain??");

                    String userSelection = userInput.nextLine().toUpperCase();

                    try {
                        Player.getInstance().setLocation(MainMap.movePlayer(Player.getInstance().getLocation().getRow(),
                                Player.getInstance().getLocation().getColumn(), Direction.valueOf(userSelection), playmap));
                    } catch (Exception e) {
                        System.out.println("Not a valid direction sailor!");
                    }
                }
                else if (entryValue.equals("look")){

                    System.out.println("Which direction are ye lookin??");

                    String userSelection = userInput.nextLine().toUpperCase();

                    try{
                    MainMap.lgView(Player.getInstance().getLocation().getRow(),Player.getInstance().getLocation().getColumn(), Direction.valueOf(userSelection),playmap);
                    } catch (Exception e){
                        System.out.println("Not a valid direction sailor!");
                    }
                }
                else if (entryValue.equals("help")){
                    help();
                }
                else if (entryValue.equals("status")){
                    System.out.println("Our ship floats, don't it! If ye need more info, fine here you go");
                    System.out.println("The hull has " + Player.getInstance().getShip().getHullHitPoints() + " remaining");
                    System.out.println("And the sails....take a look.  They are at " + Player.getInstance().getShip().getSailHealth() + "%\n");

                }

                //TODO REMOVE DEBUG BATTLE ENTRY KEEP IN CODE
                else if (entryValue.equals("debugBattle")){

                    Combat.ShipBattle.incomingFire();

                }

                else {
                    goodValue = false;
                    responses();
                    //System.out.println("Not recognized!  We have not implemented commands.  Try 'EXIT'....\n");
                }

            }
        }

        //}
    }

    //method for randoms with a min and max
    public static int getRandom(int min, int max) {
        //create a random number with the range
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random;
    }

    //method to tokenize
    public static StringTokenizer getToken(Scanner userInput2)
    {
        //get the user input next line
        String s = userInput2.nextLine();

        //s = s.replaceAll("s/[^a-zA-Z0-9]","");
        s = s.replaceAll("[^\\w\\s\\d]","");


        //tokenize the input using a space delimiter
        StringTokenizer st = new StringTokenizer(s, " ");//" " is the delimiter here.
        return st;
    }

    public static void responses(){
        int getResponse = getRandom(0,5);
        String convertRandom = Integer.toString(getResponse);
        Locale en = new Locale("en");
        ResourceBundle rb = ResourceBundle.getBundle("ResponsesTry",en);
        System.out.println(rb.getString(convertRandom));
    }

    public static void help(){

        System.out.println('\n' + "--------------------" + '\n' + "Directions" + '\n' + "--------------------");

        EnumSet.allOf(Direction.class)
                .forEach(direction->System.out.println(direction));


        System.out.println('\n' + "--------------------" + '\n' + "Actions/Statements" + '\n' + "--------------------");
        EnumSet<OpenLoopOptions> enumSet = EnumSet.allOf(OpenLoopOptions.class);
        String makeString = "";
        for (OpenLoopOptions s : enumSet) {
            if (s.toString() == "WHERE" || s.toString() == "AM" || s.toString() == "I"){
                if(makeString == ""){
                    makeString = makeString + s;
                }
                else{
                    makeString = makeString + " " + s;
                }
                if (makeString.equals("WHERE AM I")){
                    System.out.println(makeString);
                }
            }
            else
            {
                System.out.println(s);
            }
        }

        System.out.println('\n' + "--------------------" + '\n' + "Items" + '\n' + "--------------------");

        EnumSet.allOf(ItemTypes.class)
                .forEach(item->System.out.println(item));

        System.out.println('\n' + "--------------------" + '\n' + "What would you like to do?");
    }
}
