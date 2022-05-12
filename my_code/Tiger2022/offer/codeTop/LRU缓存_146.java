package Tiger2022.offer.codeTop;
/*
146. LRU 缓存
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。



示例：

输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4


提示：

1 <= capacity <= 3000
0 <= key <= 10000
0 <= value <= 105
最多调用 2 * 105 次 get 和 put
通过次数314,632提交次数598,082
 */

import org.junit.Test;

import java.util.*;

public class LRU缓存_146 {

    @Test
    public void test() throws Exception {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
    }

    class LRUCache {
        class DListNode {
            int key;
            int val;
            DListNode next;
            DListNode pre;

            public DListNode() {
            }

            public DListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        DListNode head;
        DListNode tail;
        Map<Integer, DListNode> map;
        int capacity;

        public LRUCache(int capacity) {
            head = new DListNode();
            tail = new DListNode();
            head.next = tail;
            tail.pre = head;
            this.capacity = capacity;
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;
            DListNode node = map.get(key);
            moveNode(node);
            return node.val;
        }


        public void put(int key, int value) {
            if (map.containsKey(key)) {
                DListNode node = map.get(key);
                node.val = value;
                moveNode(node);
                return;
            }
            if (map.size() == capacity) {
                DListNode node = tail.pre;

                tail.pre = node.pre;
                node.pre.next = tail;

                map.remove(node.key);
            }
            DListNode node = new DListNode(key, value);
            insertHead(node);

            map.put(key, node);
        }

        private void insertHead(DListNode node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        private void moveNode(DListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            insertHead(node);
        }
    }


}
