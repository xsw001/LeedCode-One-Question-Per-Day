//
// @lc app=leetcode.cn id=381 lang=java
//
// [381] insert-delete-getrandom-o1-duplicates-allowed
//
class RandomizedCollection {
    Map<Integer, Set<Integer>> idx;
    List<Integer> nums;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);//把val放进去
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>()); //额外维护数值在列表中每一次出现的下标集合
        set.add(nums.size() - 1);//记录一下val的所有下标
        idx.put(val, set);//把 val 与 下标 的对映关系放进 map 中
        return set.size() == 1;//如果下标集合的长度为1，说明目前就一个，属于第一次出现，返回true
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idx.containsKey(val)) {// 没有val  返回false
            return false;
        }
        Iterator<Integer> it = idx.get(val).iterator(); //  找到 值为 val的下标集合，生成 迭代器，方便遍历
        int i = it.next();// 值为val的所有下标中的其中一个
        int lastNum = nums.get(nums.size() - 1);// 数组最后的元素
        nums.set(i, lastNum);// 用指定的元素(lastNum)替代此列表中指定位置上(i)的元素
        idx.get(val).remove(i);// 移除set集合中的  被删除的 值为val的 下标
        idx.get(lastNum).remove(nums.size() - 1);// 移除set集合中的  被替换的（原先在列表尾部的元素的）下标
        if (i < nums.size() - 1) {// 如果 i == nums.size() - 1，说明val就是最后一个元素，不可以再赋予新下标，不然相当于没删掉
            idx.get(lastNum).add(i);// 给原先在列表尾部的元素的重新 赋于新的下标值，就是被删除的val原先的位置 i
        }
        if (idx.get(val).size() == 0) {
            idx.remove(val);// 如果啊，删完之后，val的下标集合空了，那就把这个集合也抹去
        }
        nums.remove(nums.size() - 1);// 删除val
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if(nums.size() == 0)
            return -1;
        Random r = new Random();
        int loc = r.nextInt(nums.size());
        return nums.get(loc);//只需要随机生成一个列表中的索引，就能够得到一个随机元素
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
// @lc code=end