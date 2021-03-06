package xsw.March;
/*
705. 设计哈希集合
不使用任何内建的哈希表库设计一个哈希集合（HashSet）。

实现 MyHashSet 类：

void add(key) 向哈希集合中插入值 key 。
bool contains(key) 返回哈希集合中是否存在这个值 key 。
void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

示例：

输入：
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
输出：
[null, null, null, true, false, null, true, null, false]

解释：
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // 返回 True
myHashSet.contains(3); // 返回 False ，（未找到）
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // 返回 True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // 返回 False ，（已移除）


提示：

0 <= key <= 106
最多调用 104 次 add、remove 和 contains 。


进阶：你可以不使用内建的哈希集合库解决此问题吗？
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class easy_705 {

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(1);
        set.add(2);
        set.contains(1);
        set.contains(3);
        set.add(2);
        set.contains(2);
        set.remove(2);
        set.contains(2);
        HashSet<Integer> s = new HashSet<>();
        s.add(2);
    }
    static class MyHashSet {
        private static final int BASE = 769;
        private LinkedList[] data;

        /** Initialize your data structure here. */
        public MyHashSet() {
            data = new LinkedList[BASE];
            for (int i = 0; i < BASE; ++i) {
                data[i] = new LinkedList<Integer>();
            }
        }

        public void add(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element == key) {
                    return;
                }
            }
            data[h].offerLast(key);
        }

        public void remove(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element == key) {
                    data[h].remove(element);
                    return;
                }
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element == key) {
                    return true;
                }
            }
            return false;
        }

        private static int hash(int key) {
            return key % BASE;
        }
    }
}

class MyHashSet {

    ArrayList<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        list = new ArrayList<>();
    }

    public void add(int key) {
        if (!list.contains(key))
            list.add(key);
    }

    public void remove(int key) {
        if (!list.contains(key))
            return;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == key) {
                list.remove(i);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return list.contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
