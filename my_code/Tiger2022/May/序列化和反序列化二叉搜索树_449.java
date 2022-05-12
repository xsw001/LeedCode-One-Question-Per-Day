package Tiger2022.May;

import org.junit.Test;
import xsw.Nov_Dec.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

/*
449. 序列化和反序列化二叉搜索树
序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

编码的字符串应尽可能紧凑。



示例 1：

输入：root = [2,1,3]
输出：[2,1,3]
示例 2：

输入：root = []
输出：[]


提示：

树中节点数范围是 [0, 104]
0 <= Node.val <= 104
题目数据 保证 输入的树是一棵二叉搜索树。
通过次数22,308提交次数38,623
请问您在哪类招聘中遇到此题？
 */
public class 序列化和反序列化二叉搜索树_449 {

    @Test
    public void test() {
        TreeNode node = TreeNode.stringToTreeNode("41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23");
        //TreeNode node = TreeNode.stringToTreeNode("1,2,3");
        System.out.println(node);
        Code codec = new Code();
        System.out.println(codec.serialize(node));
        String serialize = codec.serialize(node);
        System.out.println(codec.deserialize(serialize));
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfsBuilder(root, sb);
            return sb.toString().substring(0, sb.length() - 1);
        }

        private void dfsBuilder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
                return;
            }
            sb.append(root.val).append(",");
            dfsBuilder(root.left, sb);
            dfsBuilder(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0 || (data.length() == 1 && data.charAt(0) == '#'))
                return null;
            String[] split = data.split(",");
            List<String> list = new ArrayList<>(Arrays.asList(split));
            return dfs(list);
        }

        private TreeNode dfs(List<String> data) {
            if (data.isEmpty() || data.get(0).equals("#")) {
                data.remove(0);
                return null;
            }
            TreeNode root = new TreeNode();
            root.val = Integer.parseInt(data.get(0));
            data.remove(0);
            root.left = dfs(data);
            root.right = dfs(data);
            return root;
        }
    }

    public class Code {
        public String serialize(TreeNode root) {
            List<Integer> list = new ArrayList<Integer>();
            postOrder(root, list);
            String str = list.toString();
            return str.substring(1, str.length() - 1);
        }

        private void postOrder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            postOrder(root.left, list);
            postOrder(root.right, list);
            list.add(root.val);
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] arr = data.split(", ");
            Deque<Integer> stack = new ArrayDeque<Integer>();
            for (String s : arr) {
                stack.push(Integer.parseInt(s));
            }
            return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
        }

        // 后序遍历得到的数组中，根结点的值位于数组末尾
        // 左子树的节点均小于根节点的值，右子树的节点均大于根节点的值
        // 可以根据这些性质设计递归函数恢复二叉搜索树。
        private TreeNode construct(int lower, int upper, Deque<Integer> stack) {
            if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
                return null;
            }
            int val = stack.pop();
            TreeNode root = new TreeNode(val);
            root.right = construct(val, upper, stack);
            root.left = construct(lower, val, stack);
            return root;
        }
    }

}
