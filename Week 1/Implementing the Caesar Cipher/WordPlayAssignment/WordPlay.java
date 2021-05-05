
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        
        if (ch == 'a' || ch == 'i' || ch == 'u' || ch == 'e' || ch == 'o') {
            return true;
        }
        
        return false;
    }
    
    public void testIsVowel() {
        char ch = 'S';
        System.out.println(ch + " is a vowel = " + isVowel(ch));
        
        ch = 'U';
        System.out.println(ch + " is a vowel = " + isVowel(ch));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < phrase.length(); i++) {
            char currentCharacter = phrase.charAt(i);
            
            if (isVowel(currentCharacter)) {
                sb.append(ch);
            } else {
                sb.append(currentCharacter);
            }
        }
        
        return sb.toString();
    }
    
    public void testReplaceVowels() {
        String phrase = "This is my very first phrase";
        String phrase2 = "Hello World";
        
        char ch = '*';
        System.out.println(phrase + " -> " + replaceVowels(phrase, ch));
        System.out.println(phrase2 + " -> " + replaceVowels(phrase2, ch));
        
    }
    
    
    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder();
        
        for (int i = 0; i < phrase.length(); i++) {
            char currentCharacter = phrase.charAt(i);
            
            if (Character.toLowerCase(currentCharacter) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    newPhrase.append('*');
                } else {
                    newPhrase.append('+');
                }
            } else {
                newPhrase.append(currentCharacter);
            }
        }
        
        return newPhrase.toString();
    }
    
    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(phrase + " with " + ch + " replaced become = " + emphasize(phrase, ch));
        
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        System.out.println(phrase + " with " + ch + " replaced become = " + emphasize(phrase, ch));
    }
}
