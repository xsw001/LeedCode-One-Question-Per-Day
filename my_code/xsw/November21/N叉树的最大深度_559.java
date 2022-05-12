package xsw.November21;
/*

 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N叉树的最大深度_559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    int ans = 0;

    public int maxDepth(Node root) {
        if(root == null)
            return  0;
        if (root.children.size() == 0) {
            return 1;
        }
        for (Node child : root.children) {
            ans = Math.max(ans, maxDepth(child) + 1);
        }
        return ans;
    }

    public int maxDepth1(Node root) {
        int ans = 0;
        if(root == null)
            return  0;
        for (Node child : root.children) {
            ans = Math.max(ans, maxDepth1(child));
        }
        return ans + 1;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    public int maxDepth11(Node root) {
        if(root == null)
            return  0;
        LinkedList<Node> list = new LinkedList<>();
        list.addFirst(root);
        int ans = 0;
        while(!list.isEmpty()){
            int n = list.size();
            while(n > 0){
                Node node = list.pollLast();
                for (Node child : node.children) {
                    list.addFirst(child);
                }
                --n;
            }
            ++ans;
        }
        return  ans;
    }
}