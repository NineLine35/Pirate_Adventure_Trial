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
        // Hull Repair Was Used
        if (item.getName().toLowerCase() == ItemTypes.PLANK_AND_NAILS.toString().toLowerCase().replace("_", " ")){
            // Checks if hull has taken damage
            if (this.getHullHitPoints() < maxHullHitPoints){
                // Hull is repaired by one hitpoint
                this.setHullHitPoints(this.getHullHitPoints() + 1);
                System.out.println("Your hull has been repaired to " + this.getHullHitPoints() + " hitpoints");
            }
            else {
                // User is told hull is alright
                System.out.println("Your hull does not need to be repaired");
            }
        } else if (item.getName().toLowerCase() == ItemTypes.PATCHING_KIT.toString().toLowerCase().replace("_", " ")){
            // Checks if sail is max health
            if (this.getSailHealth() == this.maxSailHealth){
                // User is told sail is full health
                System.out.println("Your sail does not need to be repaired");
            } else {
                // Makes sure sail is greater than 10% less in order to not over repair ship
                if (this.getSailHealth() <= (this.maxSailHealth + 10)) {
                    // Repairs ship by 10
                    this.setSailHealth(this.getSailHealth() + 10);
                } else {
                    // Repairs ship to max as 10% would over repair ship
                    this.setSailHealth(this.maxSailHealth);
                }
                System.out.println("Your sail has been repaired to " + this.getSailHealth() + "%");
            }
        }
    }
}
