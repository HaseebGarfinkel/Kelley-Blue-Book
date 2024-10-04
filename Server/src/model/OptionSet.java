package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
    
    private String setName;
    private ArrayList<Option> opt;
    private Option choice;

    public OptionSet() {
        this.setName = "";
        this.opt = new ArrayList<Option>();
    }

    public OptionSet(String inputName) {
        this.setName = inputName;
        this.opt = new ArrayList<Option>();
    }

    protected String getSetName() {
        return this.setName;
    }

    protected Option getOneOpt(int x) {
        return this.opt.get(x);
    }

    protected ArrayList<Option> getOpt() {
        return this.opt;
    }

    protected int getOptLength() {
        return this.opt.size();
    }

    protected String getOptName(int x) {
        return this.opt.get(x).getName();
    }

    protected float getOptPrice(int x) {
        return this.opt.get(x).getPrice();
    }

    protected Option getOptionChoice() {
        return this.choice;
    }

    protected void setOptionChoice(String optionName) {

        for (int i = 0; i < this.opt.size(); i++) {
            if (this.opt.get(i).getName().equals(optionName)) {
                this.choice = this.opt.get(i);
            }
        }
    }

    protected void setSetName(String newName) {
        this.setName = newName;
    }

    protected void setOneOpt(Option newOpt, int x) {
        this.opt.set(x, newOpt);
    }

    protected void setOpt(ArrayList<Option> newOpts) {
        this.opt = newOpts;
    }

    protected void setOptName(int x, String newOptName) {
        this.opt.get(x).setName(newOptName);
    }

    protected void setOptPrice(int x, float newOptPrice) {
        this.opt.get(x).setPrice(newOptPrice);
    }

    public void buildOption(int x, String name, float price) {
        this.opt.add(x, new Option(name, price));
    }

    protected void printData() {
        System.out.println(this.setName);
        System.out.println(this.opt);
    }

    protected void printOneOpt(int x) {
        System.out.println(this.opt.get(x).getName());
        System.out.println(this.opt.get(x).getPrice());
    }

    class Option implements Serializable {

        private String name;
        private float price;

        Option() {
            this.name = "";
            this.price = 0.0f;
        }

        Option(String inputName, float inputPrice) {
            this.name = inputName;
            this.price = inputPrice;
        }

        protected String getName() {
            return this.name;
        }

        protected float getPrice() {
            return this.price;
        }

        protected void setName(String newName) {
            this.name = newName;
        }

        protected void setPrice(float newPrice) {
            this.price = newPrice;
        }

        protected void printData() {
            System.out.println(this.name);
            System.out.println(this.price);
        }
    }
}
