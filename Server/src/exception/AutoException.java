package exception;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import adapter.FixAuto;

public class AutoException extends Exception implements FixAuto {
    
    private int errorNum;
    private String errorMsg;

    public AutoException(int num, String msg) {
        this.errorNum = num;
        this.errorMsg = msg;
    }
    
    public void exceptionLogger() {

        try {
            FileWriter fileWriter = new FileWriter("ExceptionLog.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String timeStamp = dateFormat.format(new Date());
            
            bufferedWriter.write("EXCEPTION #: " + this.errorNum + " | " + this.errorMsg + " | TIMESTAMP: " + timeStamp + "\n");
            bufferedWriter.close();
        } catch(IOException e) {

        }
    }
    
    public String fixException() {

        Fix1to100 f1 = new Fix1to100();
        
        switch(this.errorNum) {
            case 1: return f1.fix1();
            case 2: return f1.fix2();
            case 3: return f1.fix3();
            case 4: return f1.fix4();
            case 5: return f1.fix5();
        }
        return "";
        
    }
}
