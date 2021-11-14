package service.implementation;

import model.House;
import model.NormalizedDataset;
import model.Point;
import model.Well;
import service.SimpleAlgorithm;

import java.util.*;

public class SimpleAlgorithmImpl implements SimpleAlgorithm {
    private Integer numberOfHouses;
    public List<Well> wellList;

    public SimpleAlgorithmImpl(Integer h) {
        numberOfHouses = h;
    }

    @Override
    public Double getSumOfDistances(List<Map<Integer,Double>> list, NormalizedDataset normalizedDataset) {
        double cheapestSum=0;
        for(int i=0; i < normalizedDataset.getH(); i++){
            double min = Double.MAX_VALUE;
            int wellIndex = 0;
            int houseIndex = 0;

            for(int j=0; j < normalizedDataset.getN(); j++){
                if(wellList.get(j).getConnectionAmount().equals(normalizedDataset.getK())){
                    continue;
                }
                if(list.get(j).get(i)<= min){
                    min = list.get(j).get(i);
                    wellIndex = j;
                    houseIndex = i;
                }
            }

            wellList.get(wellIndex).setHouseToList(new House(normalizedDataset.getSetOfH().get(houseIndex)));
            wellList.get(wellIndex).setConnectionAmount(wellList.get(wellIndex).getConnectionAmount()+1);
            cheapestSum = cheapestSum + min;
            System.out.println(min);
        }
        return cheapestSum;
    }

    @Override
    public Double getDistanceBetweenPoints(Point from, Point to) {
        return Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }

    public List<Map<Integer,Double>> findAllDistancesFromEachWellToEachHouse(NormalizedDataset normalizedDataset){
        List<Map<Integer,Double>> mapList = new ArrayList<>();
        wellList = new ArrayList<>();
        for(int i=0; i<normalizedDataset.getN(); i++){
            Well well = new Well(normalizedDataset.getSetOfN().get(i));
            Map<Integer, Double> map = new HashMap<>();
            for(int j=0; j<normalizedDataset.getH(); j++){
                map.put(j,getDistanceBetweenPoints(normalizedDataset.getSetOfN().get(i),normalizedDataset.getSetOfH().get(j)));
            }
            mapList.add(map);
            wellList.add(well);
        }
        return mapList;
    }

    public void printResult(){
        System.out.println("\tWells \t\t | \t House");
        for(int i=0; i<wellList.size(); i++){
            System.out.printf("%s \t-> \t%s%n", wellList.get(i).getPoint(), wellList.get(i).getHouseList());
        }
    }
}


