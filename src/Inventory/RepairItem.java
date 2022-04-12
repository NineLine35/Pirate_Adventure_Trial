package Inventory;

public class RepairItem extends Item{
    private String repairType;
    private int repairAmount;

    public RepairItem (String name, int quantity, String repairType, int repairAmount){
        super(name, quantity);
        setRepairType(repairType);
    }
    public RepairItem (String name, String repairType, int repairAmount){
        super (name);
        setRepairAmount(repairAmount);
        setRepairType(repairType);
    }

    public String getRepairType(){ return this.repairType; }
    public void setRepairType(String repairType){ this.repairType = repairType; }

    public int getRepairAmount() { return this.repairAmount; }
    public void setRepairAmount(int repairAmount) { this.repairAmount = repairAmount; }

}
