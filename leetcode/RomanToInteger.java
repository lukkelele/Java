import java.util.HashMap;

public class RomanToInteger {

    public int romanToInt(String s) {
        // subtraction , 6 total instances with I , X , C
        
        HashMap<Character, Integer> numerals = new HashMap<Character, Integer>();
        int[] integer_values = {1, 5, 10, 50, 100, 500, 1000};
        char[] roman_characters = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        int int_index = 0;
        for (char character : roman_characters) {
            numerals.put(character, integer_values[int_index]);
            int_index++;
        }
        int s_length = s.length();
        char[] roman_number = s.toCharArray();
        int decimal_number = 0;

        int k = 0;
        int c = 0;
        //for (int k = 0 ; k < s_length ; k++){
        while (k < s_length){
            
            int sum = 0;
            char currentCharacter = roman_number[s_length-k-1];
            char pastCharacter = roman_number[s_length-k-2];
            int currentCharacter_val = numerals.get(currentCharacter);  
            int pastCharacter_val = numerals.get(pastCharacter);  
            System.out.println("Current char: "+currentCharacter+"\nPast char: "+pastCharacter+"\nCurrent char_val = "+currentCharacter_val+"\npastCharacter_val = "+pastCharacter_val);
            sum = (pastCharacter_val > currentCharacter_val) ? sum=(currentCharacter_val+pastCharacter_val) : (sum=(currentCharacter_val - pastCharacter_val)); 
            decimal_number += sum;
            System.out.println(decimal_number);
            k = k + 2;
            if (s_length-k-2 == -1){
                decimal_number += pastCharacter_val;
                break;
            }
            if (s_length-k-2 < 0){
                //decimal_number += pastCharacter_val;
                break;
            }
            pastCharacter_val = 0;
            currentCharacter_val = 0;
            }

        return decimal_number;
    }
    
}
