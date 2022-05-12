package xsw.July;
/*
138. 复制带随机指针的链表
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。



示例 1：



输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
示例 2：



输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
示例 3：



输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
示例 4：

输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。


提示：

0 <= n <= 1000
-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。
通过次数83,413提交次数132,642
 */

import xsw.Nov_Dec.ListNode;
import java.util.ArrayList;
import java.util.HashMap;

public class 复制带随机指针的链表_138 {

    // Definition for a Node.
    static class Node extends ListNode {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    ", random=" + random +
                    '}';
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null)
            return head;
        Node dummy = new Node(0);
        Node cur = dummy;
        ArrayList<Node> list = new ArrayList<>();// 多余了，map里边直接放就完事了
        HashMap<Node, Integer> map = new HashMap<>();
        Node p = head;
        int i = 0;
        while (head != null) {
            map.put(head, i++);
            Node temp = new Node(head.val);
            cur.next = temp;
            cur = temp;
            head = head.next;
            list.add(temp);
        }
        Node q = dummy.next;
        while (p != null) {
            if (p.random != null)
                q.random = list.get(map.get(p.random));
            p = p.next;
            q = q.next;
        }
        return dummy.next;
    }


    public static Node copyRandomList1(Node head) {
        if (head == null)
            return head;
        Node dummy = new Node(0);
        Node cur = dummy;
        HashMap<Node, Node> map = new HashMap<>();//使用「哈希表」的目的为了实现原节点和新节点的映射关系，更进一步的是为了快速找到某个节点 random 在新链表的位置。
        Node p = head;
        while (head != null) {
            Node temp = new Node(head.val);
            cur.next = temp;
            cur = temp;
            head = head.next;
            map.put(head, temp);
        }
        Node q = dummy.next;
        while (p != null) {
            if (p.random != null)
                q.random = map.get(p.random);
            p = p.next;
            q = q.next;
        }

        String s = "asda";
        s += ":sda";
        return dummy.next;
    }

    class Solution {
        /*
            我们使用「哈希表」的目的为了实现原节点和新节点的映射关系，更进一步的是为了快速找到某个节点 random 在新链表的位置。
            那么我们可以利用原链表的 next 做一个临时中转，从而实现映射。
            具体的，我们可以按照如下流程进行：
                1.对原链表的每个节点节点进行复制，并追加到原节点的后面；
                2.完成 1 操作之后，链表的奇数位置代表了原链表节点，链表的偶数位置代表了新链表节点
                    且每个原节点的 next 指针执行了对应的新节点。
                    这时候，我们需要构造新链表的 random 指针关系，
                    可以利用 link[i + 1].random = link[i].random.next
                    i 为奇数下标，含义为 新链表节点的 random 指针指向旧链表对应节点的 random 指针的下一个值；
                3.对链表进行拆分操作。
        */
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Node p = head;
            //第一步，在每个原节点后面创建一个新节点
            //1->1'->2->2'->3->3'
            while (p != null) {
                Node newNode = new Node(p.val);
                newNode.next = p.next;
                p.next = newNode;
                p = newNode.next;
            }
            p = head;
            //第二步，设置新节点的随机节点
            while (p != null) {
                if (p.random != null) {
                    p.next.random = p.random.next;
                }
                p = p.next.next;
            }
            Node dummy = new Node(-1);
            p = head;
            Node cur = dummy;
            //第三步，将两个链表分离
            while (p != null) {
                cur.next = p.next;
                cur = cur.next;
                p.next = cur.next;
                p = p.next;
            }
            return dummy.next;
        }
    }
}
