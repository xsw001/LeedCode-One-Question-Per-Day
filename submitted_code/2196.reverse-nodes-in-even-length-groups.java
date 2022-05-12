//
// @lc app=leetcode.cn id=2196 lang=java
//
// [2196] reverse-nodes-in-even-length-groups
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
        public ListNode reverseEvenLengthGroups(ListNode head) {
            if (head.next == null || head.next.next == null)
                return head;
            int group = 1;
            int index = 1;
            ListNode cur = head, next = head.next;
            ArrayList<ListNode> list = new ArrayList<>();
            while (cur.next != null) {
                if (index == (group + 1) * group / 2) {
                    ++group;
                    cur.next = null;
                    list.add(head);
                    head = next;
                }
                ++index;
                cur = next;
                next = next.next;
            }
            list.add(head);
            ListNode ans = new ListNode();
            cur = ans;
            for (int i = 0; i < list.size() - 1; i++) {
                if ((i + 1) % 2 == 0) {
                    cur.next = reverse(list.get(i));
                } else {
                    cur.next = list.get(i);
                }
                while (cur.next != null)
                    cur = cur.next;
            }
            ListNode last = list.get(list.size() - 1);
            next = last;
            int l = 0;
            while (next != null) {
                ++l;
                next = next.next;
            }
            cur.next = l % 2 == 0 ? reverse(last) : last;
            return ans.next;
        }

        private ListNode reverse(ListNode root) {
            ListNode ans = new ListNode();
            ListNode cur = root;
            ListNode next;
            while(cur != null){
                next = cur.next;
                cur.next = ans.next;
                ans.next = cur;
                cur = next;
            }
            return ans.next;
        }
}
// @lc code=end