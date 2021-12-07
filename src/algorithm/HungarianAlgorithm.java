package algorithm;

import model.House;
import model.NormalizedDataset;
import model.Point;
import model.Well;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HungarianAlgorithm {
    private List<Well> wellList;
    private List<House> houseList;
    private List<List<Double>> distanceArray;
    private NormalizedDataset normalizedDataset;
    private Integer rows = 0;
    private Integer columns = 0;
    private Integer houseQuantity;
    private Integer wellQuantity;
    private Integer connected = 0;
    private List<Integer> connections = new ArrayList<>();

    public HungarianAlgorithm(List<Well> wellList,
                              List<House> houseList,
                              List<List<Double>> distanceArray,
                              NormalizedDataset normalizedDataset) {
        this.wellList = wellList;
        this.houseList = houseList;
        this.distanceArray = distanceArray;
        this.normalizedDataset = normalizedDataset;
        houseQuantity = normalizedDataset.getH();
        wellQuantity = normalizedDataset.getN();
    }


    public void run(){
        detectMinimalValueInRowsAndSubtract();
        detectMinimalValueInColumnsAndSubtract();


        assign();
        detach();


        while(flaggedLines() < houseQuantity){
            subtractMinimalValue(detectMinimalValue());
            rollback();
            assign();
            detach();
        }

        print();
    }

    private void detectMinimalValueInColumnsAndSubtract(){
        for(int i = 0; i< wellQuantity; i++){
            double min = Double.MAX_VALUE;
            for(int j = 0; j< houseQuantity; j++){
                if(distanceArray.get(j).get(i) < min){
                    min = distanceArray.get(j).get(i);
                }
            }

            for(int j = 0; j< houseQuantity; j++){
                distanceArray.get(j).set(i, distanceArray.get(j).get(i) - min);
            }
        }
        print("second step");
    }

    private void detectMinimalValueInRowsAndSubtract(){
        for(int i = 0; i < houseQuantity; i++){
            double min = Double.MAX_VALUE;
            for(int j = 0; j< wellQuantity; j++){
                if(distanceArray.get(i).get(j) < min){
                    min = distanceArray.get(i).get(j);
                }
            }

            for(int j = 0; j< wellQuantity; j++){
                distanceArray.get(i).set(j, distanceArray.get(i).get(j) - min);
            }
        }
        print("first step");
    }

    //TODO Breakpoint 1
    private Integer flaggedLines(){
        System.out.println("Breakpoint 1");
        return houseQuantity - rows + normalizedDataset.getK() * columns;
    }

    //TODO Breakpoint 2
    private void rollback(){
        System.out.println("Breakpoint 2");
        connected = rows = columns = 0;
        connections.removeAll(connections);

        for (int i = 0; i < wellQuantity; i++) {
            wellList.get(i).getConnectionList().removeAll(wellList.get(i).getConnectionList());
            wellList.get(i).setFlagged(Boolean.FALSE);
        }

        for (int i = 0; i < houseQuantity; i++) {
            houseList.get(i).setConnection(-1);
            houseList.get(i).setFlagged(Boolean.FALSE);
        }
    }

    //TODO Breakpoint 3
    private void flag(int index){
        System.out.println("Breakpoint 3");
        for(int pos = 0; pos < wellQuantity; pos++){
            if(distanceArray.get(index).get(pos) == 0 && !wellList.get(pos).isFlagged()){
                columns = columns + 1;
                wellList.get(pos).setFlagged(true);
                for(Integer i: wellList.get(pos).getConnectionList()){
                    houseList.get(i).setFlagged(true);
                    rows = rows + 1;
                    flag(i);
                }
            }
        }
    }

    //TODO Breakpoint 4
    private void detach(){
        System.out.println("Breakpoint 4");
        for (int i = 0; i < houseQuantity; i++) {
            if (houseList.get(i).getConnection() < 0) {
                rows++;
                houseList.get(i).setFlagged(true);
                flag(i);
            }
        }
    }

    //TODO Breakpoint 5.1
    private double detectMinimalValue(){
        System.out.println("Breakpoint 5.1");
        double min = Double.MAX_VALUE;
        for(int i=0; i< houseQuantity; i++){
            for(int j=0; j< wellQuantity; j++){
                if(distanceArray.get(i).get(j) < min && !wellList.get(j).isFlagged() && houseList.get(i).isFlagged()){
                    min = distanceArray.get(i).get(j);
                }
            }
        }

        return min;
    }

    //TODO Breakpoint 5.2
    private void subtractMinimalValue(double min){
        System.out.println("Breakpoint 5.2");
        for(int i=0; i< houseQuantity; i++){
            for(int j=0; j< wellQuantity; j++){
                if(!wellList.get(j).isFlagged() && houseList.get(i).isFlagged()){
                    distanceArray.get(i).set(j, distanceArray.get(i).get(j) - min);
                }
            }
        }
        print("Another step");
    }

    //TODO Breakpoint 6 COPY
    private void assign() {
        System.out.println("Breakpoint 6 COPY");
        List<Integer> clone = new ArrayList<>(Collections.nCopies(houseQuantity, -1));
        List<Integer> limit = new ArrayList<>(Collections.nCopies(wellQuantity, 0));

        detectAll(0, 0, clone, limit);

        for (int i = 0; i < houseQuantity; i++) {
            if (connections.get(i) >= 0) {
                wellList.get(connections.get(i)).getConnectionList().add(i);
                houseList.get(i).setConnection(connections.get(i));
            }
        }
    }

    //TODO Breakpoint 7 COPY
    private void detectAll(int pos, int assigned, List<Integer> clone, List<Integer> limit) {
        System.out.println("Breakpoint 7 COPY");
        if(clone != null){
            if (pos == houseQuantity) {
                if (assigned > connected) {
                    connected = assigned;
                    connections = clone;
                }
            }
            else{
                boolean found = false;
                for (int currentWell = 0; currentWell < wellQuantity; currentWell++) {

                    List<Integer> tempRes = new ArrayList<>(clone);
                    List<Integer> tempWellConnection = new ArrayList<>(limit);
                    System.out.println(tempWellConnection.size());
                    double temDistance = distanceArray.get(pos).get(currentWell);

                    if (temDistance == 0 && tempWellConnection.get(currentWell) < normalizedDataset.getK()) {
                        found = true;
                        tempRes.set(pos, currentWell);
                        System.out.println(tempRes.get(pos));
                        tempWellConnection.set(currentWell, tempWellConnection.get(currentWell) + 1);
                        System.out.println(tempWellConnection.get(currentWell));
                        detectAll(pos + 1, assigned + 1, tempRes, tempWellConnection);
                    }

                }

                if (!found) {
                    detectAll(pos + 1, assigned, clone, limit);
                }
            }
        }
    }

    private void print(String string){
        System.out.printf("\n\n-->%s<--\n\n", string);
        for(int i = -1; i< distanceArray.size(); i++){
            if(i==-1){
                System.out.printf("%9s", " ");
                for(int j = 0; j< distanceArray.get(0).size(); j++){
                    System.out.printf("%13s", "Well "+(j+1)+"|");
                }
                System.out.println();
                continue;
            }
            System.out.printf("House No%d|",i+1);
            for(int j = 0; j< distanceArray.get(i).size(); j++){
                System.out.printf("%10.4f |", distanceArray.get(i).get(j));
            }
            System.out.println();
        }
    }

    private void print(){
        double sum = 0;
        StringBuilder s = new StringBuilder();
        StringBuilder result;
        for (Well well : wellList) {
            result = new StringBuilder(well.getPoint().toString() +" --> ");
            for (int h : well.getConnectionList()) {
                result.append(houseList.get(h).getPoint());
                sum += getDistanceBetweenPoints(well.getPoint(), houseList.get(h).getPoint());
            }
            s.append(result).append("\n");
        }
        s.append("Sum : ").append(sum);

        System.out.println(s);
    }

    private Double getDistanceBetweenPoints(Point from, Point to) {
        return Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }
}
