# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        prev = ListNode(0)
        crt = prev
        carry = 0
        while l1 is not None or l2 is not None  or carry != 0:
           s = (l1.val if l1 is not None else 0) + (l2.val if l2 is not None else 0) + carry
           carry = s // 10
           crt.next = ListNode(s % 10)
           crt = crt.next
           l1 = l1.next if l1 is not None else None
           l2 = l2.next if l2 is not None else None
        return prev.next