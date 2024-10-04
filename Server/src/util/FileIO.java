package util;

import java.io.*;
import java.util.*;

import exception.*;
import model.*;

public class FileIO {
    
    private String fname;
    private boolean DEBUG = false;

    public FileIO() {
        this.fname = "";
    }

    public FileIO(String inputFname) {
        this.fname = inputFname;
    }

    public Automobile buildAutoObject() {

        try {

            try {
                if (this.fname.equals("")) {
                    throw new AutoException(1, "MISSING FILE NAME");
                }

            } catch(AutoException err) {
                err.exceptionLogger();
                this.fname = err.fixException();
            }
            

            FileReader file = new FileReader(this.fname);
            BufferedReader buff = new BufferedReader(file);

            String line1 = buff.readLine();
            StringTokenizer st1 = new StringTokenizer(line1);
            Automobile a1 = new Automobile();

            try {
                if(!st1.hasMoreTokens()) {
                    throw new AutoException(2, "MISSING MAKE");
                } else {
                    a1.setMake(st1.nextToken());
                }
            } catch(AutoException err) {
                err.exceptionLogger();
                a1.setMake(err.fixException());
            }

            try {
                if(!st1.hasMoreTokens()) {
                    throw new AutoException(3, "MISSING MODEL");
                } else {
                    a1.setModel(st1.nextToken());
                }
            } catch(AutoException err) {
                err.exceptionLogger();
                a1.setModel(err.fixException());
            }

            try {
                if(!st1.hasMoreTokens()) {
                    throw new AutoException(4, "MISSING BASE PRICE");
                } else {
                    a1.setBasePrice(Double.parseDouble(st1.nextToken()));
                }
            } catch(AutoException err) {
                err.exceptionLogger();
                a1.setBasePrice(Double.parseDouble(err.fixException()));
            }

            boolean eof = false;

            int lineNum = 0;
            int numOptionSets = 0;

            while (!eof) {


                String line = buff.readLine();
                lineNum += 1;
                if (DEBUG) {
                    System.out.println("READ LINE NUMBER " + lineNum);
                }

                if (line == null) {

                    eof = true;

                } else {

                    if (lineNum > 0) {

                        StringTokenizer st = new StringTokenizer(line);
                        if (DEBUG) {
                            System.out.println("TOKENIZED LINE NUMBER " + lineNum);
                        }

                        if (st.hasMoreTokens()) {
                            OptionSet optSet = new OptionSet(st.nextToken());                      
                            int counter = 0;

                            while(st.hasMoreTokens()) {
                                optSet.buildOption(counter, st.nextToken(), Float.parseFloat(st.nextToken()));
                                counter += 1;
                            }

                            a1.setOneOptSet(optSet, numOptionSets);
                            numOptionSets += 1;
                            if (DEBUG) {
                                System.out.println("CREATED OPTIONSET NUMBER " + numOptionSets);
                            }
                        
                        }
                    }
                }
            }
            buff.close();
            return a1;
            

        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
            return new Automobile();
        }
        
    }

    public String writeData(Automobile a1) {

        String serializeName = "FordZTW.ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(serializeName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(a1);
            out.close();
            fileOut.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return serializeName;
       
    }

    public Automobile readData(String serializeName) {

        Automobile newa1;

        try {
            if (serializeName.equals("")) {
                throw new AutoException(5, "MISSING SERIALIZE FILE NAME");
            }
        } catch (AutoException err) {
            err.exceptionLogger();
            serializeName = err.fixException();
        }

        try {
            FileInputStream fileIn = new FileInputStream(serializeName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newa1 = (Automobile) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println(e);
            newa1 = new Automobile();
        }

        return newa1;
    }

    public Automobile readProperties() {
        
        Properties props = new Properties();

        try {

            FileInputStream in = new FileInputStream(fname);
            props.load(in);
            
            
            String carMake = props.getProperty("CarMake");

            if (!carMake.equals(null)) {

                String carModel = props.getProperty("CarModel");
                Double carPrice = Double.parseDouble(props.getProperty("CarPrice"));
                String option1 = props.getProperty("Option1");
                String optionValue1a = props.getProperty("OptionValue1a");
                String optionValue1b = props.getProperty("OptionValue1b");
                String option2 = props.getProperty("Option2");
                String optionValue2a = props.getProperty("OptionValue2a");
                String optionValue2b = props.getProperty("OptionValue2b");

                Automobile a1 = new Automobile(carMake, carModel, carPrice);

                a1.setOptSetName(0, option1);
                a1.setOneOptSetOpt(0, 0, optionValue1a, 0.0f);
                a1.setOneOptSetOpt(0, 1, optionValue1b, 0.0f);

                a1.setOptSetName(1, option2);
                a1.setOneOptSetOpt(1, 0, optionValue2a, 0.0f);
                a1.setOneOptSetOpt(1, 1, optionValue2b, 0.0f);

                return a1;
            }
    

        } catch(IOException e) {

        }
       
        return null;

    }
}
