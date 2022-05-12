package xsw.Nov_Dec;/*
381. O(1) 时间插入、删除和获取随机元素 - 允许重复
设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

注意: 允许出现重复元素。

insert(val)：向集合中插入元素 val。
remove(val)：当 val 存在时，从集合中移除一个 val。
getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
示例:

// 初始化一个空的集合。
RandomizedCollection collection = new RandomizedCollection();

// 向集合中插入 1 。返回 true 表示集合不包含 1 。
collection.insert(1);

// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
collection.insert(1);

// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
collection.insert(2);

// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
collection.getRandom();

// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
collection.remove(1);

// getRandom 应有相同概率返回 1 和 2 。
collection.getRandom();
*/

import java.util.*;

public class RandomizedCollection {

    Map<Integer, Set<Integer>> loc = null;
    ArrayList<Integer> nums = null;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        loc = new HashMap<>();
        nums = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        Set<Integer> set = loc.getOrDefault(val, new HashSet<>());
        nums.add(val);
        set.add(nums.size() - 1);
        loc.put(val, set);
        return set.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (!loc.containsKey(val)) {
            return false;   // 没有val  返回false
        }
        Iterator<Integer> it = loc.get(val).iterator();//  找到 值为 val的下标集合，生成 迭代器，方便遍历
        int i = it.next();// 值为val的所有下标中的其中一个
        int lastNum = nums.get(nums.size() - 1);// 数组最后的元素
        nums.set(i, lastNum);// 用指定的元素(lastNum)替代此列表中指定位置上(i)的元素
        loc.get(val).remove(i);// 移除set集合中的  被删除的 值为val的 下标
        loc.get(lastNum).remove((nums.size() - 1));// 移除set集合中的  被替换的（原先在列表尾部的元素的）下标
        if (i < nums.size() - 1) {// 如果 i == nums.size() - 1，说明val就是最后一个元素，不可以再赋予新下标，不然相当于没删掉

            loc.get(lastNum).add(i);// 给原先在列表尾部的元素的重新 赋于新的下标值，就是被删除的val原先的位置 i
        }
        if (loc.get(val).size() == 0) {
            loc.remove(val);// 如果啊，删完之后，val的下标集合空了，那就把这个集合也抹去
        }
        nums.remove((nums.size() - 1));// 删除val
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return nums.get((int) (Math.random() * (nums.size() - 1)));

    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
