package Inventory;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class for the players inventory
 * Implements the inventory class
 */
public class PlayerInventory implements Inventory{
    ArrayList<Item> items;

    public PlayerInventory () { items = new ArrayList<>(); }
    /**
     * Finds and returns and item if found in the item list
     * Returns null if item not found
     * @param itemName
     * @return
     */
    public Item findItem(String itemName){
        Item foundItem = null;
        // Runs through the list of items
        for (int i = 0; i < items.size(); i++){
            // Item is found
            if (items.get(i).getName().toLowerCase().equals(itemName.toLowerCase().replace("_", " "))){
                // Item is set to found item
                foundItem = items.get(i);
            }
        }
        return foundItem;
    }

    /**
     * Adds item to list of items or updates quantity if item exists
     * @param item
     * @param quantity
     */
    public void addItem(Item item, int quantity){
        boolean hasDupe = false;
        int dupePos = 0;
        item.setQuantity(quantity);

        // Runs through list of items to check for duplicates
        for (int i = 0; i < items.size(); i++){
            // Duplicate is found and values are set to be added
            if (item.getName().equals(this.items.get(i).getName())){
                hasDupe = true;
                dupePos = i;
            }
        }

        // Item is a duplicate
        if (hasDupe){
            // Updates the quantity of the item
            this.items.get(dupePos).setQuantity(quantity += this.items.get(dupePos).getQuantity());
        } else{
            // Adds the item to the list
            this.items.add(item);
        }
    }

    /**
     * Sells the item from players inventory
     * @param item
     */
    public void sellItem(Item item){
        // Runs through all repair items
        for(int i = 0; i < this.items.size(); i++){
            // Item is found
            if (this.items.get(i) == item){
                // Item is removed
                this.items.remove(i);
            }
        }
    }

    /**
     * Gets rid of the item or updates quantity if ship is repaired
     * @param item
     */
    public void useRepairItem(Item item){
        // Runs through items
        for(int i = 0; i < this.items.size(); i++){
            // Item is found
            if (this.items.get(i) == item){
                // Checks if quantity is more than one
                if (this.items.get(i).getQuantity() > 1) {
                    // Quantity is updated to reflect item being used
                    this.items.get(i).setQuantity(this.items.get(i).getQuantity() - 1);
                } else {
                    // Item is removed
                    this.items.remove(i);
                }
            }
        }
    }

    /**
     * Outputs all the items in the inventory
     */
    public void outputInventory() {
        if (items.size() > 0){
            System.out.println("Your Items");
            for (int i = 0; i < items.size(); i++){
                System.out.println(items.get(i).toString());
            }
        } else {
            System.out.println("You do not have any items");
        }
    }
}
