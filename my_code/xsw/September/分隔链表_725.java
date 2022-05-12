package xsw.September;
/*

 */

import xsw.Nov_Dec.ListNode;

import java.util.ArrayList;
import java.util.Arrays;

public class 分隔链表_725 {

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            ++len;
            temp = temp.next;
        }
        int a = len > k ? len / k : 1;
        int b = len > k ? len % k : 0;
        temp = head;
        int i = 0;
        while (temp != null) {
            res[i] = temp;
            for (int j = 0; j < a-1; j++) {
                temp = temp.next;
                if (temp == null)
                    return res;
            }
            if (b > 0) {
                temp = temp.next;
                --b;
            }
            if (temp == null)
                return res;
            ListNode next = temp.next;
            temp.next = null;
            temp = next;
            ++i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        ListNode listNode = ListNode.initListNode(data);
        System.out.println(Arrays.toString(splitListToParts(listNode, 3)));
        ArrayList<String> list = new ArrayList<>();

    }

}