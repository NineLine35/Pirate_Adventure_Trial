package Player;


import Inventory.Item;
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


    private Player(){};

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
        Item item;
        item = inventory.findItem(itemName);
        if (item == null){
            System.out.println("You Do Not Have That Item To Use Sailor");
        } else {
            ship.repairShip(item);
            inventory.useRepairItem(item);
        }
    }

    public void displayLocation(){
        int x = getLocation().getRow();
        int y = getLocation().getColumn();
        System.out.println("Ye be located at coordinate " + x + "," + y);
    }

}
