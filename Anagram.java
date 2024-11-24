package Anagram;
import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first string: ");
        String firstString = scanner.nextLine();
        
        System.out.println("Enter the second string: ");
        String secondString = scanner.nextLine();
        
        boolean anagram=areAnagrams(firstString, secondString);
        if (anagram){
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are not anagrams.");
        }
    }
    
    public static boolean areAnagrams(String string1, String string2){
        string1 = string1.replaceAll("\\s","").toLowerCase();
        string2 = string2.replaceAll("\\s","").toLowerCase();
        if (string1.length()!=string2.length()) {
            return false;
        }
        char[] charArray1=string1.toCharArray();
        char[] charArray2=string2.toCharArray();
        Arrays.sort(charArray1);  
        Arrays.sort(charArray2);  
        return Arrays.equals(charArray1, charArray2);
    }
}



