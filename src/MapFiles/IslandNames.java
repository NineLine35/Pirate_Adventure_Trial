package MapFiles;

/**
 * Enum for IslandNames
 */
//1.8 - use of enum
public enum IslandNames {
    //1.4 and 1.7 - override
    //find the name and return a readable name
    // TODO double check this is 1.4 and 1.7
    STORMY_ATOLL{
        @Override
        public String toString() {
            return "Stormy Atoll";
        }
    },
    SCOURGE_ISLE{
        @Override
        public String toString() {
            return "Scourge Isle";
        }
    },
    BAY_OF_GOLD{
        @Override
        public String toString() {
            return "Bay of Gold";
        }
    },
    BLOOD_PORT{
        @Override
        public String toString() {
            return "Blood Port";
        }
    },
    ISLE_OF_STORMS{
        @Override
        public String toString() {
            return "Isle of Storms";
        }
    },
    SKULL_ISLAND{
        @Override
        public String toString() { return "Skull Island";}
    },
    PORT_ROYAL{
        @Override
        public String toString() { return "Port Royal"; }
    }
}
