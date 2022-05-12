package xsw.October;
/*
211. 添加与搜索单词 - 数据结构设计
请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

实现词典类 WordDictionary ：

WordDictionary() 初始化词典对象
void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。


示例：

输入：
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
输出：
[null,null,null,null,false,true,true,true]

解释：
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


提示：

1 <= word.length <= 500
addWord 中的 word 由小写英文字母组成
search 中的 word 由 '.' 或小写英文字母组成
最多调用 50000 次 addWord 和 search
通过次数31,294提交次数64,006
 */

import java.util.ArrayList;
import java.util.*;

public class 添加与搜索单词_数据结构设计_211 {

    static class WordDictionary {

        HashMap<String, HashSet<String>> map;

        public WordDictionary() {
            map = new HashMap<>();
        }

        public void addWord(String word) {
            String k = word.charAt(0) + "" + word.length();
            map.computeIfAbsent(k, v -> new HashSet<>()).add(word);
        }

        public boolean search(String word) {
            char c = word.charAt(0);
            if (c != '.') {
                String k = word.charAt(0) + "" + word.length();
                HashSet<String> set = map.get(k);
                if (set == null)
                    return false;
                return searchInList(new ArrayList<>(set), word);
            } else {
                for (int i = 0; i < 26; i++) {
                    String k = (char) ('a' + i) + "" + word.length();
                    HashSet<String> set = map.get(k);
                    if (set == null)
                        continue;
                    if (searchInList(new ArrayList<>(set), word))
                        return true;
                }
                return false;
            }
        }

        private boolean searchInList(ArrayList<String> list, String str) {
            for (String s : list) {
                int i = 0;
                for (; i < s.length(); i++) {
                    if (s.charAt(i) == str.charAt(i) || str.charAt(i) == '.')
                        continue;
                    break;
                }
                if (i == str.length())
                    return true;
            }
            return false;
        }
    }


    public static void main(String[] args) {
        WordDictionary w = new WordDictionary();
        w.addWord("at");
        w.addWord("and");
        w.addWord("an");
        w.addWord("add");
        w.search("a");
        w.search(".at");
        w.addWord("bat");
        w.search(".at");
        w.search("an.");
        w.search("a.d.");
        w.search("b.");
        w.search("a.d");
        w.search(".");
    }

    static class WordDictionary1 {// 字典樹

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

        public WordDictionary1() {
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
}