package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class InputUtil {

    public static List<String> read(String path){

        List<String> list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            for(String line; (line = br.readLine()) != null; ) {
                list.add(DataParser.parseRawStringToCuratedString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
