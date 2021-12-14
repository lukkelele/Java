public class AddTwoNumbersV2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode head = new ListNode(0);
        ListNode l3 = head;

        int carry = 0;
        
        while (l1 != null || l2 != null){
            
            int l1_val = (l1 != null) ? l1.val : 0;
            int l2_val = (l2 != null) ? l2.val : 0;

            int sum = (l1_val+l2_val+carry);
            carry = (sum) / 10;

            int digit = (sum) % 10;
            ListNode new_node = new ListNode(digit);

            l3.next = new_node;
            l3 = l3.next;
            if (l1 != null) l1 = l1.next;   // ListNode linked list already exists..
            if (l2 != null) l2 = l2.next;
            
        }
        if (carry > 0){
            ListNode lastnode = new ListNode(1);
            l3.next = lastnode;
        }
        return head.next;
        
    }
}
    
