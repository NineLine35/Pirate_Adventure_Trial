package Player;

import Ship.Ship;
import Ship.ShipBuilder;

public class Player {
    private int chest;      //Holds gold
    private String name;  // Player name
    private Ship ship; // Ship class placeholder


    //Method to set the player ship class and assign a name
    public void assignShip(String type, String shipName){
        ship = ShipBuilder.getShip(type);
        ship.setShipName(shipName);
    }
}
