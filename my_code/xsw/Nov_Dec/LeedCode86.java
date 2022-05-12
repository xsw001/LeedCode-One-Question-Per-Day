package xsw.Nov_Dec;

/*
86. 分隔链表
给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。



示例：

输入：head = 1->4->3->2->5->2, x = 3
输出：1->2->2->4->3->5
*/

public class LeedCode86 {

    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode great = new ListNode(0);
        ListNode j = small;
        ListNode k = great;
        ListNode i = head;
        while (i != null) {
            if (i.val < x) {
                j.next = new ListNode(i.val);
                j = j.next;
            } else {
                k.next = new ListNode(i.val);
                k = k.next;
            }
            i = i.next;
        }
        j.next = great.next;
        return small.next;
    }

    public static void main(String[] args) {
        int[] node = {};
        ListNode listNode = ListNode.initListNode(node);
        ListNode.showListNode(listNode);
        ListNode partition = partition1(listNode, 3);
        ListNode.showListNode(partition);
        System.out.println();
    }

    //忽然间发现不需要这样换来换去
    public static ListNode partition1(ListNode head, int x) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode X = head;
        ListNode smaller = dummy;
        while (X!=null && X.val < x) {
            X = X.next;
            smaller = smaller.next;
        }
        ListNode i = X;
        ListNode pre = smaller;
        ListNode temp;
        while (i != null) {
            if (i.val < x) {
                temp = i;
                pre.next = i.next;
                i = pre.next;
                temp.next = X;
                smaller.next = temp;
                smaller = temp;
            } else {
                i = i.next;
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}
