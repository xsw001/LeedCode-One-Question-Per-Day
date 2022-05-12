package Tiger2022.January;
/*
23. 合并K个升序链表
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。



示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：

输入：lists = []
输出：[]
示例 3：

输入：lists = [[]]
输出：[]


提示：

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4
通过次数372,971提交次数662,750
 */

import org.junit.Test;
import xsw.Nov_Dec.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 合并K个升序链表_23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || (lists.length == 1 && lists[0] == null))
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if(list != null)
                pq.add(list);
        }
        ListNode node = new ListNode();
        ListNode end = node;
        while ((!pq.isEmpty())) {
            ListNode poll = pq.poll();
            end.next = new ListNode(poll.val);
            end = end.next;
            if (poll.next != null) {
                poll = poll.next;
                pq.add(poll);
            }
        }
        return node.next;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
    }

}