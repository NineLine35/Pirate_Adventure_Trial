package Inventory;

public abstract class Item {
    private String name;
    private int quantity;

    // Constructor if quantity and name are given
    public Item(String name, int quantity){
        this.setName(name);
        this.setQuantity(quantity);
    }

    // Constructor if only name is given, quantity is assumed as 1
    public Item(String name){
        this.setName(name);
        this.setQuantity(1);
    }

    public int getQuantity() { return this.quantity; }
    public void setQuantity(int q) { this.quantity = q; }

    public String getName(){ return this.name; }
    public void setName(String n) { this.name = n; }
}
