//
// @lc app=leetcode.cn id=82 lang=java
//
// [82] remove-duplicates-from-sorted-list-ii
//
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
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = head;
        ListNode cur = dummy;
        cur.next = null;
        while(p != null){
            ListNode temp = p.next;
            while(temp != null && p.val == temp.val)
                temp = temp.next;
            if(p.next == temp){
                cur.next = p;
                cur = cur.next;
                cur.next = null;
            }
            p = temp;
        }
        return dummy.next;
    }
}
// @lc code=end