//
// @lc app=leetcode.cn id=147 lang=java
//
// [147] insertion-sort-list
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode();
        ListNode ptr = new ListNode();
        dummy.next = head;
        ptr = head;
        ListNode red = new ListNode();
        red = head.next;
        ptr.next = null;
        while (red != null) {
            if (red.val >= ptr.val) {
                ptr.next = red;
                red = red.next;
                ptr = ptr.next;
                ptr.next = null;
            } else if (red.val <= head.val) {
                ListNode cur = red;
                red = red.next;
                cur.next = head;
                dummy.next = cur;
                head = cur;
            } else {
                ListNode temp = head;
                while (temp.next.val < red.val)
                    temp = temp.next;
                ListNode cur = red;
                red = red.next;
                cur.next = temp.next;
                temp.next = cur;
            }
        }
        return head;
    }
}
// @lc code=end