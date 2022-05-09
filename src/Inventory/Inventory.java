package Inventory;
import java.util.ArrayList;

/**
 * Interface For Different Inventories
 */
public interface Inventory {

    public void addItem(Item item, int quantity);
    public void outputInventory();
    public void sellItem(Item item);

}
