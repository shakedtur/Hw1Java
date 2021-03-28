package components;

public class Tracking {
    //1.1.1
    //Fields;
    public int time;
    public Node node;
    public Status status;
    //Ctor
    //1.1.2
    public Tracking(int time,Node node,Status status){
        this.time=time;
        this.node=node;
        this.status=status;
    }

    //methods:
    //1.1.3
    //TODO

    
    //getters /setters
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
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
