package Inventory;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<Item>();

    public void addItem(Item item, int quantity) {
        boolean hasDupe = false;
        int dupePos = 0;

        // Runs through list of items to check for duplicates
        for (int i = 0; i < this.items.size(); i++){
            // Duplicate is found and values are set to be added
            if (item == this.items.get(i)){
                hasDupe = true;
                dupePos = i;
            }
        }

        // Item is a duplicate
        if (hasDupe == true){
            // Updates the quantity of the item
            this.items.get(dupePos).setQuantity(quantity += this.items.get(dupePos).getQuantity());
        } else{
            // Adds the item to the list
            this.items.add(item);
        }
    }

    public void outputItems() {
        // Run through all items in inventory
        for (int i = 0; i < this.items.size(); i++){
            // Print out items currently in inventory
            System.out.println(this.items.get(i).getName());
        }
    }
}
