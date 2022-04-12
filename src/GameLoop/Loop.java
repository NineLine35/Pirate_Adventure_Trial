package GameLoop;

import MapFiles.Landmass;

/**
 * Abstract Loop class to base all gameplay loops off of.  For example, opening game play loop, at sea loop, at port loop
 * etc.
 */

 abstract class Loop {

     public abstract void launch(Landmass[][] x);

}
