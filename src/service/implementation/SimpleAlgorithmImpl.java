package service.implementation;

import model.NormalizedDataset;
import model.Point;
import service.SimpleAlgorithm;

import java.util.*;

public class SimpleAlgorithmImpl implements SimpleAlgorithm {
    private Integer numberOfHouses;

    public SimpleAlgorithmImpl(Integer h) {
        numberOfHouses = h;
    }

    @Override
    public Double getSumOfDistances(List<Map<Integer,Double>> list) {
        double cheapestSum=0;
        for(int i=0; i < 6; i++){
            double min = Double.MAX_VALUE;
            for(int j=0; j< list.size(); j++){
                if(list.get(j).get(i)<= min){
                    min = list.get(j).get(i);
                }
            }
            cheapestSum = cheapestSum + min;
            System.out.println(min);
            //TODO доделать функцию, которая определяет который из колодцев ближе к дому.
        }
        return cheapestSum;
    }

    @Override
    public Double getDistanceBetweenPoints(Point from, Point to) {
        return Math.sqrt(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }

    public List<Map<Integer,Double>> findAllDistancesFromEachWellToEachHouse(NormalizedDataset normalizedDataset){
        List<Map<Integer,Double>> mapList = new ArrayList<>();
        for(int i=0; i<normalizedDataset.getN(); i++){
            Map<Integer, Double> map = new HashMap<>();
            for(int j=0; j<normalizedDataset.getH(); j++){
                map.put(j,getDistanceBetweenPoints(normalizedDataset.getSetOfN().get(i),normalizedDataset.getSetOfH().get(j)));
            }
            mapList.add(map);
        }
        return mapList;
    }
}
