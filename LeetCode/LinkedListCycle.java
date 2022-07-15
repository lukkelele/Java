import java.util.HashMap;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        HashMap<ListNode, Integer> hmap = new HashMap<ListNode, Integer>();

        ListNode node = head;
        int current_index = 0;

        while (node != null) {
            if (hmap.containsKey(node.next)) return true;
            if (node != null) hmap.put(node, current_index);
            current_index++;
            node = node.next;
        }
        return false;
    }
}



 //Definition for singly-linked list.
 class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
 
