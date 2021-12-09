package service;

import algorithm.HungarianAlgorithm;
import model.House;
import model.NormalizedDataset;
import model.Point;
import model.Well;
import utility.Log;

import java.util.*;

public class Service {
    private List<Well> wellList;
    private List<House> houseList;
    private List<List<Double>> distanceArray;
    private Log log;

    private final HungarianAlgorithm hungarianAlgorithm;

    public Service(NormalizedDataset normalizedDataset) {
        log = new Log("technical_logs.txt");
        log.log("Service.class - new instance created with input normalizedDataset: "+normalizedDataset.toString());
        fillArrayWithDistances(normalizedDataset);
        printDistancesMatrix();
        hungarianAlgorithm = new HungarianAlgorithm(wellList, houseList, distanceArray, normalizedDataset);
    }

    public void run(){
        log.log("Service.run: called");
        hungarianAlgorithm.run();
        log.log("Service.run: completed");
    }

    private void fillArrayWithDistances(NormalizedDataset normalizedDataset){
        log.log("Service.fillArrayWithDistances: called");
        distanceArray = new ArrayList<>();

        for(int i=0; i<normalizedDataset.getH(); i++){
            List<Double> list = new ArrayList<>();
            for(int j=0; j<normalizedDataset.getN(); j++){
                list.add(getDistanceBetweenPoints(normalizedDataset.getSetOfH().get(i),normalizedDataset.getSetOfN().get(j)));
            }
            distanceArray.add(list);
        }

        fillCoordinates(normalizedDataset);
        log.log("Service.fillArrayWithDistances: completed with result: " + distanceArray.toString());
    }

    private void fillCoordinates(NormalizedDataset normalizedDataset){
        log.log("Service.fillCoordinates: called");
        wellList = new ArrayList<>();
        houseList = new ArrayList<>();

        for(Point p: normalizedDataset.getSetOfN()){
            wellList.add(new Well(p));
            log.log("Service.fillCoordinates: well coordinates: "+p.toString());
        }

        for(Point p: normalizedDataset.getSetOfH()){
            houseList.add(new House(p));
            log.log("Service.fillCoordinates: house coordinates: "+p.toString());
        }
        log.log("Service.fillCoordinates: completed");
    }

    private void printDistancesMatrix(){
        log.log("Service.printDistancesMatrix: -->Distance Matrix<--");
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
        log.log("Service.printDistancesMatrix: completed");
    }

    private Double getDistanceBetweenPoints(Point from, Point to) {
        log.log("Service.getDistanceBetweenPoints: called");
        log.log("Service.getDistanceBetweenPoints: completed with result: " + Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2)));
        return Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }

}


