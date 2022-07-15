public class Palindrome {

    public boolean isPalindrome(int x){


        String xString = String.valueOf(x);
        char[] chars = xString.toCharArray();
        int x_length = xString.length();
        
        int k = 0;
        int j = xString.length() - 1;
        while (k < x_length) {
            char beginning = chars[k];
            char end = chars[j];
            k++;
            j--;
            if (beginning != end) {
                return false;
            }
        }
        return true;
    }
    
}
