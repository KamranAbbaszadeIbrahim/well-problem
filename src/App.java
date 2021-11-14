import model.Dataset;
import model.NormalizedDataset;
import service.implementation.SimpleAlgorithmImpl;
import utility.DataParser;
import utility.InputUtil;
import utility.Log;
import utility.OutputUtil;

import java.util.List;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        String path = "C:\\Users\\4LT4IR\\Documents\\WUT\\Advanced Algorithms\\Project\\App\\src\\test.txt";
        Dataset dataset = DataParser.parseListToDatasetObject(InputUtil.read(path));
        NormalizedDataset d = DataParser.parseDatasetToNormalizedDataset(dataset);
        OutputUtil.out(d, "\n");
        SimpleAlgorithmImpl s = new SimpleAlgorithmImpl(d.getH());
        List<Map<Integer,Double>> list = s.findAllDistancesFromEachWellToEachHouse(d);
        System.out.println(list);
        System.out.println("////////");
        System.out.println("Cheapest Distance: " + s.getSumOfDistances(list, d));
        Log log = new Log("text.txt");
        log.log("Salam");
        s.printResult();
    }
}
