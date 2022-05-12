package xsw.November21;
/*
677. 键值映射
实现一个 MapSum 类，支持两个方法，insert 和 sum：

MapSum() 初始化 MapSum 对象
void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。


示例：

输入：
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
输出：
[null, null, 3, null, 5]

解释：
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)


提示：

1 <= key.length, prefix.length <= 50
key 和 prefix 仅由小写英文字母组成
1 <= val <= 1000
最多调用 50 次 insert 和 sum
通过次数17,938提交次数28,414
 */

import org.junit.Test;

import java.util.*;

public class 键值映射_677 {

    class MapSum {

        HashMap<String, Integer> map;

        public MapSum() {
            map = new HashMap<String, Integer>();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int num = 0;
            for (String k : map.keySet()) {
                if (k.startsWith(prefix))
                    num += map.get(k);
            }
            return num;
        }
    }


    @Test
    public void test() {
        MapSum obj = new MapSum();
        obj.insert("app", 2);
        System.out.println(obj.sum("ap"));
        obj.insert("iapple", 1);
        obj.insert("bapple", 1);
        System.out.println(obj.sum("ap"));
    }


    class MapSum1 {

        class TrieNode {
            int val = 0;
            TrieNode[] next = new TrieNode[26];
        }

        TrieNode root;
        Map<String, Integer> map;

        public MapSum1() {
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
}