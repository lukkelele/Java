import ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 // stored in reverse order

public class addTwoNumbers {

    

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // l1 = [3, 4, 9]
        // l2 = [4, 1, 0]
        String values_L1 = "";
        while (l1.next != null){
            String value_L1 = String.valueOf(l1.val);
            values_L1 += value_L1;
        }
        
        String values_L2 = "";
        while (l2.next != null){
            String value_L2 = String.valueOf(l2.val);
            values_L2 += value_L2;
        }

        char[] chars_l1 = values_L1.toCharArray();
        char[] chars_l2 = values_L2.toCharArray();

        int length_l1 = values_L1.length();
        int copy_l1 = values_L1.length();
        char[] list1 = new char[length_l1];
        
        int length_l2 = values_L2.length();
        int copy_l2 = values_L2.length();
        char[] list2 = new char[length_l2];

        for (int k = 0 ; k < length_l1 ; k++) {
            list1[k] = chars_l1[length_l1-k-1];
        }
        for (int k = 0 ; k < length_l2 ; k++) {
            list1[k] = chars_l2[length_l2-k-1];
        }

        String L1_string = "";
        int counter_l1 = 0;
         for (int k = 0 ; k < length_l1 ; k++) {
            if (length_l1-1 == k) {
                ListNode newNode = new ListNode(Integer.parseInt(String.valueOf(list1[k])));
            } else {
                ListNode newNode = new ListNode(Integer.parseInt(String.valueOf(list1[k])));
                newNode.next = new ListNode(Integer.parseInt(String.valueOf(list1[k+1])));
            }

        String L2_string = "";

        for (char c : list2) {
            L2_string += c;
        }

        int sum_L1 = Integer.parseInt(L1_string);
        int sum_L2 = Integer.parseInt(L2_string);

        System.out.println((sum_L1 + sum_L2));

        return null;
    }

    
    }
}
