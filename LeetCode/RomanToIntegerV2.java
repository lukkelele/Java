import java.util.HashMap;

public class RomanToIntegerV2 {

    public int romanToInt(String s) {

        char currentCharacter;
        char pastCharacter;
        int pastCharacter_val = 0;
        int currentCharacter_val = 0;
        HashMap<Character, Integer> numerals = new HashMap<Character, Integer>();
        HashMap<Character, Integer> subtraction_charMap = new HashMap<Character, Integer>();
        int[] integer_values = {1, 5, 10, 50, 100, 500, 1000, 0};
        char[] roman_characters = {'I', 'V', 'X', 'L', 'C', 'D', 'M', 'n'};
        int[] subtraction_values = {0, -2, -2, -20, -20, -200, -200, 0};

        int int_index = 0;
        for (char character : roman_characters) {
            numerals.put(character, integer_values[int_index]);
            subtraction_charMap.put(character, subtraction_values[int_index]);
            int_index++;
        }
        pastCharacter = roman_characters[7]; // n = 0
        int s_length = s.length();
        char[] roman_number = s.toCharArray();
        int decimal_number = 0;
        int k = 0;

        while (k < s_length){
            int sum = 0;
            if (s_length-k-2 == -1){
                currentCharacter = roman_number[s_length-k-1];
                currentCharacter_val = numerals.get(currentCharacter);
                if (pastCharacter_val>currentCharacter_val){
                    int lastChar_val = subtraction_charMap.get(pastCharacter);
                    sum = lastChar_val;
                }
                pastCharacter_val = 0;
            } else if (k==0) {
                currentCharacter = roman_number[s_length-k-1];
                pastCharacter = roman_number[s_length-k-2];
                currentCharacter_val = numerals.get(currentCharacter);  
                pastCharacter_val = numerals.get(pastCharacter);
            } else {
                currentCharacter = roman_number[s_length-k-1];
                currentCharacter_val = numerals.get(currentCharacter);  
                if (pastCharacter_val>currentCharacter_val){
                    int lastChar_val = subtraction_charMap.get(pastCharacter);
                    sum = lastChar_val;
                }
                pastCharacter = roman_number[s_length-k-2];
                pastCharacter_val = numerals.get(pastCharacter);
            }
            
            if (pastCharacter_val < currentCharacter_val){
                decimal_number += (currentCharacter_val-pastCharacter_val)+sum;
            } else {
                decimal_number += (currentCharacter_val+pastCharacter_val+sum);
            }
            
            k += 2;
        }
        return decimal_number;
    }
}