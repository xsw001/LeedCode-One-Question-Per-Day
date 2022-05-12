package xsw.September;
/*

 */

import java.util.*;
import java.util.ArrayList;

public class 扁平化多级双向链表_430 {

    public static Node flatten(Node head) {
        Node dummy = new Node();
        Node temp = dummy;
        Deque<Node> one = new ArrayDeque<>();
        Deque<Node> two = new ArrayDeque<>();

        while (head.next != null || head.child != null || !one.isEmpty()) {
            while (head.next != null) {
                one.addFirst(head);
                head = head.next;
            }
            while (!one.isEmpty() && one.peekFirst().child == null)
                two.addFirst(one.pollFirst());
            if (one.isEmpty())
                break;
            Node node = one.peekFirst();

            head = node.child;
            node.child = null;
        }
        while (!two.isEmpty()) {
            Node node = two.pollFirst();
            node.prev = temp;
            node.child = null;
            node.next = null;
            temp.next = node;
            temp = temp.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Integer[] data = {1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10, null, null, 11, 12};
        ArrayList<String> list = new ArrayList<>();
        //System.out.println(buildNode(data));
    }

    private static Node buildNode(Integer[] data) {
        if (data.length == 0)
            return null;
        Node node = new Node(0);
        Node cur = node;
        boolean next = true;
        for (Integer i : data) {
            if (i == null) {
                cur = cur.prev;
                next = false;
            } else {
                Node last = new Node(i);
                if (next) {
                    last.prev = cur;
                    cur.next = last;
                } else {
                    cur.child = last;
                }
                cur = last;
                next = true;
            }
        }
        return node.next;
    }


    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", prev=" + prev +
                    ", next=" + next +
                    ", child=" + child +
                    '}';
        }
    }

    public Node flatten1(Node head) {
        if (head == null) return null;
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = head;
        while (true) {
            if (cur.child != null) {
                // 将 next 节点入栈
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                // 将子链表扁平化
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
            // 遍历子链表的下一个节点或是从栈中弹出 next 节点
            if (cur.next != null) {
                cur = cur.next;
            } else if (!stack.isEmpty()) {
                Node next = stack.pop();
                cur.next = next;
                next.prev = cur;
                cur = next;
            } else {
                return head;
            }
        }
    }
}