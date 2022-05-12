//
// @lc app=leetcode.cn id=211 lang=java
//
// [211] design-add-and-search-words-data-structure
//
class WordDictionary {

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }

        void insert(String word) {
            TrieNode node = this;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null)
                    node.children[word.charAt(i) - 'a'] = new TrieNode();
                node = node.children[word.charAt(i) - 'a'];
            }
            node.isWord = true;
        }
    }

    TrieNode node;

    public WordDictionary() {
        node = new TrieNode();
    }

    public void addWord(String word) {
        node.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, node);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (index == word.length())
            return node.isWord;

        if (word.charAt(index) == '.') {
            for (int i = 0; i < 26; i++) {
                TrieNode child = node.children[i];
                if (child != null && dfs(word, index + 1, child))
                    return true;
            }
        } else {
            TrieNode child = node.children[word.charAt(index) - 'a'];
            return child != null && dfs(word, index + 1, child);
        }
        return false;
    }
}
// @lc code=end