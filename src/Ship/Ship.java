package Ship;

import Inventory.Item;
import Inventory.ItemTypes;

public abstract class Ship {

    private int hullHitPoints;
    private int maxHullHitPoints;
    private int cargoCapacity;
    private int sailDistance;
    private int sailHealth = 100;  //Setting general health of sails as a percentage of full health
    private final int maxSailHealth = 100;
    private boolean isDestroyed = false;
    private String shipName;


    public Ship(int hullHitPointshitPoints, int cargoCapacity, int sailDistance) {
        this.hullHitPoints = hullHitPointshitPoints;
        this.maxHullHitPoints = hullHitPointshitPoints;
        this.cargoCapacity = cargoCapacity;
        this.sailDistance = sailDistance;
    }

    // Getters and Setters
    public int getHullHitPoints() {
        return hullHitPoints;
    }

    public void setHullHitPoints(int hullHitPoints) {
        this.hullHitPoints = hullHitPoints;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getSailDistance() {
        return sailDistance;
    }

    public void setSailDistance(int sailDistance) {
        this.sailDistance = sailDistance;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getSailHealth() { return  this.sailHealth; }

    public void setSailHealth(int sailHealth){ this.sailHealth = sailHealth; }

    public void repairShip(Item item){
        if (item.getName() == ItemTypes.PLANK_NAILS.toString()){
            setHullHitPoints(maxHullHitPoints);
            System.out.println("You Have Repaired Your Hull To Full Health");
        }
        else if (item.getName() == ItemTypes.PATCHING_KIT.toString()){
            setSailHealth(maxSailHealth);
            System.out.println("You Have Repaired Your Sail To Full Health");
        }
    }
}
