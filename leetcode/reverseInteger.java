
public class reverseInteger {


    public static int reverse(int x) {

        int remainder, factor, reversed;
        int count = 0;
        factor = 0;
        remainder = 0;

        while (x != 0){
            if (x < 0){
                remainder = (x % 10);
                if (remainder == 0){
                    factor = x / 10;
                    break;
                } else {
                factor = -(x / 10);
                System.out.println("Remainder: "+remainder+"\n"+factor);
                break;
                }
            } else {
                remainder = x % 10;
                factor = x / 10;
                System.out.println(remainder+"\n"+factor);
                break;
            }
        }

        String remaind = String.valueOf(remainder);
        System.out.println("PRO "+remaind);
        String factr = String.valueOf(factor);
        int factr_length = factr.length();
        char[] characters = factr.toCharArray();
        char[] copiedCharacters = factr.toCharArray(); 
        if (copiedCharacters[0] == '-'){
            int steps = 1;
            //for (char c : copiedCharacters){
            while (copiedCharacters[factr_length-1] != '-'){
                char copyFirst = copiedCharacters[steps-1];
                char copySecond = copiedCharacters[steps];
                copiedCharacters[steps] = copyFirst;
                copiedCharacters[steps-1] = copySecond;
                steps++;
            }
        }

        count = factr.length();
        for (int k = 0 ; k < count ; k++){
            if (copiedCharacters[0] == '-'){
                continue;
            }
            characters[k] = copiedCharacters[count-1-k];

        }
        
        String value = String.valueOf(characters);
        if (factor == 0){
            System.out.println("Entered 0");
            reversed = Integer.parseInt(remaind);
        } else {
            System.out.println("Entered ELSE");
            if (remaind == "0"){
                reversed = Integer.parseInt(value);
            } else {
            reversed = Integer.parseInt(remaind+value);
            }
        }

        if (reversed >= Math.pow(2,31)-1 || reversed <= -Math.pow(2,31)){
            return 0;
        }
        else {    
            return reversed;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse(-10));
    }
}
