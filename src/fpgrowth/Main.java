/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fpgrowth;


/**
 *
 * @author Kamran
 */
public class Main {

    static int threshold = 10;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //String querry = "select a.`Zone/Beat`, zlocin.`Event Clearance Description`, zlocin.`At Scene Time` from zlocin inner join (select distinct `Hundred Block Location`, `Zone/Beat` from zona) as a where zlocin.`Hundred Block Location`= a.`Hundred Block Location`";
        String querry = "select zona.`Zone/Beat`, a.`Event Clearance Group`, a.`Event Clearance Description` from zona inner join (select grupa.`Event Clearance Group`, zlocin.* from zlocin inner join grupa where zlocin.`Event Clearance Code` = grupa.`Event Clearance Code`) as a where zona.`Hundred Block Location` = a.`Hundred Block Location`";
        int column_count = 3;
        new FPGrowth(querry, column_count, threshold);
        System.out.println("Ending time: " + (System.currentTimeMillis() - start));
    }
   
}
