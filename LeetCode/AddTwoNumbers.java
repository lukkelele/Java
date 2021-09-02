import java.util.ArrayList;

public class AddTwoNumbers {
    
    public static void addTwoNumbers(ListNode l1, ListNode l2){

        ListNode currentNode;
        String reverse_l1 = "";
        String reverse_l2 = "";
        char[] reverse_charsl1; 
        char[] reverse_charsl2; 
        int l1_amount = 1;
        int l2_amount = 1;
        int counter = 0;

        currentNode = l1;
        while (currentNode != null) {
            System.out.println("amountNodes1 - loop..");
            l1_amount++;
            currentNode = currentNode.next;
        }
        currentNode = l2;
        while (currentNode != null) {
            System.out.println("amountNodes2- loop..");
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
            number1 += String.valueOf(currentNode.val);
            // move to next node
            currentNode = currentNode.next;
            if (currentNode == null){
                k = l1_amount;
            }
        }
        System.out.println("Second list - string conversion!");
        currentNode = l2;
        for (int k=0;k < l2_amount; k++){
            number2 += String.valueOf(currentNode.val);
            // move to next node
            currentNode = currentNode.next;
            if (currentNode == null){
                k = l2_amount;
            }

        // REVERSE THE STRINGS
        System.out.println("Reversing strings");

        char[] number1_chars = number1.toCharArray();
        char[] number2_chars = number2.toCharArray();

        int i = 0;
        for (char c : number1_chars){
            System.out.println("character: "+c);
            reverse_charsl1[l1_amount-i-1] = c;
            i++;
        }
        i = 0;
        for (char c : number2_chars){
            System.out.println("character: "+c);
            //System.out.println(c);
            reverse_charsl2[l1_amount-i-1] = c;
            i++;
        }


        //Creating arrays for substitute instead of ArrayList
        ListNode[] l1_array = new ListNode[l1_amount];
        ListNode[] l2_array = new ListNode[l2_amount];

        int a = 0;

        for (char c : reverse_charsl1){
            int value = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(value);
            l1_array[a] = Node;
        }

        for (char c : reverse_charsl2){
            int value = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(value);
            l2_array[a] = Node;
        }

        int d = 0;
        while (d < l1_amount) {
            ListNode Node1 = l1_array[d];
            ListNode Node2 = l1_array[d+1];
            Node1.next = Node2;
            if ((d+1)==l1_amount) {
                break;
            }
            d++;
        }




        // Arraylist
        System.out.println("Creating arraylist");
        ArrayList<ListNode> list1 = new ArrayList<ListNode>();
        ArrayList<ListNode> list2 = new ArrayList<ListNode>();

        for (char c : reverse_charsl1){
            System.out.println("reversechars_l1 - entered");
            int value = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(value);
            list1.add(Node);
        }

        for (char c : reverse_charsl2){
            int value = Integer.parseInt(String.valueOf(c));
            ListNode Node = new ListNode(value);
            list2.add(Node);
        }

        int length_list1 = list1.size();
        int length_list2 = list2.size();

        while (counter<length_list1){
            
            ListNode Node1 = list1.get(counter);
            ListNode Node2 = list1.get(counter+1);
            Node1.next = Node2;
            if ((counter+1)==length_list1) {
                break;
            }
        }

        for (ListNode listnode : list1) {
            System.out.println("Printing listnode value: "+listnode.val);
        }

        reverse_l1 = String.valueOf(reverse_charsl1);
        reverse_l2 = String.valueOf(reverse_charsl2);
        // CONVERT TO INTEGER

        System.out.println(reverse_l1);
 

        int linkedlistValue_l1 = Integer.parseInt(number1);
        int linkedlistValue_l2 = Integer.parseInt(number2);

        // Sum the numbers

        int sum_numbers = linkedlistValue_l1 + linkedlistValue_l2;
        System.out.println("SUM OF NUMBERS: "+sum_numbers);
    }

    }

    public static void main(String[] args) {
        // construct lists
        // L1
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n1.next = n2;

        // L2
        ListNode m1 = new ListNode(6);
        ListNode m2 = new ListNode(2);
        m1.next = m2;

        addTwoNumbers(n1, m1);
    }


    static int amountNodes(ListNode listnode) { // OLD
        int count = 1;
        while (listnode.next != null) {
            System.out.println("amountNodes - loop..");
            count++;
        }
        return count;
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
