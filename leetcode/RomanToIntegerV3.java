import java.util.HashMap;

public class RomanToIntegerV3 {

    public int romanToInt(String s) {

        HashMap<Character, Integer> numerals = new HashMap<Character, Integer>();
        HashMap<Character, Integer> subtraction_charMap = new HashMap<Character, Integer>();
        int[] integer_values = {1, 5, 10, 50, 100, 500, 1000};
        char[] roman_characters = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] subtraction_values = {0, -2, -2, -20, -20, -200, -200};

        int int_index = 0;
        for (char character : roman_characters) {
            numerals.put(character, integer_values[int_index]);
            subtraction_charMap.put(character, subtraction_values[int_index]);
            int_index++;
        }

        char[] chars = s.toCharArray();
        int n = s.length(), i = 0, current, next, sum = 0;

        while (i < n){
            current = getNumber(chars[i]);
        }




        return 0;
    }

    static int getNumber(char c){
        

        return Integer.parseInt(String.valueOf(c));
    }
}