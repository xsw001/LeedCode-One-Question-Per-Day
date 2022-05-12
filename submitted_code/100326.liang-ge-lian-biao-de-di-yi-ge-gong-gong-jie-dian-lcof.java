//
// @lc app=leetcode.cn id=100326 lang=java
//
// [100326] liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
//
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        ListNode p = headA, q = headB;
        int la = 0, lb = 0;
        while(p != null){
            ++la;
            p = p.next;
        }
        while(q != null){
            ++lb;
            q = q.next;
        }
        p = headA; q = headB;
        if(la > lb){
            p = q;
            q = headA;
        }
        for(int i=Math.abs(la-lb);i>0;--i){
            q = q.next;
        }
        while(q != null){
            if(q == p)
                return q;
            q = q.next;
            p = p.next;
        }
        return null;
    }
}
// @lc code=end