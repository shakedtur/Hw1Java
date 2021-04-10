package components;

/**
 * The department represents a record in the package transfer history.
 * Each package contains a collection of records of this type.
 * Time the status and location of a package is changed, a new record is added to the collection.
 * All record includes the time of creation of the record according to the clock of the program.
 * the point where the package is located and the status of the package.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 */

public class Tracking {
    //1.1.1
    //Fields;
    public int time;
    public Node node;
    public Status status;
    //Ctor
    //1.1.2
    /**
     * Ctor
     * data reset
     */
    public Tracking(){
        setTime(MainOffice.getClock());
        this.node=null;
        this.status=Status.CREATION1;

    };
    
    /**
     * Ctor for setTime, status, node
     * @param:
     * 			time - Value of the system clock as soon as the record is created
     * 			node - Package location - customer / branch / sorting center / transport vehicle.
     * 			status - Package status as soon as the record is created
     */

    public Tracking(int time,Node node,Status status){
        setTime(time);
        this.node=node;
        if(status!=Status.CREATION1)
            this.status=status;
        else
            this.status=Status.CREATION1;
    }

    //methods:
    //1.1.3
    //TODO
    
    /**
     * funcation that return string
     * checking if node is interface with Branch or Truck in the ending return string in accordance
     * @return: 
     * 			time - Value of the system clock as soon as the record is created
     * 			status - Package status as soon as the record is created
     */

    @Override
    public String toString() {
        String nodetype="Costumer";
        if(this.node instanceof Branch){
            nodetype=((Branch)node).getBranchName();
        }
        else if(this.node instanceof Truck){
            if(this.node instanceof Van)
                nodetype="Van "+((Truck) node).getTruckID();
            else if(this.node instanceof StandardTruck)
                nodetype="StandardTruck "+((Truck) node).getTruckID();
            else if(this.node instanceof NonStandardTruck)
                nodetype="NonStandardTruck "+((Truck) node).getTruckID();

        }

        return time + ": " + nodetype + ", status=" + status;
    }

    //getters /setters
    /**
     * get function for time
     * @return: time
     */
    
    public int getTime() {
        return time;
    }
    
    /**
     * set function for time
     * Math.abs(time) returns the absolute value of a given argument.
     * @param: time=time
     */
    
    public void setTime(int time) {
        this.time=Math.abs(time);
    }
    
    /**
     * get function for node
     * @return: node
     */
    
    public Node getNode() {
        return node;
    }
    
    /**
     * set function for node
     * @param: node
     */
    
    public void setNode(Node node) {
        this.node = node;
    }
    
    /**
     * get function for status
     * @return: status
     */
    
    public Status getStatus() {
        return status;
    }
    
    /**
     * set function for status
     * @param: status
     */
    
    public void setStatus(Status status) {
        this.status = status;
    }


}