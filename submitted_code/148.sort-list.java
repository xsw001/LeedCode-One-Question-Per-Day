//
// @lc app=leetcode.cn id=148 lang=java
//
// [148] sort-list
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
    public ListNode sortList(ListNode head) {
        if (head == null)
            return head;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            ++len;
            p = p.next;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = head.val;
            head = head.next;
        }
        Arrays.sort(arr);
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : arr) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
}
// @lc code=end