//
// @lc app=leetcode.cn id=100286 lang=java
//
// [100286] he-bing-liang-ge-pai-xu-de-lian-biao-lcof
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode root = new ListNode(0);
        ListNode cur = root;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur =  cur.next;
        }
        if(l1 != null)
            cur.next = l1;
        if(l2 != null)
            cur.next = l2;
        return root.next;
    }
}
// @lc code=end