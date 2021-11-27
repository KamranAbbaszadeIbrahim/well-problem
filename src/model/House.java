package model;

public class House {
    private Point point;
    private Integer connection;
    private boolean isFlagged;

    public House(Point point){
        this.point = point;
        connection = Integer.MIN_VALUE;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return point.toString();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
