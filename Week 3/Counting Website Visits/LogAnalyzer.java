
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
         
         WebLogParser parser = new WebLogParser();
         
         for (String line : fr.lines()) {
             
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
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> ipVisitCounts = new HashMap<String, Integer>();
         
         for (LogEntry logEntry : records) {
            String ipAddr = logEntry.getIpAddress();
            
            if (!ipVisitCounts.containsKey(ipAddr)) {
                ipVisitCounts.put(ipAddr, 1);
            } else {
                ipVisitCounts.put(ipAddr, ipVisitCounts.get(ipAddr) + 1);
            }
         }
         
         return ipVisitCounts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
         int maxVisits = Integer.MIN_VALUE;
         
         for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);
            
            if (currentCount > maxVisits) {
                maxVisits = currentCount;
            }
         }
         
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
         ArrayList<String> ipAddrList = new ArrayList<String>();
         int maxVisits = mostNumberVisitsByIP(ipCounts);
         
         for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);
            
            if (currentCount == maxVisits) {
                ipAddrList.add(key);
            }
         }
         
         return ipAddrList;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry logEntry : records) {
             String accessTime = logEntry.getAccessTime().toString();
             String day = accessTime.substring(4, 10);
             String currIp = logEntry.getIpAddress();
             ArrayList<String> ipList;
             
             if (!ipsPerDay.containsKey(day)) {
                 ipList = new ArrayList<String>();
             } else {
                 ipList = ipsPerDay.get(day);
             }
             
             ipList.add(currIp);
             ipsPerDay.put(day, ipList);
         }
         
         return ipsPerDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsPerDay) {
         String day = "";
         int maxVisit = Integer.MIN_VALUE;
         
         for (String key : ipsPerDay.keySet()) {
            int currentVisit = ipsPerDay.get(key).size();
            
            if (currentVisit > maxVisit) {
                maxVisit = currentVisit;
                day = key;
            }
         }
         
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDay, String day) {
        ArrayList<String> ipList = ipsPerDay.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        
        for (String ip : ipList) {
             if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }
        
        return iPsMostVisits(ipCounts);
     }
}
