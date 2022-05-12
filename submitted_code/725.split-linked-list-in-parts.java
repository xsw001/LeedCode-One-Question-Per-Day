//
// @lc app=leetcode.cn id=725 lang=java
//
// [725] split-linked-list-in-parts
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
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            ++len;
            temp = temp.next;
        }
        int a = len > k ? len / k : 1;
        int b = len > k ? len % k : 0;
        temp = head;
        int i = 0;
        while (temp != null) {
            res[i] = head;
            for (int j = 0; j < a-1; j++) {
                temp = temp.next;
                if (temp == null)
                    return res;
            }
            if (b > 0) {
                temp = temp.next;
                --b;
            }
            if (temp == null)
                return res;
            ListNode next = temp.next;
            temp.next = null;
            head = temp = next;
            ++i;
        }
        return res;
    }
}
// @lc code=end