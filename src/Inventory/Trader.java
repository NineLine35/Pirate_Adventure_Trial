package Inventory;

public class Trader {
    private TraderInventory inventory = new TraderInventory();

    public void addItem(Item item, int quantity){
        inventory.addItem(item, quantity);
    }

    // Sells item from trader inventory
    public void sellItem(String itemName) {
        Item item = inventory.findItem(itemName);

        if (item != null) {
            inventory.sellItem(item);
        }
    }

    // buys item into trader inventory
    public void buyItem(Item item){
        inventory.addItem(item,1);
    }
}
