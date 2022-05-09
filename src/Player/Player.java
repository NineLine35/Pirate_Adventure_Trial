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

    private static final Player instance = new Player();

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


    //Method to set the player ship class and assign a name
    public void assignShip(String type, String shipName){
        ship = ShipBuilder.getShip(type);
        ship.setShipName(shipName);
    }

    public void useRepairItem(String itemName){
        Item item = inventory.findItem(itemName);
        if (item == null){
            System.out.println("You Do Not Have That Item To Use Sailor");
        } else {
            if (item.getItemType().toLowerCase().equals("repair")){
                ship.repairShip(item);
                if (ship.getSailHealth() < ship.getMaxSailHealth() || ship.getHullHitPoints() < ship.getMaxHullHitPoints())
                {
                    inventory.useRepairItem(item);
                }
            } else {
                System.out.println("This item cannot repair a ship");
            }
        }
    }

    public void AddItem(Item item, int quantity){
        inventory.addItem(item, quantity);
    }

    public void sellItem(Item item) {
        inventory.sellItem(item);
        setChest(getChest() + item.getItemPrice());
    }

    public void buyItem(Item item){
        if (getChest() >= item.getItemPrice()){
            this.inventory.addItem(item, 1);
        } else {
            System.out.println("You cannot buy an item if you don't have enough coins");
        }
    }

    public void outputInventory(){
        System.out.println("You Have " + this.getChest() + " Coins In Your Chest");
        inventory.outputInventory();
    }

    public Item findItemInInventory(String itemName) {
        Item item = inventory.findItem(itemName);
        return item;
    }

    public void displayLocation(){
        int x = getLocation().getRow();
        int y = getLocation().getColumn();
        System.out.println("Ye be located at coordinate " + x + "," + y);
    }

}
