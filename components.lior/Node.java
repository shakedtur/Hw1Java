package components;

/**
 * Represents the location of a package
 * can refer to branches and trucks (all points where the package can be in the various stages of its transfer).
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 */


//interface
public interface Node {

    //methods:
    //1.2.1
	
	/**
	 * 	An interface is a completely "abstract class" that is used to group related methods with empty bodies
	 */
	
     void collectPackage(Package p);
     void deliverPackage(Package p);
     void work();

}
