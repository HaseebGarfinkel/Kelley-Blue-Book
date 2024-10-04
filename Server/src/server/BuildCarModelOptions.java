package server;

import java.util.Properties;

import adapter.ProxyAutomobile;
import model.Automobile;

public class BuildCarModelOptions extends ProxyAutomobile {
    
    private static final int WAITING = 0;
    private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;
    private int state = WAITING;


    public Object processRequest(Object object) {

        Object toClient = null;

        if (state == REQUEST_BUILD_AUTO) {
            Properties p1 = (Properties) object;
            String make = p1.getProperty("CarMake");
            String model = p1.getProperty("CarModel");
            Double price = Double.parseDouble(p1.getProperty("CarPrice"));

            System.out.println(make);

            Automobile a1 = new Automobile(make, model, price);

            System.out.println("CREATED AUTOMOBILE");

            saveToLHM(a1);
            

            toClient = "Automobile succesfully created and added to database\nPress any button to return to menu";

        } else if (state == REQUEST_CONFIGURE_AUTO) {
            // configure auto
        } else {

        }

        this.state = WAITING;

        return toClient;
    }

    public String setRequest(int i) {
        String output = null;

        if (i == 1) {
            this.state = REQUEST_BUILD_AUTO;
            output = "Upload a file to create an Automobile";
        }

        else if (i == 2) {
            this.state = REQUEST_CONFIGURE_AUTO;
            output = "Select an Automobile to configure: \n" + getAllAutos();
            // display all automobiles in LHM

        } else {
            output = "Invalid request";
        }

        return output;

    }

}
