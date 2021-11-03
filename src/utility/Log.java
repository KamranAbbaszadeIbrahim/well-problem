package utility;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log {
    private String path;
    private byte [] separator;

    public Log(String path) {
        this.path = path;
        this.separator = System.lineSeparator().getBytes();
    }

    public void log(String string){
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
            stream.write((LocalDateTime.now() + " :\t" + string).getBytes());
            stream.write(separator);
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
