//
// @lc app=leetcode.cn id=237 lang=java
//
// [237] delete-node-in-a-linked-list
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
    public void deleteNode(ListNode node) {
        ListNode p = node.next;
        while(p.next != null){
            node.val = p.val;
            node = node.next;
            p = p.next;
        }
        node.val = p.val;
        node.next = null;
    }
}
// @lc code=end