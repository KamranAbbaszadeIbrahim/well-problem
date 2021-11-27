package service;

import algorithm.HungarianAlgorithm;
import model.House;
import model.NormalizedDataset;
import model.Point;
import model.Well;

import java.util.*;

public class Service {
    private List<Well> wellList;
    private List<House> houseList;
    private List<List<Double>> distanceArray;

    private HungarianAlgorithm hungarianAlgorithm;
    private NormalizedDataset normalizedDataset;

    public Service(NormalizedDataset normalizedDataset) {
        this.normalizedDataset = normalizedDataset;
        fillArrayWithDistances(normalizedDataset);
        printDistancesMatrix();
        hungarianAlgorithm = new HungarianAlgorithm(wellList, houseList, distanceArray, normalizedDataset);
    }

    public void run(){
        hungarianAlgorithm.run();
    }
/*
    private void fillArrayWithDistances(NormalizedDataset normalizedDataset){
        distanceArray = new ArrayList<>();

        for(int i=0; i<normalizedDataset.getN(); i++){
            List<Double> list = new ArrayList<>();
            for(int j=0; j<normalizedDataset.getH(); j++){
                list.add(getDistanceBetweenPoints(normalizedDataset.getSetOfN().get(i),normalizedDataset.getSetOfH().get(j)));
            }
            distanceArray.add(list);
        }

        fillCoordinates(normalizedDataset);
    }
*/
    private void fillArrayWithDistances(NormalizedDataset normalizedDataset){
        distanceArray = new ArrayList<>();

        for(int i=0; i<normalizedDataset.getH(); i++){
            List<Double> list = new ArrayList<>();
            for(int j=0; j<normalizedDataset.getN(); j++){
                list.add(getDistanceBetweenPoints(normalizedDataset.getSetOfH().get(i),normalizedDataset.getSetOfN().get(j)));
            }
            distanceArray.add(list);
        }

        fillCoordinates(normalizedDataset);
    }

    private void fillCoordinates(NormalizedDataset normalizedDataset){
        wellList = new ArrayList<>();
        houseList = new ArrayList<>();

        for(Point p: normalizedDataset.getSetOfN()){
            wellList.add(new Well(p));
        }

        for(Point p: normalizedDataset.getSetOfH()){
            houseList.add(new House(p));
        }
    }

    private void printDistancesMatrix(){
        System.out.printf("\n\n-->%s<--\n\n", "Distance Matrix");
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

    private Double getDistanceBetweenPoints(Point from, Point to) {
        return Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }

}


