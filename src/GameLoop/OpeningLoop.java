package GameLoop;

import Inventory.Item;
import Inventory.ItemDatabase;
import Inventory.ItemTypes;
import Inventory.Trader;
import MapFiles.*;
import Player.Player;
import Player.HighScoreTracker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class OpeningLoop {

     /** launch map and game
     * @param playmap
     * @throws SQLException
     * @throws IOException
     **/

    public static void launch(Landmass[][] playmap) throws SQLException, IOException {


        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();
        System.out.println();

        System.out.println("*********************************\n");

        System.out.println(
                """
                        You awaken to the sound of distant gulls and the creaking of worn planks as the ship slowly sways in the 
                        rolling ocean. Your head throbs after a night of rum and celebration of a mutiny that was long overdue.  
                        Oh well, tis the life of a pirate.  But, now what to do?  You didn't think that far ahead, eyes always on the plunder
                        not the planning.  But now as you walk up to the bridge, with the eyes of your crew bearing down on you
                        you realize that it is time to take charge.  You know you have 10 days before the English Navy will be
                        sailing into the area.
                        
                        10 days to trade and plunder as much as possible.  Let's have at it!
                                                
                        What is yar name pirate??\n"""

        );

        // Receive player input and parse it
        Scanner userInput = new Scanner(System.in);

        // Store player input in variable
        String enteredText = userInput.nextLine();

        //set the player name entered
        Player.getInstance().setName(enteredText);

        //print out message to player
        System.out.println("\nNice to meet ya, " + Player.getInstance().getName());
        System.out.println("This rickety ship isn't much, in fact it is just a basic Sloop.  But we trust ya know" +
                " how to make us rich.  Or if not......maybe you end up like the last captain.  In fact!  We will" +
                "give you 10 days, argh, 10 days total to make us some coin captain.\n");

        //assign initial items to the player and trade

        List<Item> islandItems = ItemDatabase.retrieveIslandItems(2, 2);
        System.out.print("You Have Found ");
        for(Item islandItem : islandItems){
            System.out.print(islandItem.getName() + ",");
            Player.getInstance().AddItem(islandItem, 1);
        }


        String daysOrDay;
        //while there are turns left
        while (TurnTracker.getInstance().getTimeLeft() > 0) {
            //if turn is 1, change day to days
            if(TurnTracker.getInstance().getTimeLeft() == 1) {
                daysOrDay = "day";
            }
            //use days
            else{
                daysOrDay = "days";
            }

            //display message to user prompting them to do something and how many days they have left
            System.out.println("\nSo Captain " + Player.getInstance().getName() + " what now?  You have " + TurnTracker.getInstance().getTimeLeft() + " " + daysOrDay + " left.  If you are unsure, type help and you can see your options.");

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
                    //have a valid action, this part is needed for where am i
                    OpenLoopOptions getEntry = OpenLoopOptions.valueOf(s1.toUpperCase());

                    //string together the entry values, this will find an order of statements that are allowable
                    entryValue = Concatenate(entryValue,s1.toString(),true);

                    //increase the counter
                    countEntries++;
                }
                //not a valid action, maybe it is a direction
                catch (Exception e) {
                }
            }

            //System.out.println(countActions);
            //if the count of actions is greater than 1 and not where am i, tell the user that they have too many actions
            if (countEntries > 1 && !entryValue.equals("where am i")) {
                //System.out.println("you have too many entries");
                responses();
                //goodValue = false;
            }
            else {
                //if exit, exit the came
                if (entryValue.equals("exit")) {
                    System.out.println("Good bye!");

                    //10.2 close database

                    HighScoreTracker.writeHighScores();

                    ItemDatabase.closeDatabaseConnection();
                    System.exit(0);
                }
                //if entered map
                else if (entryValue.equals("map")) {
                    //DEBUG print out map to console
                    System.out.println("______MAP______");
                    System.out.println(Arrays.deepToString(playmap).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
                    System.out.println("_______________");
                    Player.getInstance().displayLocation();
                }
                //if entered where am i
                else if (entryValue.equals("where am i")) {
                    int x = Player.getInstance().getLocation().getRow();
                    int y = Player.getInstance().getLocation().getColumn();

                    //in open water or land
                    if (playmap[x][y] instanceof OpenWater) {
                        System.out.println("Ye in the open water captain!!");
                    } else if (playmap[x][y] instanceof Island) {
                        System.out.println("Seems we be on land....yuck");
                    }
                }
                //if entered sail
                // 2.5 Object comparison
                else if (entryValue.equals("sail")) {
                    //goodValue = true;
                    System.out.println("Which direction shall we sail captain??");

                    String userSelection = userInput.nextLine().toUpperCase();

                    try {
                        //move the player to location
                        Player.getInstance().setLocation(MainMap.movePlayer(Player.getInstance().getLocation().getRow(),
                                Player.getInstance().getLocation().getColumn(), Direction.valueOf(userSelection), playmap));
                         TurnTracker.getInstance().endOfTurn();

                    } catch (Exception e) {
                        //check the entry to see if we are just going off the map or if the user had a bad input
                        CheckMapMove(userSelection,entryValue);
                    }
                }
                //if entered look
                else if (entryValue.equals("look")){

                    //display message to user to ask for a direction to look
                    System.out.println("Which direction are ye lookin??");

                    //user input
                    String userSelection = userInput.nextLine().toUpperCase();

                    try{
                        //look in a direction
                        MainMap.lgView(Player.getInstance().getLocation().getRow(),Player.getInstance().getLocation().getColumn(), Direction.valueOf(userSelection),playmap);
                    } catch (Exception e){
                        //check the entry to see if we are just going off the map or if the user had a bad input
                        CheckMapMove(userSelection,entryValue);
                    }
                }
                //if entered help
                else if (entryValue.equals("help")){
                    help();
                }
                //if entered status
                else if (entryValue.equals("status")){
                    System.out.println("Our ship floats, don't it! If ye need more info, fine here you go");
                    System.out.println("The hull has " + Player.getInstance().getShip().getHullHitPoints() + " remaining");
                    System.out.println("And the sails....take a look.  They are at " + Player.getInstance().getShip().getSailHealth() + "%\n");
                }
                //if entered date
                else if (entryValue.equals("date")){
                    System.out.println("We have " + TurnTracker.getInstance().getTimeLeft() + " days before the English arrive");

                }


                else if (entryValue.equals("debugBattle")){

                    Combat.ShipBattle.battle();

                }
                //check the inventory
                else if (entryValue.equals("inventory")){
                    Player.getInstance().outputInventory();
                }
                //if the player wants to use something
                else if (entryValue.equals("use")){
                    System.out.println("Which item would you like to use");

                    //user input
                    String userSelection = userInput.nextLine().toLowerCase();

                    //repair the item
                    try{
                        Player.getInstance().useRepairItem(userSelection);
                    } catch (Exception e) {
                        System.out.println("This item cannot repair a ship");
                    }
                }
                //if got here, there is not a valid command
                else {
                    //goodValue = false;
                    responses();
                }

            }
        }
        //out of 10 days
        System.out.println("out of turns");
        //10.2 close database
        ItemDatabase.closeDatabaseConnection();
        }
        //}


    /**
     * method for randoms with a min and max
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        //create a random number with the range
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random;
    }

    /**
     * method for cleaning up the string to tokenize
     * @param userInput2
     * @return
     */
    public static StringTokenizer getToken(Scanner userInput2)
    {
        //get the user input next line
        String s = userInput2.nextLine();

        //remove all special characters except whitespace
        s = s.replaceAll("[^\\w\\s\\d]","");


        //tokenize the input using a space delimiter
        StringTokenizer st = new StringTokenizer(s, " ");//" " is the delimiter here.

        //return the string
        return st;
    }

    /**
     * random responses when player enters invalid comments
     */
    public static void responses(){
        //int for a random response
        int getResponse = getRandom(0,5);

        //convert the int to a string
        String convertRandom = Integer.toString(getResponse);

        //go to the resource file and pull a random response
        //5.3 - use or a resource bundle
        Locale en = new Locale("en");
        ResourceBundle rb = ResourceBundle.getBundle("ResponsesTry",en);

        //print the response
        System.out.println(rb.getString(convertRandom));
    }

    /**
     * help menu, accessible with the word help
     */
    public static void help(){
        System.out.println('\n' + "---------------------------------------------------------------" + '\n' + "HELP MENU"
                + '\n' + "---------------------------------------------------------------");

        //set up print for actions and statements
        System.out.println('\n' + "--------------------" + '\n' + "Actions/Statements" + '\n' + "--------------------");

        //get the enum for open loop options
        EnumSet<OpenLoopOptions> enumSet = EnumSet.allOf(OpenLoopOptions.class);
        String makeString = "";

        //loop through and print, but need to concatenate where am i
        for (OpenLoopOptions s : enumSet) {
            //if words where or am or i, send to concatenate
            if (s.toString() == "WHERE" || s.toString() == "AM" || s.toString() == "I"){
                //string together to make where am I
                makeString = Concatenate(makeString,s.toString(), true);

                //when it says where am i, print it
                if (makeString.equals("WHERE AM I")){
                    System.out.println(makeString);
                }
            }
            else
            {
                //print the enum action
                System.out.println(s);
            }
        }

        //set up the directions
        System.out.println('\n' + "--------------------" + '\n' + "Directions" + '\n' + "--------------------");

        //print a list of directions from the enum
        //3.5 - foreach
        EnumSet.allOf(Direction.class)
                .forEach(direction->System.out.println(direction));

        //set up items
        System.out.println('\n' + "--------------------" + '\n' + "Items" + '\n' + "--------------------");

        //loop and print the items in the enum
        //3.5 - foreach
        EnumSet.allOf(ItemTypes.class)
                .forEach(item->System.out.println(item));

        //ask the player what they want to do next
        System.out.println('\n' + "---------------------------------------------------------------" + '\n' + ""
                + '\n' + "---------------------------------------------------------------");
        System.out.println('\n' + "What would you like to do?");
    }

    /**
     * Method to concatenate strings
     * @param current = the current string you have
     * @param add = what you want to add
     * @param spaceBetween = do you want space between the words
     * @return
     */

    public static String Concatenate (String current, String add, boolean spaceBetween){
        //create a binary function for concatenating
        //4.2 - BiFunction
        //4.1 and 2.1 lambda
        BiFunction<String, String, String> s1 = (string,toAdd) ->string.concat(toAdd);

        //if the current remark is not empty and you choose to do a space or no space
        if(current != "" && spaceBetween){
            current = s1.apply(current + " ",add.toString());
        }
        else{
            current = s1.apply(current,add.toString());
        }

        //return the new current string
        return current;
    }

    /**
     * check if the map movement is valid and out of space to move, or bad value
     * @param userSelection
     * @param entryValue
     */
    public static void CheckMapMove(String userSelection,String entryValue){
        //4.5 use of stream
        //create a stream to determine if the entered value is good, but we just ran out of map
        Stream<String> checkIt = Stream.of("NORTH", "SOUTH", "EAST","WEST", "NORTHEAST", "SOUTHEAST","NORTHWEST", "SOUTHWEST");
        //try to match the user entry with our valid options
        //4.1 and 2.1 lambda
        boolean match = checkIt.anyMatch(c -> c.equals(userSelection));
        if(match){
            //we have a good direction, just can't move off of map
            System.out.println("Whoa, this Pirate Earth is flat, keep it on the map! " + entryValue.substring(0, 1).toUpperCase() + entryValue.substring(1) + " a different direction.");
        }
        //bad value
        else {
            System.out.println("Not a valid direction sailor!");
        }
    }
}
