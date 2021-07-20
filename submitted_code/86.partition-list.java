//
// @lc app=leetcode.cn id=86 lang=java
//
// [86] partition-list
//
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode X = head;
        ListNode smaller = dummy;
        while (X!=null && X.val < x) {
            X = X.next;
            smaller = smaller.next;
        }
        ListNode i = X;
        ListNode pre = smaller;
        ListNode temp;
        while (i != null) {
            if (i.val < x) {
                temp = i;
                pre.next = i.next;
                i = pre.next;
                temp.next = X;
                smaller.next = temp;
                smaller = temp;
            } else {
                i = i.next;
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}
// @lc code=end