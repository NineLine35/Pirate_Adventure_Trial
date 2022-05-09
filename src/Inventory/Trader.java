package Inventory;

/**
 * Class for the individual traders
 */
public class Trader {
    private TraderInventory traderInventory;

    public Trader() {
        traderInventory = new TraderInventory();
    }

    /**
     * Adds item to traders inventory
     * @param item
     * @param quantity
     */
    public void addItem(Item item, int quantity){
        traderInventory.addItem(item, quantity);
    }

    /**
     * Sells item from traders inventory
     * @param item
     */
    public void sellItem(Item item) {
            traderInventory.sellItem(item);
    }

    /**
     * Used when trader buys an item
     * @param item
     */
    // buys item into trader inventory
    public void buyItem(Item item){
        traderInventory.addItem(item,1);
    }

    public Item findItemInInventory(String itemName){
        Item item = traderInventory.findItem(itemName);
        return item;
    }

    public void outputInventory() {
        traderInventory.outputInventory();
    }
}
