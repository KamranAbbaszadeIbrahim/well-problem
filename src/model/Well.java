package model;

import java.util.ArrayList;
import java.util.List;

public class Well {
    private Point point;
    private List<Integer> connectionList;
    private boolean isFlagged;

    public Well(Point point){
        this.point = point;
        connectionList = new ArrayList<>();
    }

    public Point getPoint() {
        return point;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setConnection(Integer connection){
        connectionList.add(connection);
    }

    public List<Integer> getConnectionList(){
        return connectionList;
    }
}
