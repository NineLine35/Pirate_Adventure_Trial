package Inventory;

public class Trader {
    private TraderInventory inventory = new TraderInventory();

    // Sells item from trader inventory
    public void sellItem(String itemName) {
        Item item = inventory.findItem(itemName);

        if (item != null) {
            inventory.sellItem(item);
        }
    }

    public void buyItem(Item item){
        inventory.addItem(item,1);
    }
}
