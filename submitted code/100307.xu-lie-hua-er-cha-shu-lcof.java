//
// @lc app=leetcode.cn id=100307 lang=java
//
// [100307] xu-lie-hua-er-cha-shu-lcof
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
public class Codec {

    // Encodes a tree to a single string.
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
    public TreeNode deserialize(String input) {
        if (input.length() == 0 || input.startsWith("null")) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
// @lc code=end