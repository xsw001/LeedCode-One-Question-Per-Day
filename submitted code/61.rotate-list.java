//
// @lc app=leetcode.cn id=61 lang=java
//
// [61] rotate-list
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)
            return head;
        ListNode p = head;
        int len = 1;
        while(p.next != null){
            ++len;
            p = p.next;
        }
        k = k % len;
        if(k == 0)
            return head;
        p.next = head;
        p = head;
        int index = 1;
        while(index < len - k){
            ++index;
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }
}
// @lc code=end