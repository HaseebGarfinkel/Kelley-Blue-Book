package exception;

public class Fix1to100 {
    
    public String fix1() {
        System.out.println("NO FILE NAME DETECTED. DEFAULTING TO: FordZTW.txt");
        return "FordZTW.txt";
    }
    
    public String fix2() {
        System.out.println("NO MAKE DETECTED. DEFAULTING TO: Ford");
        return "Ford";
    }

    public String fix3() {
        System.out.println("NO MODEL DETECTED. DEFAULTING TO: Focus-Wagon-ZTW");
        return "Focus-Wagon-ZTW";
    }

    public String fix4() {
        System.out.println("NO BASE PRICE DETECTED. DEFAULTING TO: 18445");
        return "18445";
    }

    public String fix5() {
        System.out.println("NO SERIALIZE FILE DETECTED. DEFAULTING TO: FordZTW.ser");
        return "FordZTW.ser";
    }
}
