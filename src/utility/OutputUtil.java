package utility;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class OutputUtil {

    public static void out(String string){
        System.out.print(string);
    }

    public static void out(String string, String escapeSequence){
        System.out.printf(string+"%s", escapeSequence);
    }

    public static void out(Object object){
        System.out.print(object.toString());
    }

    public static void out(Object object, String escapeSequence){
        System.out.printf(object.toString()+"%s", escapeSequence);
    }

    public static void out(String format, Object object){
        System.out.println(String.format(format, object));
    }

    public abstract void out(String path, List<Object> list);

    public static void output(String string){
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("result.txt", false));
            stream.write(string.getBytes());
            stream.write(System.lineSeparator().getBytes());
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
