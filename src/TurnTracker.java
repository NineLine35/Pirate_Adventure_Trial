/* Singleton class that will act as a "timer" and track game turns.  Using a singleton
   approach ensures that the game will only have a single "clock" object in memory during the duration of the
   game. And ensures that other programmers cannot accidentally add more timers to the game and mess with the game flow.
 */

/***
 * Testing doc
 */

public class TurnTracker {
    // Initial time set at start of game
    private int timeLeft = 10;

    // Default constructor
    private TurnTracker(){};

    private static final TurnTracker instance = new TurnTracker();

    public static TurnTracker getInstance() {
        return instance;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    // Remove a single "timeLeft" at the end of turn
    public synchronized void endOfTurn(){
        timeLeft--;
    }

    // Method to extend the timeLeft if needed
    public synchronized void extendTurn(int amount){
        timeLeft += amount;
    }
}
