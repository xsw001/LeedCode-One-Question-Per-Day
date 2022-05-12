//
// @lc app=leetcode.cn id=100314 lang=java
//
// [100314] cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
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
        int num = 0;
        while(!list.isEmpty()){
            LinkedList<Integer> temp = new LinkedList<>();
            for(int i=list.size();i>0;--i){
                TreeNode node = list.pollLast();
                if(num % 2 == 0)
                    temp.addLast(node.val);
                else
                    temp.addFirst(node.val);
                if(node.left != null){
                    list.addFirst(node.left);
                }
                if(node.right != null){
                    list.addFirst(node.right);
                }  
            }
            ans.add(temp);
            ++num;
        }
        return ans;
    }
}
// @lc code=end