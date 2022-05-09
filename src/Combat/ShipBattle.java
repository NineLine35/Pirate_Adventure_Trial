package Combat;
import Player.Player;
import Ship.Ship;
import java.util.Random;
import java.util.Scanner;
import java.util.function.*;

public class ShipBattle {

    static Ship playerShip = Player.getInstance().getShip();

    //Simple turn counter to be used in the runtime
    public static int turnCounter = 1;



    public static void battle(){

        //DEBUG
        System.out.println("Battle Code");

        int enemyShipHP = randomRoll.get();
        boolean enemyShipArmed = false;

        if(randomRoll.get() > 5){enemyShipArmed=true;}else{enemyShipArmed=false;}

        int enemyDistance = 5;

        returnText.accept("Argh!  We are closing on the enemy.  They are " + enemyDistance +
                "00 yarrrds away. We can shoot captain, or we can close with their ship.");

        Scanner userInput = new Scanner(System.in);


        while(userInput.hasNext()) {

            if(enemyDistance > 5){
                returnText.accept("We lost the ship captain!  She lives to fight another day, but so do we!");
                return;
            }

            String enteredText = userInput.nextLine();

            if(enteredText.toLowerCase().contains("close")){

                int chance = randomRoll.get();

                if(chance >= 4 && chance < 10 && playerShip.getSailHealth() > 50){
                    enemyDistance--;
                    returnText.accept("A-HA!  We are catch'n up captain!");
                    if (!enemyCannonFire(enemyShipArmed, enemyDistance,userInput)){enemyDistance++;};
                    returnText.accept("We are now " + enemyDistance + "00 yards away!");
                }
                else if(chance < 4 || playerShip.getSailHealth() < 50){
                    enemyDistance++;
                    if(playerShip.getSailHealth() < 50){
                        returnText.accept("It looks like our sails are tore up!  We are losing wind!");
                    }
                    if (!enemyCannonFire(enemyShipArmed, enemyDistance,userInput)){enemyDistance++;};
                    returnText.accept("We are now " + enemyDistance + "00 yards away!");
                }
                else {
                    returnText.accept("We are making no ground on the ship captain");
                }

            }
            if(enteredText.toLowerCase().contains("fire")){
                //Cannon firing code
            }

        }

    }

    public static boolean enemyCannonFire(boolean enemyArmed, int distance,Scanner userInput){

        returnText.accept("The enemy ship turns and takes aim. Give us your best shot, you varmin!");

       double result = (double) (randomRoll.get() / distance);

        if(result >1.5){
            incomingFire(userInput);
            return true;
        }
        else if (result > 1.2 && result <= 1.5){
            returnText.accept("The cannon balls graze the ship. In impact of the cannon balls in the water" +
                    "cause the crew to take shelter and leave their posts.  Cowards!  Get back to your stations!" +
                    "The enemy has used this to their advantage and have slipped further away");

            return false;

        }
        else {
            returnText.accept("A complete miss!! Argh!  We press on!");
            return true;
        }

    }


    public static void incomingFire(Scanner userInput) {


        returnText.accept("""
                \nYou see the cannon muzzle flashes in the distance, then all is quiet.  
                The crew looks around slowly and waits.  The sound of the waves slapping the ship seems deafening
                then a WOOOOSH and without warning the loud crash of impact
                and shutters of splintered wood throw you to the deck.\n""");


        // Game loop open while input stream is active
           String enteredText = userInput.nextLine();

            while (playerShip.getHullHitPoints() > 0) {

                if (enteredText.toLowerCase().contains("exit")) {
                    returnText.accept("Good bye!");
                    System.exit(0);
                } else if (enteredText.toLowerCase().contains("damage report")) {

                    int damageCalc = cannonDamage();

                    // Calculate the chance of a fire breaking out on the ship and if so, calculate the damage
                    // based on the fireChance method, multiplied by the fireSpread Unary Function.
                    // With passing time (TurnCounter) the fire could cause more damage if not put out.
                    boolean fireChance = playerShip.fireChance(fireSpread.apply(turnCounter));

                    returnText.accept("\nCaptain! The ship took " + damageCalc + " units of hull damage!!");

                    // Apply the damage to the ship - via Consumer interface
                    shipHit.accept(playerShip, damageCalc);

                    // Calculate sail damage - via Function interface & Apply the damage to the sail via Consumer interface
                    sailHit.accept(playerShip, sailDamage.apply(Double.valueOf(playerShip.getSailHealth()), damageCalc));


                    returnText.accept("The ship has " + playerShip.getHullHitPoints() + "  hull points remaining!\n" +
                            "And the sails have also been hit! They only have " + playerShip.getSailHealth() + " percent life left! Argh!\n");

                    if (damageWarning.test(playerShip)) {
                        returnText.accept("You have less than half your hull remaining!!\n");
                    }

                    if (playerShip.getFireDamage() > 1) {
                        returnText.accept("The ship has caught fire captain!! The fire is currently at " + playerShip.getFireDamage()
                                + " and rising!\n");
                    }

                    return;


                } else {
                    returnText.accept("We don't understand you captain!  For this program you have EXACTLY two choices,\n" +
                            "'EXIT' &\n'DAMAGE REPORT'\n");
                }

                // Increment the turn - This is a VERY small example of a time passing mechanic used just for this assignment
                turnCounter++;


            }

            returnText.accept("Your ship has sunk.....game-over!");
            System.exit(0);
        }



    // Supplier Example
    // Assign the random function to variable randomRoll, allowing this variable to auto supply a number when needed
    public static Supplier<Integer> randomRoll = () -> new Random().nextInt(9) + 1;

    /**
     * Method utilizing Supplier randomRoll to generate a random hull & sail hit number
     * @return random hit number
     */
    public static int cannonDamage(){

        return randomRoll.get();

    };

   /* Consumer & BiConsumer Examples
   Created a simple print variable with returnText, and then created a biconsumer that accepts a Ship class and
   int.  The biconsumer variable will then use these variable to run the "hullDamage" method found in the Ship class
   to remove a set amount of damage from the ship hull.  Using the BiFunction sailDamage below, the "sailHit" will
    act like the "shipHit" variable and will set the new sail health after a cannon hit*/

    public static Consumer<String> returnText = x -> System.out.println(x);
    public static BiConsumer<Ship, Integer> shipHit = (x, y) -> x.hullDamage(y);
    public static BiConsumer<Ship, Double> sailHit = (x,y) -> x.sailDamage(y);


    //Predicate example
    // Predicate that will check if the current hull damage is at 50% of the max hull hit points
    public static Predicate<Ship> damageWarning = x -> x.getHullHitPoints() < (x.getMaxHullHitPoints() * 0.5);


    // BiFunction example
    // Function will accept a double for health of the sail remaining (as a percent/double) and the damage from the
    // cannon hit.  It will then simply divide and return the percentage hit the sails took
    public static BiFunction<Double, Integer, Double> sailDamage = (x, y) -> Double.valueOf((x / y ));

    // Unary Example
    // Function can be passed into the fireChance Ship method to simulate fire spread by applying a turncounter to the
    // x method and returning an integer for damage.
    public static UnaryOperator<Integer> fireSpread = x -> x * 2;


}


