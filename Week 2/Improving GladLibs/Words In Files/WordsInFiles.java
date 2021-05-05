
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFreq;
    
    public WordsInFiles() {
        wordFreq = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fileResource = new FileResource(f);
        
        for (String word : fileResource.words()) {
            if (wordFreq.containsKey(word)) {
                String currentFileName = f.getName();
                ArrayList<String> fileName = wordFreq.get(word);
                
                if (!fileName.contains(currentFileName)) {
                    fileName.add(currentFileName);
                    wordFreq.put(word, fileName);
                }
            } else {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                wordFreq.put(word, fileList);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordFreq.clear();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxCount = Integer.MIN_VALUE;
        
        for (String key : wordFreq.keySet()) {
            int curr = wordFreq.get(key).size();
            
            if (curr > maxCount) {
                maxCount = curr;
            }
        }
        
        return maxCount;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        
        for (String key : wordFreq.keySet()) {
            int currentCount = wordFreq.get(key).size();
            
            if (currentCount == number) {
                wordList.add(key);
            }
        }
        
        return wordList;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> fileList = wordFreq.get(word);
        
        for(String fname : fileList) {
            System.out.println(fname);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        
        int maxOccurences = maxNumber();
        
        System.out.println("Maximum number of files = " + maxOccurences);
        
        ArrayList<String> wordList = wordsInNumFiles(maxOccurences);
        System.out.println("\nWords that are in the maximum number of files:");
        
        for(String word : wordList) {
            System.out.println(word);
            System.out.println(word + " is contained in files:");
            printFilesIn(word);
            System.out.println();
        }
        
        System.out.println("Total words in 4 files = " + wordsInNumFiles(4).size());
        printFilesIn("red");
    } 
}