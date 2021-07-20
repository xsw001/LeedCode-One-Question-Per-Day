//
// @lc app=leetcode.cn id=143 lang=java
//
// [143] reorder-list
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
    public void reorderList(ListNode head) {
        if(head==null)
            return;
        ListNode last = new ListNode();
        last = head;
        int len = 0;
        while(last.next!=null){
            last = last.next;
            ++len;
        }
        //System.out.print(last.val);

        ListNode tmep = new ListNode();
        tmep = head;
        int count = 1;
        while(count < (len/2+1)){
            tmep = tmep.next;
            count++;
        }
        //System.out.print(tmep.val);
        last = tmep.next;
        tmep.next = null;

        ListNode p = new ListNode();
        tmep=null;
        while(last!=null){
            p = last.next;
            last.next = tmep;
            tmep = last;
            last=p;
        }
        p = head;
        while(tmep!=null){
            last = tmep.next;
            tmep.next = p.next;
            p.next = tmep;
            p = tmep.next;
            tmep = last;
        }

    }
}
// @lc code=end