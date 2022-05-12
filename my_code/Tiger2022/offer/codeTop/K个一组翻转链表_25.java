package Tiger2022.offer.codeTop;
/*
25. K 个一组翻转链表
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。


示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
示例 2：


输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
示例 3：

输入：head = [1,2,3,4,5], k = 1
输出：[1,2,3,4,5]
示例 4：

输入：head = [1], k = 1
输出：[1]
提示：

列表中节点的数量在范围 sz 内
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz
通过次数302,232提交次数454,767
 */

import org.junit.Test;
import xsw.Nov_Dec.ListNode;

import java.util.HashMap;

public class K个一组翻转链表_25 {

    @Test
    public void test() throws Exception {
        HashMap<Object, Object> map = new HashMap<>();

        int[] data = {1, 2, 3, 4, 5};
        ListNode node = ListNode.initListNode(data);
        ListNode.showListNode(reverseKGroup(node, 2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = head, q = head, pre = dummy;
        ListNode next, temp;
        while (q != null) {
            int ii = k;
            while (ii > 1 && q != null) {
                q = q.next;
                --ii;
            }
            if (q != null) {
                temp = p;
                next = q.next;
                ListNode preNode = null;
                for (int i = 0; i < k; i++) {
                    ListNode nx = p.next;
                    p.next = preNode;
                    preNode = p;
                    p = nx;
                }
                pre.next = q;
                temp.next = next;

                pre = temp;

                p = next;
                q = next;
                /*
                ListNode pt = pre.next;
                next = q.next;
                ListNode nt = next;
                for (int j = 0; j < k - 1; ++j) {
                    temp = p.next;
                    q.next = p;
                    p.next = nt;
                    p = temp;
                    nt = q.next;
                    pre.next = temp;
                }
                pre = pt;
                p = next;
                q = next;
                 */
            }
        }
        return dummy.next;
    }

}
