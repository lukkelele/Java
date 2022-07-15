public class AddTwoNumbers {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){

        ListNode currentNode;
        char[] reverse_charsl1; 
        char[] reverse_charsl2; 
        int l1_amount = 0;
        int l2_amount = 0;

        currentNode = l1;
        while (currentNode != null) {
            l1_amount++;
            currentNode = currentNode.next;
        }

        currentNode = l2;
        while (currentNode != null) {
            l2_amount++;
            currentNode = currentNode.next;
        }
        // create char arrays 
        reverse_charsl1 = new char[l1_amount];
        reverse_charsl2 = new char[l2_amount];
        String number1 = "";
        String number2 = "";
        
        currentNode = l1;
        for (int k=0;k < l1_amount; k++){
            number1 += String.valueOf(currentNode.val);
            // move to next node
            currentNode = currentNode.next;
            if (currentNode == null){
                k = l1_amount;
            }
        }
        
        currentNode = l2;
        for (int k=0;k < l2_amount; k++){
            number2 += String.valueOf(currentNode.val);
            // move to next node
            currentNode = currentNode.next;
            if (currentNode == null){
                k = l2_amount;
            }
        }
        char[] number1_chars = number1.toCharArray();
        char[] number2_chars = number2.toCharArray();

        int i = 0;
        for (char c : number1_chars){
            reverse_charsl1[l1_amount-i-1] = c;
            i++;
        }
        int x = 0;
        
        System.out.println(number2_chars[0]);
        for (char c2 : number2_chars){
            
            //System.out.println(c);
            reverse_charsl2[l2_amount-x-1] = c2;
            x++;
        }
        // SUM THE NUMBERS FROM L1 and L2

        long sum1 = Integer.parseInt(String.valueOf(reverse_charsl1));
        long sum2 = Integer.parseInt(String.valueOf(reverse_charsl2));

        long sum3 = sum1 + sum2;
        
        // REVERSE THE NEW NUMBER
        long sum3bits = countBits(sum3);     // count how many integers
        // create string from sum3
        String sum3_string = String.valueOf(sum3);

        String reversed_sum3_string = reverse(sum3_string, sum3bits);
        char[] reversed_sum3_char = reversed_sum3_string.toCharArray();
        ListNode[] l3_array = new ListNode[(int)sum3bits];

        int a = 0;

        for (char c : reversed_sum3_char){
            int v = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(v);
            l3_array[a] = Node;
            a++;
        }

        //for (int k = 0 ; k < sum3bits ; k++) {
        for (int k = 0 ; k < sum3bits-1 ; k++) {
            if (k+1 >= sum3){
                break;
            }
            l3_array[k].next = l3_array[k+1];
        }

        for (ListNode node : l3_array){
        }
        // CREATE NEW LINKED LIST CONTAINING REVERSED SUM 3

        return l3_array[0];
    }
    
    static int countBits(long number){
        String convertedNumber = String.valueOf(number);
        char[] characters = convertedNumber.toCharArray();
        int c = 0;
        for (char character : characters){
            c++;
        }
        return c;
    }
    
    static int amountNodes(ListNode node) { // OLD
        int amount = 0;
        while (node != null) {
            amount++;
            node = node.next;
        }
        return amount;
    }
    
    static String reverse(String number_string, long amount){
        char[] number1_chars = number_string.toCharArray();
        int amount_int = (int)amount;
        char[] reverse_chars = new char[amount_int];
        int i = 0;
        for (char c : number1_chars){
            reverse_chars[amount_int-i-1] = c;
            i++;
        }
        String converted = String.copyValueOf(reverse_chars);
        return converted;
    }
    
}

/** 
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;
    }
    *//

    /** 
    
    public static void main(String[] args) {
        // construct lists
        // L1
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n1.next = n2;
    
        // L2
        ListNode m1 = new ListNode(6);
        ListNode m2 = new ListNode(9);
        m1.next = m2;
    
        //addTwoNumbers(n1, m1);
    }
    */
}
