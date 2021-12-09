import model.Dataset;
import model.NormalizedDataset;
import service.Service;
import utility.DataParser;
import utility.InputUtil;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App{
    private static NormalizedDataset normalizedDataset;
    private static String path;

    public static void main(String[] args) {
        path = "input.txt";
        onCreate(path);

        Service service = new Service(normalizedDataset);
        service.run();
    }

    private static void onCreate(String path){
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("technical_logs.txt", false));
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Dataset dataset = DataParser.parseListToDatasetObject(InputUtil.read(path));
        normalizedDataset = DataParser.parseDatasetToNormalizedDataset(dataset);
    }
}
