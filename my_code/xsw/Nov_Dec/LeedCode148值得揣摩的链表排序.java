package xsw.Nov_Dec;/*
148. 排序链表
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
进阶：
你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
示例 1：

输入：head = [4,2,1,3]
输出：[1,2,3,4]
示例 2：

输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
示例 3：

输入：head = []
输出：[]
*/

import java.util.Arrays;

public class LeedCode148值得揣摩的链表排序 {


    public ListNode sortList1(ListNode head) {
        if (head == null)
            return head;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            ++len;
            p = p.next;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = head.val;
            head = head.next;
        }
        Arrays.sort(arr);
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : arr) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void main(String[] args) {
        int[] head = {3, 4, 5, 1, 2};
        ListNode listNode = ListNode.initListNode(head);
        ListNode sortList1 = sortList(listNode);
        ListNode.showListNode(sortList1);
    }

    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    //逐渐往小的细分，逐渐往大的合并，最终成为两个有序的子链表合并
    private static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null)
            return head;
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode fast = head, slow = head;
        while (fast != tail) {
            fast = fast.next;
            slow = slow.next;
            if (fast != tail)
                fast = fast.next;
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode.showListNode(list1);
        ListNode.showListNode(list2);
        System.out.println("-----------");
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    private static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, p1 = head1, p2 = head2;
        while (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                temp.next = p2;
                p2 = p2.next;
            } else {
                temp.next = p1;
                p1 = p1.next;
            }
            temp = temp.next;
        }
        if (p1 != null) {
            temp.next = p1;
            p1 = p1.next;
        }
        if (p2 != null) {
            temp.next = p2;
            p2 = p2.next;
        }
        return dummy.next;
    }

    //使用自底向上的方法实现归并排序，则可以达到 O(1) 的空间复杂度。
    public static ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        /*
        用 \textit{subLength}subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。

        每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于subLength）
        按照每两个子链表一组进行合并，合并后即可得到若干个长度为 subLength×2 的有序子链表（最后一个子链表的长度可以小于subLength×2）
        合并两个子链表仍然使用「合并两个有序链表」的做法。

        将 subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length，整个链表排序完毕。

        */
        for (int subLength = 1; subLength < length; subLength <<= 1) {//起始为1，每次循环加倍
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {//curr 从头到尾走一遍，每次将链表拆分成若干个长度为 subLength 的子链表
                ListNode head1 = curr;// 第一个sublength长的子链表
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;// 第2个sublength长的子链表
                curr.next = null;//curr断开
                curr = head2;//继续刚才的位置往下走
                for (int i = 1; i < subLength && curr != null; i++) {
                    curr = curr.next;
                }//第二个也寻找完毕，不急着断开，先判断是否到了最后
                ListNode next = null;
                if (curr != null) {//如果还没有结束
                    next = curr.next;//用next记录下一个位置，方便下一趟继续往下走
                    curr.next = null;//此时再断开
                }
                prev.next = merge(head1, head2);//两个两个的来，合并。合并之后有序，下一趟就是 局部有序 和 待排序的合并
                //每次合并的时候，是让两个子链表有序，没有说全部有序
                /*
                初始时 subLength=1，每个长度为 1 的子链表都是有序的。

                如果每个长度为 subLength 的子链表已经有序，合并两个长度为 subLength 的有序子链表
                得到【长度为 subLength×2 的子链表，一定也是有序的】。

                当最后一个子链表的长度小于 subLength 时，该子链表也是有序的，合并两个有序子链表之后得到的子链表一定也是有序的。
                */
                ListNode.showListNode(head1);
                ListNode.showListNode(head2);
                ListNode.showListNode(prev);
                System.out.println("__________");

                while (prev.next != null) {//将prev移到局部有序的末尾
                    prev = prev.next;
                }
                curr = next;//回到断开的下一个位置，如果刚才是最后一个也没事，因为 next 的初始值就是 null
            }
            ListNode.showListNode(dummyHead);
        }
        return dummyHead.next;
    }
}
