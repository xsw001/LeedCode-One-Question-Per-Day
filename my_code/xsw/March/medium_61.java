package xsw.March;
/*
61. 旋转链表
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。



示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
示例 2：


输入：head = [0,1,2], k = 4
输出：[2,0,1]


提示：

链表中节点的数目在范围 [0, 500] 内
-100 <= Node.val <= 100
0 <= k <= 2 * 109
通过次数144,476提交次数348,544
 */

import xsw.Nov_Dec.ListNode;

public class medium_61 {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)
            return head;
        ListNode p = head;
        int len = 1;
        while(p.next != null){
            ++len;
            p = p.next;
        }
        k = k % len;
        if(k == 0)
            return head;
        p.next = head;
        p = head;
        int index = 1;
        while(index < len - k){
            ++index;
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
    }

}