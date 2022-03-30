package MapFiles;

public class Landmass {
    int size;
    private String name;  //Use of private to ensure that island names can only be accessed through public methods (Getters/Setters)

    public Landmass(int size) {
        this.size = size;
    }

    //Default method that confirms that Islands have been generated at game start
    void confirmIslandCreation(){
        System.out.println("Islands have been created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
