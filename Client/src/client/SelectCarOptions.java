package client;

import java.util.Scanner;

import model.Automobile;

public class SelectCarOptions {
    
	private Scanner in = new Scanner(System.in);

	public void configureAuto(Object obj) {

	}

	public boolean isAutomobile(Object obj) {
        
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
