package Tiger2022.January;
/*
382. 链表随机节点
给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。

实现 Solution 类：

Solution(ListNode head) 使用整数数组初始化对象。
int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。


示例：


输入
["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
[[[1, 2, 3]], [], [], [], [], []]
输出
[null, 1, 3, 2, 2, 3]

解释
Solution solution = new Solution([1, 2, 3]);
solution.getRandom(); // 返回 1
solution.getRandom(); // 返回 3
solution.getRandom(); // 返回 2
solution.getRandom(); // 返回 2
solution.getRandom(); // 返回 3
// getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。


提示：

链表中的节点数在范围 [1, 104] 内
-104 <= Node.val <= 104
至多调用 getRandom 方法 104 次


进阶：

如果链表非常大且长度未知，该怎么处理？
你能否在不使用额外空间的情况下解决此问题？
通过次数25,735提交次数37,841
 */

import org.junit.Test;
import xsw.Nov_Dec.ListNode;

import java.util.ArrayList;
import java.util.Random;

public class 链表随机节点_382 {

    class Solution {

        int len;
        Random r;
        ListNode node;

        public Solution(ListNode head) {
            len = 0;
            node = head;
            r = new Random();
            while (head != null) {
                ++len;
                head = head.next;
            }
        }

        public int getRandom() {
            int index = r.nextInt(len + 1);
            ListNode h = node;
            int ans = h.val;
            while (index != 0) {
                --index;
                ans = h.val;
                h = h.next;
            }
            return ans;
        }
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(3));
        }

    }

}