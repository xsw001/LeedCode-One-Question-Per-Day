//
// @lc app=leetcode.cn id=100294 lang=java
//
// [100294] lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        while(k>0){
            p = p.next;
            --k;
        }
        ListNode ans = head;
        while(p != null){
            ans = ans.next;
            p = p.next;
        }
        return ans;
    }
}
// @lc code=end