package Player;

import Inventory.Item;
import Inventory.PlayerInventory;
import Inventory.RepairItem;
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

    //Method to set the player ship class and assign a name
    public void assignShip(String type, String shipName){
        ship = ShipBuilder.getShip(type);
        ship.setShipName(shipName);
    }

    public void useRepairItem(String itemName){
        RepairItem repairItem;
        repairItem = inventory.findRepairItem(itemName);
        if (repairItem == null){
            System.out.println("You Do Not Have That Item To Use Sailor");
        } else {
            ship.repairShip(repairItem);
            inventory.useRepairItem(repairItem);
            System.out.println("You Have Repaired Your " + repairItem.getRepairType() + " To Full Health");
        }
    }
}
