
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class LogAnalyzer{
    
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         
         for (String line : fr.lines()) {
             WebLogParser parser = new WebLogParser();
             LogEntry newEntry = parser.parseEntry(line);
             records.add(newEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry logEntry : records) {
             System.out.println(logEntry);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for (LogEntry logEntry : records) {
            if (!uniqueIps.contains(logEntry.getIpAddress())) {
                uniqueIps.add(logEntry.getIpAddress());
            }
         }
         
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() > num) {
                System.out.println(logEntry);
            }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for (LogEntry logEntry : records) {
            if (logEntry.getAccessTime().toString().contains(someday)) {
                if (!uniqueIps.contains(logEntry.getIpAddress())) {
                    uniqueIps.add(logEntry.getIpAddress());                
                }
            }
         }
         
         return uniqueIps;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() >= low && logEntry.getStatusCode() <= high) {
                if (!uniqueIps.contains(logEntry.getIpAddress())) {
                    uniqueIps.add(logEntry.getIpAddress());
                }
            }
         }
         
         return uniqueIps.size();
     }
}