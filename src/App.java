import model.Dataset;
import model.NormalizedDataset;
import service.Service;
import utility.DataParser;
import utility.InputUtil;

public class App{
    public static void main(String[] args) {
        String path = "C:\\Users\\4LT4IR\\Documents\\WUT\\Advanced Algorithms\\Project\\App\\src\\test.txt";
        Dataset dataset = DataParser.parseListToDatasetObject(InputUtil.read(path));
        NormalizedDataset d = DataParser.parseDatasetToNormalizedDataset(dataset);
        Service service = new Service(d);
        service.run();
    }
}
