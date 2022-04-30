package Inventory;
import java.util.ArrayList;
import java.util.Arrays;

public interface Inventory {
    ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item, int quantity);
    public void outputInventory();
    public void sellItem(Item item);

}
