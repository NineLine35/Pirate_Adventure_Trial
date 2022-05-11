package GameLoop;

import Hazard.Hazard;
import Inventory.Item;
import Inventory.ItemDatabase;
import Inventory.Trader;
import Player.Player;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Supplier;

    public class IslandLoop extends OpeningLoop{


    public static void launch() throws SQLException {

        // Grab the turns remaining from the Turntracker singleton
        int time_remaining = TurnTracker.getInstance().getTimeLeft();

        //int random = (int) (Math.random() * (1 - 0 + 1) + 0);
        int randomHazard = getRandom(0, 1);
        System.out.println("Hazard or no? " + randomHazard);
        if (randomHazard == 1) {
            //get a random number for hazard
            randomHazard = getRandom(6, 11);

            //create an object for hazard
            Hazard hazard = new Hazard();

            //make the list of hazards
            hazard.addHazard(6, """
                 Finally gettin' to port!  It been a long 'aul gettin' to this
                 here destination, but it looks like a storm be brewin' off in the distance. The smell o' rain in the 
                 air an' cracks o' lightnin' 'eard loud an' clear, it be gettin' closer. What's that there? Either ye 
                 'ad one too many rums last night, or there be a twister 'eadin' right fer yer ship. Ye better take 
                 cover, it looks like there flyin' cows!  Shiver me timbers! One just clipped yer sail an' damaged 
                 it a bit. Now ye will need a sail repair kit if ye do not want yer  ship to sail any slower!\n"""
                    , 0, 25,0);
            hazard.addHazard(7, """
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
                    , 0, 0,100);
            hazard.addHazard(8, """
                It be cool an' cloudy out, quite dark actually. There be rain in the air an' a little bit o' wind. As ye
                move closer to port, the wind starts to pick to the sky. It gets a little chilly so ye grab some rum 
                to warm yer insides. After a few swigs, ye take a few more, a little extra rum ne'er 'urt any gentleman 
                o' fortune. The wind picks to the sky a bit more an' now ye get a little dizzy. Ye don't want the crew 
                to think poorly on ye, so instead o' askin' fer 'elp, ye man the ship yerself, ye be determined to port 
                the ship. Ye try with all yer might, to brin' in the ship straight, but the wind an' waves make it too 
                'ard, not to mention, ye might be seein' 2 docks from ye buzz ye 'ave. Ye feel a jar an' then 'earrr a 
                boom, oh lad, ye 'it the dock!  Now yer ship 'as a 'uge 'ole that there will need fixin' an' ye 'ave 
                to pay 200 coins to repair the dock!  Not a jolly mornin' fer this here gentleman o' fortune.\n"""
                    , 2, 0,200);
            //in port or near/human damage
            hazard.addHazard(9, """
                It be a nice sunny day, no breeze in the air. Ye decide to take a little trip into the island to find 
                yerself yer own private area to cool down in the water an' take a bath. Ye see a beautiful oasis ahead 
                o' ye an' just yer luck, no one be there!  'Ow be no one grabbin' advantage o' this here blue green water
                surrounded by sand an' greenery?  As ye take a step closer to the water, yer foot sinks!  It be strange
                feeling, a bit gooey an' cold. Ye try to pull yer foot up an' it be stuck. The worse part be, ye lost 
                yer balance an' fell to the ground. Now ye be on all fours an' not able to move. Ye start to sink. Ye 
                yell fer 'elp at the top o' yer lungs an' finally a small blond 'aired lad emerges from the greenery. 
                Ye think ye be goin' to die, 'ow be this here little lad goin' to pull ye out?  Then ye spot the donkey 
                off to the side, eat a little green snack. 'E offers to 'elp pull ye out, but it will cost ye 50 coins. 
                It be do or die at this here point, so ye take the loss o' 50 coins an' get freed.\n""",
                    0, 0,50);
            hazard.addHazard(10, """
                It be a 'ot 'umid day as ye roll into port. The smells o' jasmine 
                an' salt in the air. Off in the distance ye 'earrr a woman's voice screamin' 'ysterically.  
                Could it be? A damsel in distress in need o' yer assistance?  Upon approach, ye see the damsel. 
                She 'as the longest dark locks ye 'ave e'er spied, the most beautiful brown doe eyes an' skin that 
                there been kissed by the sun. She be 'angin' from a long sturdy vine, swayin' in the breeze, below 'er 
                an alligator, waitin' fer 'is next meal. Ye scare the alligator off an' rescue the damsel. The next 
                mornin' ye find the damsel stole 75 o' yer coin!  no jolly deed goes unpunished.\n"""
                    , 0,0,75);
            hazard.addHazard(11, """
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
                    , 0,50,0);

            //this is just to display the hazard picked
            System.out.println("the second hazard key" + randomHazard);

            //get the Hazard list
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
                }
            }
        }
        else
        {
            System.out.println("no hazard");
        }

        //Player.getInstance().displayLocation();

        //get the x and y coordinate
        int x = Player.getInstance().getLocation().getRow();
        int y = Player.getInstance().getLocation().getColumn();

        //temporarily print out the coordinates
        //System.out.println(x);
        //System.out.println(y);

        //retrieve data for the island the player is on
        //Inventory.ItemDatabase.retrieveIslandItemData(x,y);

        // Sets the island items list
        List<Item> islandItems = ItemDatabase.retriveIslandItems(x, y);

        // This loops through the items found list and adds items while telling user what they found
        System.out.print("You Have Found ");
        for(Item islandItem : islandItems){
            System.out.print(islandItem.getName() + ",");
            Player.getInstance().AddItem(islandItem, 1);
        }
        System.out.print(" On The Island\n");

        // Sets trader inventory
        Trader trader = new Trader();
        List<Item> traderItems = ItemDatabase.retrieveTraderItems(x, y);

        // Loops through islands trader items and adds to traders list
        for (Item traderItem : traderItems) {
            trader.addItem(traderItem, 1);
        }
        // Outputs trader inventory
        trader.outputInventory();

        // Checks if user wants to trade
        System.out.println("Would You Like To Trade");
        Scanner userInput = new Scanner(System.in);
        String enteredText;
        enteredText = userInput.nextLine();

        // Loop to put user in trading system
        while (!enteredText.toLowerCase().equals("leave") && !enteredText.toLowerCase().equals("no")) {
            // Checks if user wants to trade
            if (enteredText.toLowerCase().equals("trade") || enteredText.toLowerCase().equals("yes")) {
                // Loop for buying and selling
                while (!enteredText.toLowerCase().equals("cancel")) {
                    // Checks what user wants to do
                    System.out.println("Would you like to BUY, SELL or CANCEL");
                    enteredText = userInput.nextLine();
                    // User wants to buy items
                    if (enteredText.toLowerCase().equals("buy")) {
                        // Outputs the traders inventory
                        trader.outputInventory();
                        // User types what they want to buy
                        System.out.println("What item would you like to buy");
                        enteredText = userInput.nextLine();
                        // Checks if trader has the item
                        Item buyItem = trader.findItemInInventory(enteredText);
                        // Checks if the item typed was a valid item
                        if (buyItem != null){
                            // Makes sure user has enough money
                            if (Player.getInstance().getChest() > buyItem.getItemPrice())
                            {
                                // Sells the item from the trader
                                trader.sellItem(buyItem);
                                // Buys the item for the player
                                Player.getInstance().buyItem(buyItem);
                                // Output to user to tell them that the item has been bought
                                System.out.println("You have bought " + buyItem.getName() + " for " + buyItem.getItemPrice() + " coins");
                            } else {
                                // Tells user they do not have enough money
                                System.out.println("You do not have the coin for this");
                            }
                        } else {
                            // Tells user that trader does not have item
                            System.out.println("The trader does not have that item to sell");
                        }
                    } else if (enteredText.toLowerCase().equals("sell")) {
                        // Outputs players inventory
                        Player.getInstance().outputInventory();
                        // Checks what item user wants to sell
                        System.out.println("What item would you like to sell");
                        enteredText = userInput.nextLine();
                        // Checks if player has item
                        Item sellItem = Player.getInstance().findItemInInventory(enteredText);
                        // Checks if item typed is valid
                        if (sellItem != null){
                            // Sells item from player
                            Player.getInstance().sellItem(sellItem);
                            // Buys item for trader
                            trader.buyItem(sellItem);
                            // Tells user the item they have sold
                            System.out.println("You have sold " + sellItem.getName() + " for " + sellItem.getItemPrice() + " coins");
                        } else {
                            // Tells user they do not have the item
                            System.out.println("You do not have that item to sell");
                        }
                    } else if (enteredText.toLowerCase().equals("inventory")) {
                        // Outputs user inventory
                        Player.getInstance().outputInventory();
                    }
                }
            } else if (enteredText.toLowerCase().equals("inventory")){
                // Outputs user inventory
                Player.getInstance().outputInventory();
            } else {
                // User typed something that wasn't valid and gives a hint
                System.out.println("LEAVE if you don't want to trade");
            }
            // Prompts user to type something valid
            System.out.println("Would You Like To LEAVE or TRADE");
            enteredText = userInput.nextLine();
        }
        // Loop is left and the user is told
        System.out.println("You go back to your ship");
    }
}
