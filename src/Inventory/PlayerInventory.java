package Inventory;

import java.util.ArrayList;
import java.util.Locale;

public class PlayerInventory implements Inventory{

    public Item findItem(String itemName){
        Item foundItem = null;
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getName() == itemName.toUpperCase().replace(" ","_")){
                foundItem = items.get(i);
            }
        }
        return foundItem;
    }

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

    //
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

    public void useRepairItem(Item item){
        boolean exists = false;
        // Runs through items
        for(int i = 0; i < this.items.size(); i++){
            // Item is found
            if (this.items.get(i) == item){
                if (this.items.get(i).getQuantity() > 1) {
                    this.items.get(i).setQuantity(this.items.get(i).getQuantity() - 1);
                } else {
                    // Item is used
                    this.items.remove(i);
                }
                // Boolean is changed to output to user
                exists = true;
            }
        }

        if (!exists){
            System.out.println("You Do Not Have That Item To Use Sailor");
        }
    }

    public void outputInventory() {
        System.out.println("Your Items");

        for (int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).toString());
        }
    }
}
