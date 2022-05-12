package xsw.March;
/*
331. 验证二叉树的前序序列化
序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。

给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。

每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。

你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。

示例 1:

输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
输出: true
示例 2:

输入: "1,#"
输出: false
示例 3:

输入: "9,#,#,1"
输出: false
通过次数13,670提交次数29,045
 */

public class medium_331 {

    public static boolean isValidSerialization1(String preorder) {
        String[] array = preorder.split(",");
        BinaryTree tree = new BinaryTree(array);
        tree.preOrderRecur();
        String s = preorder.replaceAll(",", "");
        return tree.isEqu(s);
    }

    public static void main(String[] args) {
        String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(isValidSerialization(s));
    }

    //是真的巧，真的喵
    private static boolean isValidSerialization(String s) {
        int l = s.length(),count = 0;
        for (int i = l - 1; i >= 0; i--) {
            if (s.charAt(i) == ',')
                continue;
            if (s.charAt(i) == '#')
                ++count;
            else {
                while (i>=0 && s.charAt(i) != ',')
                    --i;
                if(count < 2)
                    return false;
                --count;
            }
        }
        return count == 1;
    }

}

class BinaryTree {
    //节点个数
    private int size;
    //根节点
    private Node root;
    StringBuilder res = new StringBuilder();
    boolean flag = true;

    public BinaryTree() {

    }

    //有参构造方法
    public BinaryTree(String[] c) {
        this();
        root = addAllRecur(c);
    }

    //前序遍历数据创建二叉树（递归）
    private Node addAllRecur(String[] c) {
        if (size >= c.length) {
            flag = false;
            return null;
        }
        String var = c[size++];
        if (var.equals("#")) {
            return null;
        }
        Node node = new Node(Integer.parseInt(var + ""));
        node.left = addAllRecur(c);
        node.right = addAllRecur(c);
        return node;
    }

    //前序遍历，递归
    public void preOrderRecur() {
        preOrderRecur(root);
    }

    public void preOrderRecur(Node root) {
        if (root == null) {
            res.append("#");
            return;
        }
        res.append(root.data);
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    public boolean isEqu(String s) {
        return res.toString().equals(s) && flag;
    }

    private static class Node {

        Integer data;
        Node right;
        Node left;

        Node(Integer data) {
            this.data = data;
        }
    }
}