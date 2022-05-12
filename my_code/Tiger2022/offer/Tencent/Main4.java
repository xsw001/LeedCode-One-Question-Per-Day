package Tiger2022.offer.Tencent;

import org.junit.Test;
import xsw.Nov_Dec.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main4 {

    @Test
    public void test() throws Exception {
        int[][] arr = {{3, 7, 4}, {7, 4, 5, 1, 10, 3}};
        ListNode[] a = new ListNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            a[i] = ListNode.initListNode(arr[i]);
        }
        System.out.println(solve(a));
    }

    public ListNode solve(ListNode[] a) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode t = a[0];
        int min = Integer.MAX_VALUE;
        while (t != null) {
            min = Math.min(min, t.val);
            map.put(t.val, t);
            t = t.next;
        }
        for (int i = 1; i < a.length; i++) {
            ListNode cur = a[i];
            min = Math.min(min, cur.val);
            ListNode pre = cur;
            cur = cur.next;
            while (cur != null) {
                min = Math.min(min, cur.val);
                if (!map.containsKey(cur.val)) {
                    ListNode listNode = new ListNode(cur.val);
                    map.get(pre.val).next = listNode;
                    map.put(cur.val, listNode);
                }
                pre = cur;
                cur = cur.next;
            }
        }
        int len = 0;
        int key = -1;
        for (Integer k : map.keySet()) {
            ListNode listNode = map.get(k);
            int l = 0;
            while (listNode != null) {
                ++l;
                listNode = listNode.next;
            }
            if (l > len) {
                len = l;
                key = k;
            }
        }
        ListNode root = map.get(key);
        ListNode node = root;
        while (node.next != null)
            node = node.next;
        node.next = root;

        while (node.next.val != min)
            node = node.next;

        if (node.val > node.next.next.val) {
            ListNode ans = node.next;
            node.next = null;
            return ans;
        } else {
            ListNode dummy = new ListNode(-1);
            ListNode p = node.next.next;
            while(p.val != min){
                ListNode q = new ListNode(p.val);
                q.next = dummy.next;
                dummy.next = q;
                p = p.next;
            }
            ListNode q = new ListNode(p.val);
            q.next = dummy.next;
            dummy.next = q;
            return dummy.next;
        }
    }
}
