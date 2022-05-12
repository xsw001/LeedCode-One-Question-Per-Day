//
// @lc app=leetcode.cn id=100282 lang=java
//
// [100282] cong-wei-dao-tou-da-yin-lian-biao-lcof
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
    public int[] reversePrint(ListNode head) {
        if(head == null)
            return new int[]{};
        if(head.next == null)
            return new int[]{head.val};
        ListNode p = head, q = head.next;
        p.next = null;
        int l = 1;
        while(q != null){
            ListNode t = q.next;
            q.next = p;
            p = q;
            q = t;
            ++l;
        }
        int[] ans = new int[l];
        l = 0;
        while(p != null){
            ans[l++] = p.val;
            p = p.next;
        }
        return ans;
    }
}
// @lc code=end