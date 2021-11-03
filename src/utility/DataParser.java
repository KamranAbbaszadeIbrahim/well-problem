package utility;

import model.Dataset;
import model.NormalizedDataset;
import model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class DataParser {
    public static Dataset parseListToDatasetObject(List<String> list){

        Dataset dataset = new Dataset();
        dataset.setN(parseStringToInteger(list.get(0)));
        dataset.setK(parseStringToInteger(list.get(1)));
        dataset.setH(parseStringToInteger(list.get(2)));
        dataset.setSetOfN(list.get(3));
        dataset.setSetOfH(list.get(4));

        return dataset;
    }

    private static Integer parseStringToInteger(String string){
        StringBuilder sb = new StringBuilder();
        for(Character c: string.toCharArray()){
            if(c>='0' && c<='9'){
                sb.append(c);
            }
            else{
                if(sb.length() != 0){
                    break;
                }
            }
        }
        return Integer.valueOf(sb.toString());
    }

    public static NormalizedDataset parseDatasetToNormalizedDataset(Dataset dataset){
        NormalizedDataset normalizedDataset = new NormalizedDataset();

        normalizedDataset.setN(dataset.getN());
        normalizedDataset.setK(dataset.getK());
        normalizedDataset.setH(dataset.getH());
        normalizedDataset.setSetOfN(parseStringToPointList(dataset.getSetOfN()));
        normalizedDataset.setSetOfH(parseStringToPointList(dataset.getSetOfH()));

        return normalizedDataset;
    }

    private static List<Point> parseStringToPointList(String string){
        List<Point> list = new ArrayList<>();
        List<String> strings = parseTokenToList(string);
        for(int i=1; i<strings.size(); i+=2){
            Point point = new Point();
            point.setX(Double.valueOf(strings.get(i-1)));
            point.setY(Double.valueOf(strings.get(i)));
            list.add(point);
        }
        return list;
    }

    private static List<String> parseTokenToList(String string){
        StringTokenizer st = new StringTokenizer(string);
        List<String> list = new ArrayList<>();
        while(st.hasMoreTokens()){
            list.add(st.nextToken());
        }
        return list;
    }

    public static String parseRawStringToCuratedString(String string){
        String tmp = string.replaceAll("[^a-zA-Z0-9-]", " ");
        return tmp.replaceAll("[^0-9-]", " ");
    }
}
