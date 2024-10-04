package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;


public class Automobile implements Serializable {

    private String make;
    private String model;
    private double basePrice;
    private ArrayList<OptionSet> optSet;
    private ArrayList<Option> choices;

    public Automobile() {
        this.make = "";
        this.model = "";
        this.basePrice = 0.0;
        this.optSet = new ArrayList<OptionSet>();
    }

    public Automobile(String inputMake, String inputModel, double inputBasePrice) {
        this.make = inputMake;
        this.model = inputModel;
        this.basePrice = inputBasePrice;
        this.optSet = new ArrayList<OptionSet>();
    }

    public synchronized String getMake() {
        return this.make;
    }

    public synchronized String getModel() {
        return this.model;
    }

    public synchronized double getBasePrice() {
        return this.basePrice;
    }

    public synchronized OptionSet getOneOptSet(int x) {
        return this.optSet.get(x);
    }

    public synchronized ArrayList<OptionSet> getOptSet() {
        return this.optSet;
    }

    public synchronized int getOptSetLength() {
        return optSet.size();
    }

    public synchronized String getOptSetName(int x) {
        return optSet.get(x).getSetName();
    }

    public synchronized String getOptName(int x, int y) {
        return optSet.get(x).getOptName(y);
    }

    public synchronized float getOptPrice(int x, int y) {
        return optSet.get(x).getOptPrice(y);
    }

    public synchronized String getOptionChoice(String setName) {
        for (int i = 0; i < this.optSet.size(); i++) {
            if (this.optSet.get(i).getSetName().equals(setName)) {
                return this.optSet.get(i).getOptionChoice().getName();
            }
        }
        return "";
    }

    public synchronized float getOptionChoicePrice(String setName) {
        for (int i = 0; i < this.optSet.size(); i++) {
            if (this.optSet.get(i).getSetName().equals(setName)) {
                return this.optSet.get(i).getOptionChoice().getPrice();
            }
        }
        return 0.0f;
    }

    public synchronized float getTotalPrice() {
        float totalPrice = 0.0f;
        for (int i = 0; i < this.optSet.size(); i++) {
            totalPrice = totalPrice + this.optSet.get(i).getOptionChoice().getPrice();
        }
        return totalPrice;
    }

    public synchronized void setMake(String newMake) {
        this.make = newMake;
    }

    public synchronized void setModel(String newModel) {
        this.model = newModel;
    }

    public synchronized void setBasePrice(double newBasePrice) {
        this.basePrice = newBasePrice;
    }

    public synchronized void setOneOptSet(OptionSet newSet, int x) {
        this.optSet.add(x, newSet);
    } 

    public synchronized void setOptSet(ArrayList<OptionSet> newOptSet) {
        this.optSet = newOptSet;
    }

    public synchronized void setOptSetName(int x, String newSetName) {
        this.optSet.get(x).setSetName(newSetName);
    }

    public synchronized void setOptName(int x, int y, String newOptName) {
        this.optSet.get(x).setOptName(y, newOptName);
    }

    public synchronized void setOptPrice(int x, int y, float newOptPrice) {
        this.optSet.get(x).setOptPrice(y, newOptPrice);
    }

    public synchronized void setOneOptSetOpt(int x, int y, String newName, float newPrice) {
        this.optSet.get(x).buildOption(y, newName, newPrice);
    }

    public synchronized void setOptionChoice(String setName, String optionName) {
        for (int i = 0; i < this.optSet.size(); i++) {
            if (this.optSet.get(i).getSetName().equals(setName)) {
                this.optSet.get(i).setOptionChoice(optionName);
            }
        }
    }

    public synchronized void printMake() {
        System.out.println(this.make);
    }

    public synchronized void printModel() {
        System.out.println(this.model);
    }

    public synchronized void printBasePrice() {
        System.out.println(this.basePrice);
    }

    public synchronized void printMakeModel() {
        System.out.println(this.make + " " + this.model);
    }

    public synchronized void printOptSet() {
        System.out.println(this.optSet);
    }

    public synchronized void printOneOptSet(int x) {
        System.out.println(this.optSet.get(x));
    }

    public synchronized void printOneOpt(int x, int y) {
        System.out.println(this.optSet.get(x).getOneOpt(y));
    }

    public synchronized void printData() {
        for (int x = 0; x < this.optSet.size(); x++) {
            System.out.println("\n" + this.optSet.get(x).getSetName() + "\n");
            for (int y = 0; y < this.optSet.get(x).getOptLength(); y++) {
                System.out.println(this.optSet.get(x).getOptName(y));
                System.out.println(this.optSet.get(x).getOptPrice(y));
            }
        }

    }

    public synchronized void updateOptSetName(String optionSetName, String newSetName) {
        for (int i = 0; i < this.getOptSetLength(); i++) {
            if (this.getOptSetName(i).equals(optionSetName)) {
                this.setOptSetName(i, newSetName);
            }
        }
    }

    public synchronized void updateOptName(String optionSetName, String option, String newName) {
        for (int i = 0; i < this.optSet.size(); i++) {
            if (this.getOptSetName(i).equals(optionSetName)) {
                for (int j = 0; j < this.optSet.get(i).getOptLength(); j++) {
                    if (this.getOptName(i, j).equals(option)) {
                        this.setOptName(i, j, newName);
                    }
                }
            }
        }
    }

    public synchronized void updateOptPrice(String optionSetName, String option, float newPrice) {
        for (int i = 0; i < this.optSet.size(); i++) {
            if (this.getOptSetName(i).equals(optionSetName)) {
                for (int j = 0; j < this.optSet.get(i).getOptLength(); j++) {
                    if (this.getOptName(i, j).equals(option)) {
                        this.setOptPrice(i, j, newPrice);
                    }
                }
            }
        }
    }

    public synchronized void deleteOptSet(int x) {
        OptionSet[] newOptSet = new OptionSet[this.optSet.size() - 1];
        for (int i = 0; i < this.optSet.size(); i++) {
            int counter = 0;
            if (i != x) {
                newOptSet[counter] = this.optSet.get(counter);
                counter++;
            }
        }
    }
}
