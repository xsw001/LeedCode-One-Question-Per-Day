//
// @lc app=leetcode.cn id=100311 lang=java
//
// [100311] cong-shang-dao-xia-da-yin-er-cha-shu-lcof
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] levelOrder(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        if(root == null) 
            return new int[]{};
        list.addFirst(root);
        ArrayList<Integer> ans =  new ArrayList<>();
        while(!list.isEmpty()){
            TreeNode node = list.pollLast();
            ans.add(node.val);
            if(node.left != null)
                list.addFirst(node.left);
            if(node.right != null)
                list.addFirst(node.right);
        }
        int[] arr = new int[ans.size()];
        for(int i=0;i<ans.size();++i){
            arr[i] = ans.get(i);
        }
        return arr;
    }
}
// @lc code=end