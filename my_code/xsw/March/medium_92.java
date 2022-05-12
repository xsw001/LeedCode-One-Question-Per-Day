package xsw.March;
/*
92. 反转链表 II
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
 */

import xsw.Nov_Dec.ListNode;

import java.util.Stack;

public class medium_92 {

    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        Stack<Integer> stack = new Stack<>();
        ListNode dummy = new ListNode();
        dummy.next = head;
        int i = 0;
        ListNode p = dummy;
        ListNode pre = dummy;
        ListNode q = new ListNode();
        while (i <= right) {
            if (i >= left)
                stack.push(p.val);
            pre = p;
            if(i == left-1)
                q = pre;
            p = p.next;
            ++i;
        }
        pre.next = null;
        pre = q;
        while (!stack.isEmpty()) {
            pre.next = new ListNode(stack.pop());
            pre = pre.next;
        }
        pre.next = p;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6,7};
        ListNode node = ListNode.initListNode(data);
        ListNode.showListNode(node);
        ListNode listNode = reverseBetween(node, 1, 7);
        ListNode.showListNode(listNode);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode behind = cur.next;
        for (int i = left; i < right; i++) {
            cur.next = behind.next;
            behind.next = pre.next;
            pre.next = behind;
            behind = cur.next;
        }
        return dummy.next;
    }
}