package xsw.April.团队赛;
/*2. 二叉树染色
通过的用户数11
尝试过的用户数17
用户总通过次数11
用户总提交次数18
题目难度Medium
小扣有一个根结点为 root 的二叉树模型，初始所有结点均为白色，可以用蓝色染料给模型结点染色，模型的每个结点有一个 val 价值。小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 k 个，求所有染成蓝色的结点价值总和最大是多少？

示例 1：

输入：root = [5,2,3,4], k = 2

输出：12

解释：结点 5、3、4 染成蓝色，获得最大的价值 5+3+4=12
image.png

示例 2：

输入：root = [4,1,3,9,null,null,2], k = 2

输出：16

解释：结点 4、3、9 染成蓝色，获得最大的价值 4+3+9=16
image.png

提示：

1 <= k <= 10
1 <= val <= 10000
1 <= 结点数量 <= 10000
 */

import xsw.Nov_Dec.TreeNode;

public class medium_2 {
    int k;
    public int maxValue(TreeNode root, int k) {
        this.k = k;
        int ans = 0;
        int[] res = cal(root);
        for(int i : res) ans = Math.max(ans, i);
        return ans;
    }
    int[] cal(TreeNode node) {
        int[] res = new int[k + 1];
        if(node == null) return res;
        int[] left = cal(node.left), right = cal(node.right);
        res[0] = left[k] + right[k];
        for(int i = 1; i <= k; i++) {
            for(int j = 0; j < i; j++)
                res[i] = Math.max(res[i], left[j] + right[i - 1 - j]);
            res[i] += node.val;
            res[i] = Math.max(res[i], res[i - 1]);
        }
        //System.out.println(node.val + " " + Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};

    }

}