//
// @lc app=leetcode.cn id=212 lang=java
//
// [212] word-search-ii
//
class Solution {
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        String  word = "";

        public void insert(String[] words){
            for(String word : words){
                TrieNode node = this;
                for(int i=0; i < word.length(); ++i){
                    int index = word.charAt(i) - 'a';
                    if(node.children[index] == null)
                        node.children[index] = new TrieNode();
                    node = node.children[index];
                }
                node.isEnd = true;
                node.word = word;
            }
        }
    }

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> resultSet = new HashSet<>();

        TrieNode root = new TrieNode();
        root.insert(words);

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board,i,j,root,resultSet);
            }
        }
        return new ArrayList<>(resultSet);
    }

    private void dfs(char[][] board, int i, int j, TrieNode node,Set<String> resultSet) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
        char c = board[i][j];
        if(c == '#' || node.children[c-'a'] == null) return;

        if(node.children[c-'a'].isEnd){
            resultSet.add(node.children[c - 'a'].word);
        }
        board[i][j] = '#';
        for(int k = 0; k < 4; ++k){
            dfs(board,i+dirs[k][0],j+dirs[k][1],node.children[c-'a'],resultSet);
        }
        board[i][j] = c;
    }
}
// @lc code=end