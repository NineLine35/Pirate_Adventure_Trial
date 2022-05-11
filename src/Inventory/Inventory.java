package Inventory;
import java.util.ArrayList;

/**
 * Interface For Different Inventories
 */
public interface Inventory {

    /**
     * Adds item to inventories list
     * @param item
     * @param quantity
     */
    public void addItem(Item item, int quantity);

    /**
     * Outputs all items in the inventories list
     */
    public void outputInventory();

    /**
     * Sells item
     * @param item
     */
    public void sellItem(Item item);

}
