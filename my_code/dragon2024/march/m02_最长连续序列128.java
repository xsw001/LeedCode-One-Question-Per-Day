package dragon2024.march;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;


/*
 * 128.最长连续序列
 * 中等

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class m02_最长连续序列128 {

    @Test
    public void test() {
        int[] arr = {1,2,2,5,3,4};
        System.out.println(longestConsecutive(arr));
    }

    public int longestConsecutive(int[] nums) {
        int ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        //map多余了，但这是思考的过程：先想到了用map减少遍历次数，又想到不是起点直接跳过
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int cur : set) {
            int next = cur + 1, count = 1;
            if(set.contains(cur-1)){
                continue;
            }
            while(set.contains(next)){
                if(map.containsKey(next)){
                    count += map.get(next);
                    break;
                }
                ++count;
                ++next;
            }
            map.put(cur, count);
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
