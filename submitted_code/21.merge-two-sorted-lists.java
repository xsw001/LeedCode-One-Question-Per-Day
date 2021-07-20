//
// @lc app=leetcode.cn id=21 lang=java
//
// [21] merge-two-sorted-lists
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy,p1 = l1,p2 = l2;
        while(p1 != null && p2 != null){
            if(p1.val >= p2.val){
                temp.next = p2;
                p2 = p2.next;
            }else{
                temp.next = p1;
                p1 = p1.next;
            }
            temp = temp.next;
        }
        if(p1!=null){
            temp.next = p1;
            p1 = p1.next;
        }
        if(p2!=null){
            temp.next = p2;
            p2 = p2.next;
        }
        return dummy.next;
    }
}
// @lc code=end