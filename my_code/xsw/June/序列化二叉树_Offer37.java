package xsw.June;
/*

 */

import xsw.Nov_Dec.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class 序列化二叉树_Offer37 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public static class Codec {

        // Encodes a tree to a Single string.
        public String serialize(TreeNode root) {
            LinkedList<TreeNode> list = new LinkedList<>();
            list.add(root);
            StringBuilder ans = new StringBuilder();
            while(!list.isEmpty()){
                TreeNode temp = list.poll();
                if(temp == null){
                    ans.append("null");
                    ans.append(",");
                    continue;
                }
                ans.append(temp.val);
                ans.append(",");
                list.add(temp.left);
                list.add(temp.right);
            }
            return ans.substring(0,ans.length());
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            TreeNode treeNode = TreeNode.stringToTreeNode(data);
            System.out.println(treeNode);

            return treeNode;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

    public static void main(String[] args) {
        String data = "";
        Codec codec = new Codec();
        TreeNode treeNode = codec.deserialize(data);
        String serialize = codec.serialize(treeNode);
        System.out.println(serialize);

    }

}