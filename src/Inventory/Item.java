package Inventory;

public class Item {
    private String name;
    private int quantity;

    // Constructor if quantity and name are given
    public Item(String n, int q){
        this.setName(n);
        this.setQuantity(q);
    }

    // Constructor if only name is given, quantity is assumed as 1
    public Item(String n){
        this.setName(n);
        this.setQuantity(1);
    }

    // Gets and sets for quantity
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int q) { this.quantity = q; }

    // Gets and sets for name
    public String getName(){ return this.name; }
    public void setName(String n) { this.name = n; }
}
