public class AddTwoNumbers {
    
    public static void addTwoNumbers(ListNode l1, ListNode l2){

        ListNode currentNode;
        String reverse_l1 = "";
        String reverse_l2 = "";
        char[] reverse_charsl1; 
        char[] reverse_charsl2; 
        int l1_amount = 0;
        int l2_amount = 0;
        int counter = 0;

        currentNode = l1;
        while (currentNode != null) {
            System.out.println("currentnode val1 - "+currentNode.val);
            l1_amount++;
            currentNode = currentNode.next;
        }

        currentNode = l2;
        while (currentNode != null) {
            System.out.println("currentnode val2 - "+currentNode.val);
            l2_amount++;
            currentNode = currentNode.next;
        }

        // create char arrays 
        reverse_charsl1 = new char[l1_amount];
        reverse_charsl2 = new char[l2_amount];

        String number1 = "";
        String number2 = "";
        
        System.out.println("Amount of nodes: l1 = "+l1_amount+" and l2 = "+l2_amount);
        
        currentNode = l1;
        for (int k=0;k < l1_amount; k++){
            System.out.println("currentnode number1string - "+currentNode.val);
            number1 += String.valueOf(currentNode.val);
            // move to next node
            currentNode = currentNode.next;
            if (currentNode == null){
                k = l1_amount;
            }
        }
        
        currentNode = l2;
        for (int k=0;k < l2_amount; k++){
            System.out.println("currentnode number1string - "+currentNode.val);
            number2 += String.valueOf(currentNode.val);
            // move to next node
            currentNode = currentNode.next;
            if (currentNode == null){
                k = l2_amount;
            }
        }
        
        System.out.println("STRINGS ==== number1 ="+number1+"\n"+"number2= "+number2+"\nL1 amount= "+l1_amount+"\nL2 amount= "+l2_amount);
        // REVERSE THE STRINGS
        System.out.println("Reversing strings");

        char[] number1_chars = number1.toCharArray();
        char[] number2_chars = number2.toCharArray();

        int i = 0;
        for (char c : number1_chars){
            System.out.println("character in number1: "+c);
            reverse_charsl1[l1_amount-i-1] = c;
            i++;
        }
        int x = 0;
        
        System.out.println(number2_chars[0]);
        for (char c2 : number2_chars){
            
            System.out.println("character in number2: "+c2);
            //System.out.println(c);
            reverse_charsl2[l2_amount-x-1] = c2;
            x++;
        }
        System.out.println("Creating ListNode arrays..");
        //Creating arrays for substitute instead of ArrayList
        ListNode[] l1_array = new ListNode[l1_amount];
        ListNode[] l2_array = new ListNode[l2_amount];
        //ListNode[] l3_array = new ListNode[]

        int a = 0;

        for (char c : reverse_charsl1){
            //System.out.println("1. a= "+a);
            int value = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(value);
            l1_array[a] = Node;
            System.out.println(l1_array[a].val);
            a++;
        }

        a = 0;
        for (char c : reverse_charsl2){
            //System.out.println("2. a= "+a);
            int value = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(value);
            l2_array[a] = Node;
            System.out.println(l2_array[a].val);
            a++;
        }


        // SUM THE NUMBERS FROM L1 and L2

        int sum1 = Integer.parseInt(String.valueOf(reverse_charsl1));
        int sum2 = Integer.parseInt(String.valueOf(reverse_charsl2));

        System.out.println("Sum1 = "+sum1);
        System.out.println("Sum2 = "+sum2);
        
        int sum3 = sum1 + sum2;
        
        System.out.println("Sum3 = "+sum3);

        // REVERSE THE NEW NUMBER
        int sum3bits = countBits(sum3);     // count how many integers
        // create string from sum3
        String sum3_string = String.valueOf(sum3);

        System.out.println(sum3bits);

        String reversed_sum3_string = reverse(sum3_string, sum3bits);
        int reversed_sum3 = Integer.parseInt(reversed_sum3_string);
    
        char[] reversed_sum3_char = reversed_sum3_string.toCharArray();

        ListNode[] l3_array = new ListNode[sum3bits];
        

        a=0;

        for (char c : reversed_sum3_char){
            int v = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(v);
            l3_array[a] = Node;
            System.out.println(l3_array[a].val);
            System.out.println("a= "+a);
            a++;

        }

        System.out.println("Setting next\n");
        //for (int k = 0 ; k < sum3bits ; k++) {
        for (int k = 0 ; k < sum3bits-1 ; k++) {
            if (k+1 >= sum3){
                break;
            }
            l3_array[k].next = l3_array[k+1];
            System.out.println(l3_array[k].val);
            System.out.print("NEXT VAL: "+l3_array[k].next.val+"\n");
        }

        for (ListNode node : l3_array){
            System.out.println("Node value: "+node.val);
        }

        // CREATE NEW LINKED LIST CONTAINING REVERSED SUM 3





        // SET NEXT FOR EACH NODE.. 
        // 
        for (int k = 0 ; k < l1_amount ; k++) {
            l1_array[k].next = l1_array[k+1];
            if (l1_amount == 2){
                l1_array[0].next = l1_array[1];
                break;
            }
        }

        for (int k = 0 ; k < l1_amount ; k++) {
            l2_array[k].next = l2_array[k+1];
            if (l2_amount == 2){
                l2_array[0].next = l2_array[1];
                break;
            }
        }

        

        // CONVERT TO INTEGER

    }

    
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

        addTwoNumbers(n1, m1);
    }





    static int countBits(int number){
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
            System.out.println("currentnode val1 - "+node.val);
            amount++;
            node = node.next;
        }
        return amount;
    }

    static String reverse(String number_string, int amount){
        char[] number1_chars = number_string.toCharArray();
        char[] reverse_chars = new char[amount];
        int i = 0;
        for (char c : number1_chars){
            System.out.println("character in number1: "+c);
            reverse_chars[amount-i-1] = c;
            i++;
        }
        String converted = String.copyValueOf(reverse_chars);
        return converted;
    }



}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    int getVal(){
        return this.val;
    }

}
