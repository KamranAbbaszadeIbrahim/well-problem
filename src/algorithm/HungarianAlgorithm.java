package algorithm;

import model.House;
import model.NormalizedDataset;
import model.Point;
import model.Well;
import utility.Log;
import utility.OutputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HungarianAlgorithm {
    private final Log log;
    private final List<Well> wellList;
    private final List<House> houseList;
    private final List<List<Double>> distanceArray;
    private final NormalizedDataset normalizedDataset;
    private Integer rows = 0;
    private Integer columns = 0;
    private final Integer houseQuantity;
    private final Integer wellQuantity;
    private Integer connected = 0;
    private List<Integer> connections = new ArrayList<>();

    public HungarianAlgorithm(List<Well> wellList,
                              List<House> houseList,
                              List<List<Double>> distanceArray,
                              NormalizedDataset normalizedDataset) {
        log = new Log("technical_logs.txt");
        this.wellList = wellList;
        this.houseList = houseList;
        this.distanceArray = distanceArray;
        this.normalizedDataset = normalizedDataset;
        log.log("HungarianAlgorithm.class - new instance created");
        houseQuantity = normalizedDataset.getH();
        wellQuantity = normalizedDataset.getN();
        log.log("HungarianAlgorithm.houseQuantity: " + houseQuantity);
        log.log("HungarianAlgorithm.wellQuantity: " + wellQuantity);
    }


    public void run(){
        log.log("HungarianAlgorithm.run: called");
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
        log.log("HungarianAlgorithm.run: completed");
    }

    private void detectMinimalValueInColumnsAndSubtract(){
        log.log("HungarianAlgorithm.detectMinimalValueInColumnsAndSubtract: called");
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
        print("detectMinimalValueInColumnsAndSubtract");
        log.log("HungarianAlgorithm.detectMinimalValueInColumnsAndSubtract: completed");
    }

    private void detectMinimalValueInRowsAndSubtract(){
        log.log("HungarianAlgorithm.detectMinimalValueInRowsAndSubtract: called");
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
        print("detectMinimalValueInRowsAndSubtract");
        log.log("HungarianAlgorithm.detectMinimalValueInRowsAndSubtract: completed");
    }

    private Integer flaggedLines(){
        log.log("HungarianAlgorithm.flaggedLines: called");
        log.log("HungarianAlgorithm.flaggedLines: completed with result: " + (houseQuantity - rows + normalizedDataset.getK() * columns));
        return houseQuantity - rows + normalizedDataset.getK() * columns;
    }

    private void rollback(){
        log.log("HungarianAlgorithm.rollback: called");
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
        log.log("HungarianAlgorithm.rollback: completed");
    }

    private void flag(int index){
        log.log("HungarianAlgorithm.flag: called");
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
        log.log("HungarianAlgorithm.flag: completed with results: rows = ["+rows+"]; columns = [" + columns + "]");
    }

    private void detach(){
        log.log("HungarianAlgorithm.detach: called");
        for (int i = 0; i < houseQuantity; i++) {
            if (houseList.get(i).getConnection() < 0) {
                rows++;
                houseList.get(i).setFlagged(true);
                flag(i);
            }
        }
        log.log("HungarianAlgorithm.detach: completed");
    }

    private double detectMinimalValue(){
        log.log("HungarianAlgorithm.detectMinimalValue: called");
        double min = Double.MAX_VALUE;
        for(int i=0; i< houseQuantity; i++){
            for(int j=0; j< wellQuantity; j++){
                if(distanceArray.get(i).get(j) < min && !wellList.get(j).isFlagged() && houseList.get(i).isFlagged()){
                    min = distanceArray.get(i).get(j);
                }
            }
        }
        log.log("HungarianAlgorithm.detectMinimalValue: completed with result: min = " + min);
        return min;
    }

    private void subtractMinimalValue(double min){
        log.log("HungarianAlgorithm.subtractMinimalValue: called with input: " + min);
        for(int i=0; i< houseQuantity; i++){
            for(int j=0; j< wellQuantity; j++){
                if(!wellList.get(j).isFlagged() && houseList.get(i).isFlagged()){
                    distanceArray.get(i).set(j, distanceArray.get(i).get(j) - min);
                }
            }
        }
        print("subtractMinimalValue");
        log.log("HungarianAlgorithm.subtractMinimalValue: completed");
    }

    private void assign() {
        log.log("HungarianAlgorithm.assign: called");
        List<Integer> clone = new ArrayList<>(Collections.nCopies(houseQuantity, -1));
        List<Integer> limit = new ArrayList<>(Collections.nCopies(wellQuantity, 0));

        detectAll(0, 0, clone, limit);

        for (int i = 0; i < houseQuantity; i++) {
            if (connections.get(i) >= 0) {
                wellList.get(connections.get(i)).getConnectionList().add(i);
                houseList.get(i).setConnection(connections.get(i));
            }
        }

        log.log("HungarianAlgorithm.assign: completed");
    }

    private void detectAll(int pos, int assigned, List<Integer> clone, List<Integer> limit) {
        log.log("HungarianAlgorithm.detectAll: called with inputs: pos = " + pos +"; assigned = " + assigned + "; clone = " + clone.toString() + "; limit = " + limit.toString());
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

                    double temDistance = distanceArray.get(pos).get(currentWell);

                    if (temDistance == 0 && tempWellConnection.get(currentWell) < normalizedDataset.getK()) {
                        found = true;
                        tempRes.set(pos, currentWell);
                        tempWellConnection.set(currentWell, tempWellConnection.get(currentWell) + 1);
                        detectAll(pos + 1, assigned + 1, tempRes, tempWellConnection);
                    }

                }

                if (!found) {
                    detectAll(pos + 1, assigned, clone, limit);
                }
            }
        }
        log.log("HungarianAlgorithm.detectAll: completed");
    }

    private void print(String string){
        log.log("HungarianAlgorithm.print: called");
        log.log(String.format("-->%s<--", string));
        for(int i = -1; i< distanceArray.size(); i++){
            if(i==-1){
                String s = String.format("%9s", " ");
                for(int j = 0; j< distanceArray.get(0).size(); j++){
                    s = s + String.format("%13s", "Well "+(j+1)+"|");
                }
                log.log(s);
                continue;
            }
            String s = String.format("House No%d|",i+1);
            for(int j = 0; j< distanceArray.get(i).size(); j++){
                s = s + String.format("%10.4f |", distanceArray.get(i).get(j));
            }
            log.log(s);
        }
        log.log("HungarianAlgorithm.print: completed");
    }

    private void print(){
        log.log("HungarianAlgorithm.print: called");
        double sum = 0;
        StringBuilder finalResult = new StringBuilder();
        StringBuilder result = new StringBuilder();
        StringBuilder tmp;
        for (Well well : wellList) {
            tmp = new StringBuilder(well.getPoint().toString() +" --> [");
            for (int h : well.getConnectionList()) {
                tmp.append(houseList.get(h).getPoint()).append(", ");
                sum += getDistanceBetweenPoints(well.getPoint(), houseList.get(h).getPoint());
            }
            log.log("HungarianAlgorithm.print: " + tmp);
            result.append(tmp).append("]").append("\n");
        }
        log.log("HungarianAlgorithm.print: cheapest distance sum: " + sum);
        finalResult.append("Cheapest Distance Sum : ").append(sum);
        finalResult.append("\nWells \t\t\t\tHouses\n");
        finalResult.append(result);
        OutputUtil.output(finalResult.toString());
        log.log("HungarianAlgorithm.print: completed");
    }

    private Double getDistanceBetweenPoints(Point from, Point to) {
        log.log("HungarianAlgorithm.getDistanceBetweenPoints: called");
        log.log("HungarianAlgorithm.getDistanceBetweenPoints: completed with result: " + Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2)));
        return Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }
}
