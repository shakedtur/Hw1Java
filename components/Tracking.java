package components;

public class Tracking {
    //1.1.1
    //Fields;
    public int time;
    public Node node;
    public Status status;
    //Ctor
    //1.1.2
    public Tracking(){};

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
        return "Tracking{" +
                "time=" + time +
                ", node=" + node +
                ", status=" + status +
                '}';
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
