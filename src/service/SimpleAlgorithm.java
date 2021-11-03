package service;

import model.Point;

import java.util.List;
import java.util.Map;

public interface SimpleAlgorithm {

    Double getSumOfDistances(List<Map<Integer,Double>> list);

    Double getDistanceBetweenPoints(Point from, Point to);
}
