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

    static int threshold = 50;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String querry = "select zona.`Zone/Beat`, a.`Event Clearance Group`, a.`Event Clearance Description` "
        		+ "from zona inner join (select grupa.`Event Clearance Group`, zlocin.* from zlocin inner join grupa "
        		+ "where zlocin.`Event Clearance Code` = grupa.`Event Clearance Code`) as a where zona.`Hundred Block Location` = a.`Hundred Block Location`";
        String[] header = {"Zone/Beat", "Event Clearance Description", "At Scene Time"};
        new FPGrowth(querry, threshold, header);
        System.out.println("Ending time: " + (System.currentTimeMillis() - start));
    }
   
}
