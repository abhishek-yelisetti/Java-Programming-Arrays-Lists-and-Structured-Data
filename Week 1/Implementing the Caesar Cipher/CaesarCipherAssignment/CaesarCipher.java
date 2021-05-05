
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key) {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabets.substring(key) + alphabets.substring(0, key);
        StringBuilder encryptedMsg = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            int index = alphabets.toLowerCase().indexOf(Character.toLowerCase(curr));
            
            if (index != -1) {
                if (Character.isLowerCase(curr)) {
                    encryptedMsg.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMsg.append(shiftedAlphabet.charAt(index));
                }
            } else {
               encryptedMsg.append(curr);
            }
        }
        
        return encryptedMsg.toString();
    }
    
    public void testCaesar() {
       // FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        
        String encryptedMsg = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encryptedMsg);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String firstShiftedAlphabet = alphabets.substring(key1) + alphabets.substring(0, key1);
        String secondShiftedAlphabet = alphabets.substring(key2) + alphabets.substring(0, key2);
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabets.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));
            
            if (index != -1) {
                String shiftedAlphabet;
                
                if (i % 2 == 0) {
                    shiftedAlphabet = firstShiftedAlphabet;
                } else {
                    shiftedAlphabet = secondShiftedAlphabet;
                }
                
                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }
        
        return encryptedMessage.toString();
    }
    
    public void testEncryptTwoKeys() {
        String message = "First Legion";
        int key1 = 23;
        int key2 = 17;
        System.out.println(message + " -> " + encryptTwoKeys(message, key1, key2));
        
        message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        key1 = 8;
        key2 = 21;
        System.out.println(message + " -> " + encryptTwoKeys(message, key1, key2));
    }
}