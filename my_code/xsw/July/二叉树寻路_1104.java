package xsw.July;
/*
1104. 二叉树寻路
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。



给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。



示例 1：

输入：label = 14
输出：[1,3,4,14]
示例 2：

输入：label = 26
输出：[1,2,6,10,26]


提示：

1 <= label <= 10^6
通过次数10,059提交次数13,704
 */

import java.util.*;

import xsw.Nov_Dec.TreeNode;

public class 二叉树寻路_1104 {

    // 头像了，太笨了
    public static List<Integer> pathInZigZagTree(int label) {
        TreeNode treeNode = intToTreeNode(label);
        String s = levelOrderTraversal(treeNode);
        System.out.println(s);
        String[] nodes = s.split(",");
        int i = 1;
        for (String node : nodes) {
            if (node.equals("" + label))
                break;
            ++i;
        }
        LinkedList<Integer> ans = new LinkedList<>();
        while (i > 0) {
            ans.addFirst(Integer.parseInt(nodes[i - 1]));
            i /= 2;
        }
        return ans;
    }

    public static TreeNode intToTreeNode(int num) {
        int index = 1;
        TreeNode root = new TreeNode(index++);
        if (num == 1) {
            return root;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            if (index > num) {
                break;
            }

            node.left = new TreeNode(index++);
            nodeQueue.add(node.left);
            node.right = new TreeNode(index++);
            nodeQueue.add(node.right);

        }
        return root;
    }

    public static String levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int l = 1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode front = queue.remove();
            ++count;
            if (l % 2 == 0)
                temp.insert(0, front.val + ",");
            else
                temp.append(front.val).append(",");
            if (count >= Math.pow(2, l) - 1) {
                ++l;
                sb.append(temp);
                temp = new StringBuilder();
            }
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
        if (temp.length() > 0)
            sb.append(temp);
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(pathInZigZagTree(14));
        System.out.println(Solution.pathInZigZagTree(14));
    }

/*
相对应的镜像关系：

[8,9,10,11,12,13,14,15]
[15,14,13,12,11,10,9,8]

很显然，以两边的数为一队组合，即可以推断出

[8+15] = [9+14] = [10+13] = [11+12]

这时，我们要是想求10的镜像数，就可以用

8 + 15 - 10 来求

推广到一般情况
    [ 2^(level - 1) , 2^(level - 1) + 1 ... 2^level- 2 , 2^level- 1]

求label的镜像数：
    2^(level - 1) + 2^level- 1 - label
*/
    static class Solution {
        public static List<Integer> pathInZigZagTree(int label) {
            int row = 1, rowStart = 1;
            while (rowStart * 2 <= label) {
                row++;
                rowStart *= 2;
            }

            List<Integer> path = new ArrayList<Integer>();
            path.add(label);
            row--;
            label >>= 1;
            while (row > 0) {
                if (row % 2 == 0) {
                    path.add(getReverse(label, row));
                } else {
                    path.add(label);
                }
                row--;
                label >>= 1;
            }
            Collections.reverse(path);
            return path;
        }

        // 位运算快速求2的幂
        public static int getReverse(int label, int row) {
            return (1 << row - 1) + (1 << row) - 1 - label;
        }
    }

// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/solution/er-cha-shu-xun-lu-by-leetcode-solution-ryx0/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}