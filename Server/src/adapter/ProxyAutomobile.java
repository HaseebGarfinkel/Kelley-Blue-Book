package adapter;

import java.util.concurrent.locks.*;

import model.*;
import scale.*;
import server.*;
import util.*;

public abstract class ProxyAutomobile {
    
    private static LHMAuto<Automobile> l1 = new LHMAuto<Automobile>();

    public void buildAuto(String fileName, String fileType) {

        FileIO f1 = new FileIO(fileName);

        if (fileType.equals("txt")) {

            Automobile a1 = f1.buildAutoObject();
            l1.add(a1.getMake() + " " + a1.getModel(), a1);

        } else if (fileType.equals("properties")) {

            Automobile a1 = f1.readProperties();
            l1.add(a1.getMake() + " " + a1.getModel(), a1);

        } else {

            System.out.println("Error: Unknown file type given.");
            
        }

    }

    public void printAuto(String modelName) {
        
        Automobile a1 = l1.find(modelName);
        System.out.print("\nMake: ");
        a1.printMake();
        System.out.print("Model: ");
        a1.printModel();
        System.out.print("Baseprice: ");
        a1.printBasePrice();
        a1.printData();

    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {

        Automobile a1 = l1.find(modelName);
        a1.updateOptSetName(optionSetName, newName);

    }

    public void updateOptionPrice(String modelName, String optionSetName, String option, float newPrice) {

        Automobile a1 = l1.find(modelName);
        a1.updateOptPrice(optionSetName, option, newPrice);
        
    }

    public String getOptionChoice(String modelName, String setName) {

        Automobile a1 = l1.find(modelName);
        return a1.getOptionChoice(setName);

    }

    public float getOptionChoicePrice(String modelName, String setName) {

        Automobile a1 = l1.find(modelName);
        return a1.getOptionChoicePrice(setName);

    }

    public void setOptionChoice(String modelName, String setName, String optionName) {

        Automobile a1 = l1.find(modelName);
        a1.setOptionChoice(setName, optionName);

    }

    public void editOption(String modelName, String optionSetName, String oldName, String newName) {

        Automobile a1 = l1.find(modelName);

        Lock l2 = new ReentrantLock();
        l2.lock();

        try {
            EditOptions e1 = new EditOptions(a1, optionSetName, oldName, newName);
            Thread t1 = new Thread(e1);
            t1.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        
        l2.unlock();
        
    }

    public void saveToLHM(Automobile a1) {

        l1.add(a1.getMake() + " " + a1.getModel(), a1);

    }

    public String getAllAutos() {
        
        return l1.iterate();

    }
}
