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
class Solution_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode crt = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int s = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = s / 10;
            crt.next = new ListNode(s % 10);
            crt = crt.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return prev.next;
    }
}
