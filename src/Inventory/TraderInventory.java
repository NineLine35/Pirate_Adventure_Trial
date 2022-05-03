package Inventory;

import java.util.ArrayList;

public class TraderInventory implements Inventory {

    public void addItem(Item item, int quantity){
        boolean hasDupe = false;
        int dupePos = 0;
        item.setQuantity(quantity);

        // Runs through list of items to check for duplicates
        for (int i = 0; i < items.size(); i++){
            // Duplicate is found and values are set to be added
            if (item == this.items.get(i)){
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

    // Used when selling items
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

    public void outputInventory() {
        System.out.println("Traders Items");

        for (int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).toString());
        }
    }
}
