
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Tester{
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("Total unique IPs = " + la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(200);
        
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        
        ArrayList<String> record = la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14 = " + record);
        
        record = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30 = " + record);
        
        la.readFile("weblog1_log");
        
        record = la.uniqueIPVisitsOnDay("Mar 17");
        System.out.println("Mar 17 = " + record.size() +" Unique IP's");
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("countUniqueIPsInRange 200 - 299 = " + la.countUniqueIPsInRange(200, 299));
        System.out.println("countUniqueIPsInRange 300 - 399 = " + la.countUniqueIPsInRange(300, 399));
        
        la.readFile("weblog1_log");
        System.out.println("countUniqueIPsInRange 200 - 299 = " + la.countUniqueIPsInRange(200, 299));
    }
}
