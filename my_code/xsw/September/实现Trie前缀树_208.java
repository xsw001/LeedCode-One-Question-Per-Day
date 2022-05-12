package xsw.September;
/*
208. 实现 Trie (前缀树)
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。


示例：

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True


提示：

1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成
insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
通过次数140,612提交次数196,287
 */

import java.util.ArrayList;
import java.util.HashSet;

public class 实现Trie前缀树_208 {

    /*
    	172 ms	48.5 MB
     */
    static class Trie1 {

        HashSet<String> words;
        public HashSet<String> per;

        /**
         * Initialize your data structure here.
         */
        public Trie1() {
            words = new HashSet<>();
            per = new HashSet<>();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            words.add(word);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
                per.add(sb.toString());
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return words.contains(word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return per.contains(prefix);
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    /*
        执行用时：33 ms, 在所有 Java 提交中击败了72.90%的用户
        内存消耗：47.5 MB, 在所有 Java 提交中击败了59.26%的用户
     */
    static class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null)
                    node.children[index] = new Trie();
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie trie = help(word);
            return trie != null && trie.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie trie = help(prefix);
            return trie != null;
        }

        private Trie help(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (node.children[index] == null)
                    return null;
                node = node.children[index];
            }
            return node;
        }
    }

}