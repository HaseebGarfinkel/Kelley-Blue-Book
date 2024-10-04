package client;

import java.io.*;
import java.util.Properties;

public class CarModelOptionsIO {
    
    public Object loadPropertiesFile(String fname) {

        Properties props = new Properties();

        try {
            props.load(new FileInputStream(fname));
        } catch (Exception e) {

        }

        return props;
    }

    public Object loadTextFile(String fname) {
        
        StringBuffer sbuff = new StringBuffer();

        try {

            BufferedReader buff = new BufferedReader(new FileReader(fname));
            boolean eof = false;
            int counter = 0;

            while(!eof) {
                String line = buff.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    if (counter == 0) {
                        sbuff.append(line);
                    } else {
                        sbuff.append("\n" + line);
                    }
                }
                counter++;
            }
            buff.close();

        } catch (Exception e) {

        }
        
        return sbuff;
    }
}
