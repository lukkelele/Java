import java.util.Scanner;

public class sumcheck {

    private static int count = 1;
    private static Scanner user_input = new Scanner(System.in);
    static boolean tick;

    public static void main(String[] args) {

        String sum1;
        String sum2;

        print();
        sum1 = user_input.nextLine();
        print();
        sum2 = user_input.nextLine();
        compare(sum1, sum2);

        if (tick == true) {
            System.out.println("SIGNATURE: VERIFIED");
        } else if (tick == false) {
            System.out.println("SIGNATURE: NOT VERIFIED");
        }
    }

    private static void print() {
        System.out.println("enter sum" + count);
        count++;
    }

    private static boolean compare(String sum1, String sum2) {

        boolean flag = true;
        tick = true;
        while (flag == true) {
            int sum1_length = sum1.length();
            int sum2_length = sum2.length();
            if (sum1_length != sum2_length) {
                System.out.println("Length of sums is not equal!\n");
                tick = false;
                return tick;
            } else {
                String checkedSum1 = "";
                String checkedSum2 = "";
                for (int i = 0; i < sum1_length; i++) {
                    char c1 = sum1.charAt(i);
                    char c2 = sum2.charAt(i);
                    String converted_c1 = Character.toString(c1);
                    String converted_c2 = Character.toString(c2);
                    checkedSum1 += converted_c1;
                    checkedSum2 += converted_c2;
                    if (c1 != c2) {
                        System.out.println("Checked sums until first error:\nSum1: " + checkedSum1 + " <--\nSum2: "
                                + checkedSum2 + " <--");
                        return tick = false;
                    }
                }
                flag = false;
            }
        }
        return tick;
    }
}