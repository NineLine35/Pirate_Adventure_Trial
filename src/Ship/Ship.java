package Ship;

abstract class Ship {

    private int hitPoints;
    private int cargoCapacity;
    private int sailDistance;
    private boolean isDestroyed = false;


    public Ship(int hitPoints, int cargoCapacity, int sailDistance) {
        this.hitPoints = hitPoints;
        this.cargoCapacity = cargoCapacity;
        this.sailDistance = sailDistance;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
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
}
