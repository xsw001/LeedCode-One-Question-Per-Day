//
// @lc app=leetcode.cn id=100312 lang=java
//
// [100312] cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        List<List<Integer>> ans =  new ArrayList<>();
        if(root == null) 
            return ans;
        list.addFirst(root);
        int num = 1;
        while(!list.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<>();
            int t = 0;
            for(int i=0;i<num;++i){
                TreeNode node = list.pollLast();
                temp.add(node.val);
                if(node.left != null){
                    list.addFirst(node.left);
                    ++t;
                }
                if(node.right != null){
                    list.addFirst(node.right);
                    ++t;
                }  
            }
            num = t;
            ans.add(temp);
        }
        return ans;
    }
}
// @lc code=end