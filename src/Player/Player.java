package Player;


import Inventory.Item;
import Inventory.ItemTypes;
import Inventory.PlayerInventory;
import MapFiles.MainMap;
import Ship.Ship;
import Ship.ShipBuilder;

/**
 * Singleton implementation of the player, to ensure there are not more than one player being passed to each aspect
 * of the game.
 */
public class Player {
    private int chest;      //Holds gold
    private String name;  // Player name
    private Ship ship; // Ship class placeholder
    private PlayerInventory inventory;
    private MainMap.Coordinates location = new MainMap.Coordinates(2,2);


    private Player(){
        inventory = new PlayerInventory();
    };

    /**
     * Singleton instance of the player
     */
    private static final Player instance = new Player();

    // Getters and setters
    public static Player getInstance() {return instance;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public Ship getShip(){return ship;};

    public MainMap.Coordinates getLocation() {
        return location;
    }

    public void setLocation(MainMap.Coordinates location) {
        this.location = location;
    }


    /**
     * Method to set the player ship class and assign a name
     */
    public void assignShip(String type, String shipName){
        ship = ShipBuilder.getShip(type);
        ship.setShipName(shipName);
    }

    /**
     * Uses the repair item specified by the user
     * @param itemName
     */
    public void useRepairItem(String itemName){
        // Checks if user has item
        Item item = inventory.findItem(itemName);
        // User does not have item
        if (item == null){
            System.out.println("You Do Not Have That Item To Use Sailor");
        } else {
            // Checks if item is a repair item
            if (item.getItemType().toLowerCase().equals("repair")){
                // Repairs the ship
                ship.repairShip(item);
                // Checks if sail is not full health in order to not get rid of item without repairing
                if (ship.getSailHealth() < ship.getMaxSailHealth() || ship.getHullHitPoints() < ship.getMaxHullHitPoints())
                {
                    // Uses the repair item from inventory list
                    inventory.useRepairItem(item);
                }
            } else {
                // Tells user it is not a repair item
                System.out.println("This item cannot repair a ship");
            }
        }
    }

    /**
     * Adds item to player inventory
     * @param item
     * @param quantity
     */
    public void AddItem(Item item, int quantity){
        // Adds item to list
        inventory.addItem(item, quantity);
    }

    /**
     * Sells item from player inventory and gives player the coins
     * @param item
     */
    public void sellItem(Item item) {
        // Sells the item from inventory
        inventory.sellItem(item);
        // Gives user the coins
        setChest(getChest() + item.getItemPrice());
    }

    /**
     * Buys item for player inventory
     * @param item
     */
    public void buyItem(Item item){
        // Checks if user has the coin to buy the item
        if (getChest() >= item.getItemPrice()){
            // Adds item to inventory
            this.inventory.addItem(item, 1);
            // Updates chest
            this.setChest(this.getChest() - item.getItemPrice());
        } else {
            // Tells user they do not have the coin to buy item
            System.out.println("You cannot buy an item if you don't have enough coins");
        }
    }

    /**
     * Outputs the users inventory and coin
     */
    public void outputInventory(){
        System.out.println("You Have " + this.getChest() + " Coins In Your Chest");
        inventory.outputInventory();
    }

    /**
     * Finds the item in inventory
     * @param itemName
     * @return
     */
    public Item findItemInInventory(String itemName) {
        Item item = inventory.findItem(itemName);
        return item;
    }

    /**
     * Gets the current location of the ship
     */
    public void displayLocation(){
        int x = getLocation().getRow();
        int y = getLocation().getColumn();
        System.out.println("Ye be located at coordinate " + x + "," + y);
    }

}
