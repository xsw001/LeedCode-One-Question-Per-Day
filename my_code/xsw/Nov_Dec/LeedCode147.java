package xsw.Nov_Dec;/*

147. 对链表进行插入排序
对链表进行插入排序。


插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

插入排序算法：

插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
重复直到所有输入数据插入完为止。


示例 1：

输入: 4->2->1->3
输出: 1->2->3->4
示例 2：

输入: -1->5->3->4->0
输出: -1->0->3->4->5
*/

public class LeedCode147 {
    public static ListNode insertionSortList(ListNode head) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode();
        ListNode ptr = new ListNode();
        dummy.next = head;
        ptr = head;
        ListNode red = new ListNode();//待排序链表的头节点red
        red = head.next;
        ptr.next = null;//把两部分断开了
        while (red != null) {
            if (red.val >= ptr.val) {//第一种情况：待排元素的值大于已排元素的最大值
                ptr.next = red;
                red = red.next;
                ptr = ptr.next;
                ptr.next = null;
            } else if (red.val <= head.val) {//第二种情况：待排元素的值小于已排元素的最小值
                ListNode cur = red;
                red = red.next;
                cur.next = head;
                dummy.next = cur;
                head = cur;
            } else {//第三种情况：待排元素的值位于首尾之间
                ListNode temp = head;
                while (temp.next.val < red.val)//查找应该插入的位置
                    temp = temp.next;
                ListNode cur = red;
                red = red.next;
                cur.next = temp.next;
                temp.next = cur;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nodeValues = {2,-1,5,3,4,0};
        //int[] nodeValues = {4, 2, 1, 3};
        ListNode listNode = ListNode.initListNode(nodeValues);
        ListNode sortList = insertionSortList(listNode);
        ListNode.showListNode(sortList);
    }
}
