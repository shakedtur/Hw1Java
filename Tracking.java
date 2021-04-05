package components;

public class Tracking {
    //1.1.1
    //Fields;
    public int time;
    public Node node;
    public Status status;
    //Ctor
    //1.1.2
    public Tracking(){
        setTime(MainOffice.getClock());
        this.node=null;
        this.status=Status.CREATION;

    };

    public Tracking(int time,Node node,Status status){
        setTime(time);
        this.node=node;
        if(status!=Status.CREATION)
            this.status=status;
        else
            this.status=Status.CREATION;
    }

    //methods:
    //1.1.3
    //TODO

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
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time=Math.abs(time);
    }
    public Node getNode() {
        return node;
    }
    public void setNode(Node node) {
        this.node = node;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }


}
