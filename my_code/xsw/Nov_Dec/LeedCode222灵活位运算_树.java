package xsw.Nov_Dec;/*

222. 完全二叉树的节点个数
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值
并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6
*/

public class LeedCode222灵活位运算_树 {
    public static int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    public static void main(String[] args) {
        String input = "1,2,3,4,5,8,7,9,6";
        TreeNode root = TreeNode.stringToTreeNode(input);
        int nodes = countNodes2(root);
        System.out.println(nodes);
    }

    static int count = 0;

    public static int countNodes1(TreeNode root) {
        if (root == null)
            return 0;
        ++count;
        countNodes1(root.left);
        countNodes1(root.right);
        return count;
    }

    /*
    根据节点个数范围的上下界得到当前需要判断的节点个数 kk，如果第 kk 个节点存在
    则节点个数一定大于或等于 kk，如果第 kk 个节点不存在，则节点个数一定小于 kk
    由此可以将查找的范围缩小一半，直到得到节点个数。
    */
    public static int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level;// 2 的 level 次方
        int high = (1 << (level + 1)) - 1;// 2 的 level+1 次方减 1
        while (low < high) {
            System.out.println("low:" + low +" "+"high:" + high);
            int mid = (high + low + 1) / 2;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
            System.out.println("low:" + low +" "+"high:" + high);
        }
        return low;
    }

    /*
     如何判断第 k 个节点是否存在呢？
     如果第 k 个节点位于第 h 层，则 k 的二进制表示包含 h+1 位
     其中最高位是 1，其余各位从高到低表示从根节点到第 k 个节点的路径，0 表示移动到左子节点, 1 表示移动到右子节点
     通过位运算得到第 k 个节点对应的路径,判断该路径对应的节点是否存在，即可判断第 k 个节点是否存在。
     */
    public static boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}
