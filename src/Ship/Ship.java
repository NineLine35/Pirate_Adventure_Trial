package Ship;

import Inventory.Item;
import Inventory.ItemTypes;

import java.util.Random;

public abstract class Ship {

    private int hullHitPoints;
    private int maxHullHitPoints;
    private int cargoCapacity;
    private int sailDistance;
    private int sailHealth = 100;  //Setting general health of sails as a percentage of full health
    private final int maxSailHealth = 100;
    private boolean isDestroyed = false;
    private String shipName;
    private int fireDamage = 0;


    public Ship(int hullHitPoints, int cargoCapacity, int sailDistance) {
        this.hullHitPoints = hullHitPoints;
        this.maxHullHitPoints = hullHitPoints;
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

    public int getMaxHullHitPoints() {
        return maxHullHitPoints;
    }

    public int getFireDamage() {
        return fireDamage;
    }

    public void setFireDamage(int fireDamage) {
        this.fireDamage = fireDamage;
    }

    /**
     * Method to remove an "x" amount of hit points from the hull due to damage, including fire damage
     */
    public void hullDamage(int x){
        setHullHitPoints(hullHitPoints - (x + (fireDamage/2)));

    }

    /**
     * Method to remove an "x" amount of hit points from the sail due to damage
     */
    public void sailDamage(double x){
        setSailHealth((int) (sailHealth - x));
    }

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

    /**
     * Simple method to calculate a fire chance on the ship, and if true set random damage value
     * @return true if a fire breaks out
     */
    public boolean fireChance(int spread){
        Random rand = new Random();
        if(rand.nextInt(10) > 6){
            setFireDamage((rand.nextInt(4)) * spread);
            return true;
        }
        else{
            return false;
        }
    }
}
