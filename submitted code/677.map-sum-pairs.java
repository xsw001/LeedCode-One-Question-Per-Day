//
// @lc app=leetcode.cn id=677 lang=java
//
// [677] map-sum-pairs
//
    class MapSum {

        class TrieNode {
            int val = 0;
            TrieNode[] next = new TrieNode[26];
        }

        TrieNode root;
        Map<String, Integer> map;

        public MapSum() {
            root = new TrieNode();
            map = new HashMap<String, Integer>();
        }

        public void insert(String key, int val) {
            int num = val - map.getOrDefault(key, 0);
            map.put(key, val);
            TrieNode node = root;
            for (char c : key.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
                node.val += num;
            }
        }

        public int sum(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    return 0;
                }
                node = node.next[c - 'a'];
            }
            return node.val;
        }
    }
// @lc code=end