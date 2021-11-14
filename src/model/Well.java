package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Well {
    private String id;
    private Integer connectionAmount;
    private Point point;
    private List<House> houseList;

    public Well(Point point){
        this.point = point;
        id = UUID.randomUUID().toString();
        houseList = new ArrayList<>();
        connectionAmount = 0;
    }

    public void setHouseToList(House house) {
        houseList.add(house);
    }

    public String getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    public void setConnectionAmount(Integer connectionAmount) {
        this.connectionAmount = connectionAmount;
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public Integer getConnectionAmount() {
        return connectionAmount;
    }
}
