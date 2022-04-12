package Hazard;

public class FindAction {
    Actions actions;

    // Constructor
    public FindAction(Actions actions) {
        this.actions = actions;
    }

    // Prints a line about Day using switch
    public void actionIsLike() {
        switch (actions) {
            case PICKUP:
                System.out.println("you want to pickup.");
                break;
            case MOVE:
                System.out.println("you want to move.");
                break;
            case LOOK:
                System.out.println("you want to look.");
                break;
            default:
                System.out.println("that is not an action");
                break;
        }
    }
}
