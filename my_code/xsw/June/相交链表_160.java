package xsw.June;
/*
160. 相交链表
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。

图示两个链表在节点 c1 开始相交：



题目数据 保证 整个链式结构中不存在环。

注意，函数返回结果后，链表必须 保持其原始结构 。



示例 1：



输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Intersected at '8'
解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
示例 2：



输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Intersected at '2'
解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
示例 3：



输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
这两个链表不相交，因此返回 null 。


提示：

listA 中节点数目为 m
listB 中节点数目为 n
0 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
如果 listA 和 listB 没有交点，intersectVal 为 0
如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]


进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？

通过次数247,719提交次数419,280
 */

import xsw.Nov_Dec.ListNode;

import java.util.ArrayList;

public class 相交链表_160 {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int x = 0, y = 0;
        ListNode p = headA, q = headB;
        while (p != null) {
            ++x;
            p = p.next;
        }
        while (q != null) {
            ++y;
            q = q.next;
        }
        int index = Math.abs(x - y);
        if (x > y)
            return help(headA, headB, index);
        else
            return help(headB, headA, index);
    }

    public static ListNode help(ListNode headA, ListNode headB, int index) {
        for (int i = 0; i < index; ++i) {
            headA = headA.next;
        }
        while (headB != null && headA != null && headB.val != headA.val) {//租不到题目的要求
            headB = headB.next;
            headA = headA.next;
        }
        return headA;
    }

    public static void main(String[] args) {
        int[] data1 = {2, 5, 8, 13, 15, 20};
        int[] data2 = {24, 1, 54};
        ListNode headA = ListNode.initListNode(data1);
        ListNode headB = ListNode.initListNode(data2);

        ListNode.showListNode(getIntersectionNode(headA, headB));

    }

}