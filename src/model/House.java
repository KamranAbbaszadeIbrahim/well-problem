package model;

import java.util.UUID;

public class House {
    private String id;
    private Point point;

    public House(Point point){
        this.point = point;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
