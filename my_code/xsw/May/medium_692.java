package xsw.May;
/*
692. 前K个高频单词
给一非空的单词列表，返回前 k 个出现次数最多的单词。

返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

示例 1：

输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
输出: ["i", "love"]
解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。


示例 2：

输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
输出: ["the", "is", "sunny", "day"]
解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。


注意：

假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
输入的单词均由小写字母组成。


扩展练习：

尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 */

import java.util.*;

public class medium_692 {

    public static List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(entrySet);
        list.sort((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue()))
                return o1.getKey().compareTo(o2.getKey());
            return o2.getValue().compareTo(o1.getValue());
        });
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        String[] data = {"the", "day", "is", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(topKFrequent(data, 3));

    }

    // 更简洁
    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        List<String> rec = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            rec.add(entry.getKey());
        }
        rec.sort((word1, word2) ->
                cnt.get(word1).equals(cnt.get(word2)) ?
                        word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1));
        return rec.subList(0, k);
    }

    //优先队列
/*
    创建一个小根优先队列（顾名思义，就是优先队列顶端元素是最小元素的优先队列）
    我们将每一个字符串插入到优先队列中，如果优先队列的大小超过了 k
    那么我们就将优先队列顶端元素弹出。
    这样最终优先队列中剩下的 k 个元素就是前 k 个出现次数最多的单词。
*/
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (entry1, entry2) -> entry1.getValue().equals(entry2.getValue()) ?
                        entry2.getKey().compareTo(entry1.getKey()) : entry1.getValue() - entry2.getValue());
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> ret = new ArrayList<String>();
        while (!pq.isEmpty()) {
            ret.add(pq.poll().getKey());
        }
        Collections.reverse(ret);
        return ret;
    }
}