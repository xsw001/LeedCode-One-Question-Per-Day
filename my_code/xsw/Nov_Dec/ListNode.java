package xsw.Nov_Dec;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode head = this;
        StringBuilder builder = new StringBuilder();
        while (head != null) {
            builder.append(head.val).append(',');
            head = head.next;
        }
        return "[" + builder.toString().substring(0,builder.length()-1) + "]";
    }


    public static ListNode initListNode(int[] nodeValues) {
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void showListNode(ListNode node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }

        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(Integer.toString(node.val)).append("->");
            node = node.next;
        }
        System.out.println(result.substring(0, result.length() - 2));
    }
}
