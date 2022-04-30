package GameLoop;

import MapFiles.Landmass;

/**
 * Enum that gives structure to the various sections (rooms) of our game, and provides a way to launch the subsequent
 * class/method for that room.  Implemented an overridden abstract method called "Run" for each enum value and also have
 * a user created error if the incorrect loop object is palled to the run method for that specific Enum value.
 */
/*public enum Action {
    OPENING {
        @Override
        public void run(Loop x, Landmass[][] y) {
            if(x instanceof OpeningLoop){
                ((OpeningLoop)x).launch();
            }
            else {
                System.out.println("Error: Incorrect loop object");
                System.exit(0);
            }
        }
    },
    AT_SEA {
        @Override
        public void run(Loop x, Landmass[][] y) {
            if(x instanceof AtSeaLoop){
                ((AtSeaLoop)x).launch();
            }
            else {
                System.out.println("Error: Incorrect loop object");
                System.exit(0);
            }
        }
    },
    ON_LAND {
        @Override
        public void run(Loop x, Landmass[][] y) {

        }
    },
    AT_PORT {
        @Override
        public void run(Loop x, Landmass [][] y) {

        }
    };

    public abstract void run(Loop x, Landmass[][] y);
}*/
