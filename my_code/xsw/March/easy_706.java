package xsw.March;
/*
706. 设计哈希映射
不使用任何内建的哈希表库设计一个哈希映射（HashMap）。

实现 MyHashMap 类：

MyHashMap() 用空映射初始化对象
void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。


示例：

输入：
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
输出：
[null, null, null, 1, -1, null, 1, null, -1]

解释：
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]


提示：

0 <= key, value <= 106
最多调用 104 次 put、get 和 remove 方法


进阶：你能否不使用内置的 HashMap 库解决此问题？
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class easy_706 {

    static class MyHashMap {
        int[] map;
        boolean[] flag;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            map = new int[1000001];
            flag = new boolean[1000001];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            map[key] = value;
            flag[key] = true;
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            if (flag[key])
                return map[key];
            return map[key] == 0 ? -1 : map[key];
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            map[key] = -1;
            flag[key] = false;
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
    }


}

class Pair {
    private int key;
    private int value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class MyMap {
    private static final int BASE = 769;
    private LinkedList[] data;

    public MyMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Pair>();
        }
    }

    public void put(int key, int value) {
        int hash = hash(key);
        LinkedList<Pair> list = data[hash];
        for (Pair pair : list) {
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        list.add(new Pair(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = hash(key);
        LinkedList<Pair> list = data[hash];
        for (Pair pair : list) {
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return -1;
    }


    public void remove(int key) {
        int hash = hash(key);
        LinkedList<Pair> list = data[hash];
        for (Pair pair : list) {
            if (pair.getKey() == key) {
                list.remove(pair);
                return;
            }
        }
    }

    public int hash(int val) {
        return val % BASE;
    }
}