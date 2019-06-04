package fpgrowth;

import app.Prijava;

import app.Sucelje;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Prijava();  
        System.out.println("Ending time: " + (System.currentTimeMillis() - start));
    }
   
}
