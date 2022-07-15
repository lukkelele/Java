import java.util.ArrayList;

    public class reverse {
        
        public static int reverseInteger(int x){

            ArrayList<Integer> reversed = new ArrayList<Integer>();
            int remainder, factor;
            boolean negative;

            if (x < 0) {
                negative = true;
                factor = -(x / 10);
                remainder = -x % 10; // to be placed FIRST
            }
            else {
                negative = false;
                factor = (x / 10);
                remainder = x % 10; // to be placed FIRST
            }

            String factorString = String.valueOf(factor);
            char[] chars = factorString.toCharArray();
            
            reversed.add(remainder);

            for (char c : chars) {
                int d = Integer.parseInt(String.valueOf(c));
                if (factorString.length() != 1 | factor != 0){
                    reversed.add(1, d);
                }
            }
            String line = "";
            if (negative){
                line += "-";
            }
            for (int i : reversed){
                
                line += String.valueOf(i);
            }

            try {
                int conversion = Integer.parseInt(line);
                return conversion;
            } catch (Exception e){
                return 0;
            }
        }
}
