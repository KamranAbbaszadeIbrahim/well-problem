package service;

import model.NormalizedDataset;
import model.Point;

import java.util.List;
import java.util.Map;

public interface SimpleAlgorithm {

    Double getSumOfDistances(List<Map<Integer,Double>> list, NormalizedDataset normalizedDataset);

    Double getDistanceBetweenPoints(Point from, Point to);
}