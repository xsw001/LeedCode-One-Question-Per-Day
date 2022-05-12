//
// @lc app=leetcode.cn id=382 lang=java
//
// [382] linked-list-random-node
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

        int len;
        Random r;
        ListNode node;

        public Solution(ListNode head) {
            len = 0;
            node = head;
            r = new Random();
            while (head != null) {
                ++len;
                head = head.next;
            }
            //System.out.println(len);
        }

        public int getRandom() {
            int index = r.nextInt(len);
            ListNode h = node;
            while (index != 0) {
                --index;
                h = h.next;
            }
            return h.val;
        }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
// @lc code=end