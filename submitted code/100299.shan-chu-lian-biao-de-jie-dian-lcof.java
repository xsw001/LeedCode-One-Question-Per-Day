//
// @lc app=leetcode.cn id=100299 lang=java
//
// [100299] shan-chu-lian-biao-de-jie-dian-lcof
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
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                break;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
// @lc code=end