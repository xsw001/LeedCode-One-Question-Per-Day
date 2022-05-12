package xsw.November21.第267场周赛;
/*
5927. 反转偶数长度组的节点 显示英文描述
通过的用户数0
尝试过的用户数0
用户总通过次数0
用户总提交次数0
题目难度Medium
给你一个链表的头节点 head 。

链表中的节点 按顺序 划分成若干 非空 组，这些非空组的长度构成一个自然数序列（1, 2, 3, 4, ...）。一个组的 长度 就是组中分配到的节点数目。换句话说：

节点 1 分配给第一组
节点 2 和 3 分配给第二组
节点 4、5 和 6 分配给第三组，以此类推
注意，最后一组的长度可能小于或者等于 1 + 倒数第二组的长度 。

反转 每个 偶数 长度组中的节点，并返回修改后链表的头节点 head 。



示例 1：



输入：head = [5,2,6,3,9,1,7,3,8,4]
输出：[5,6,2,3,9,1,4,8,3,7]
解释：
- 第一组长度为 1 ，奇数，没有发生反转。
- 第二组长度为 2 ，偶数，节点反转。
- 第三组长度为 3 ，奇数，没有发生反转。
- 最后一组长度为 4 ，偶数，节点反转。
示例 2：



输入：head = [1,1,0,6]
输出：[1,0,1,6]
解释：
- 第一组长度为 1 ，没有发生反转。
- 第二组长度为 2 ，节点反转。
- 最后一组长度为 1 ，没有发生反转。
示例 3：



输入：head = [2,1]
输出：[2,1]
解释：
- 第一组长度为 1 ，没有发生反转。
- 最后一组长度为 1 ，没有发生反转。
示例 4：

输入：head = [8]
输出：[8]
解释：只有一个长度为 1 的组，没有发生反转。


提示：

链表中节点数目范围是 [1, 105]
0 <= Node.val <= 105
 */

import org.junit.Test;
import xsw.Nov_Dec.ListNode;

import java.util.ArrayList;

public class LeedCode2 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
            while (cur != null) {
                next = cur.next;
                cur.next = ans.next;
                ans.next = cur;
                cur = next;
            }
            return ans.next;
        }
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}