package Hazard;

public class HitAccount {
    private int hitAmount = 0;
    private HitAccount() {};

    private static final HitAccount instance = new HitAccount();

    public static HitAccount getInstance() {
        return instance;
    }

    public synchronized boolean addHit(int amount) {
        hitAmount += amount;
        if(hitAmount < 3) return false;
        return true;
    }

    public synchronized boolean removeHit(int amount) {
        if(amount > hitAmount) return false;
        hitAmount -= amount;
        return true;
    }

    public synchronized int getBalance() {
        return hitAmount;
    }
}
