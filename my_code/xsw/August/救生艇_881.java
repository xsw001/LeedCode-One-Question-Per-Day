package xsw.August;
/*
881. 救生艇
第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。

每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

返回载到每一个人所需的最小船数。(保证每个人都能被船载)。



示例 1：

输入：people = [1,2], limit = 3
输出：1
解释：1 艘船载 (1, 2)
示例 2：

输入：people = [3,2,2,1], limit = 3
输出：3
解释：3 艘船分别载 (1, 2), (2) 和 (3)
示例 3：

输入：people = [3,5,3,4], limit = 5
输出：4
解释：4 艘船分别载 (3), (3), (4), (5)
提示：

1 <= people.length <= 50000
1 <= people[i] <= limit <= 30000
通过次数23,625提交次数46,592
请问您在哪类招聘中遇到此题？
 */

import java.util.ArrayList;
import java.util.*;

public class 救生艇_881 {

    public static int numRescueBoats(int[] people, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int person : people) {
            map.put(person, map.getOrDefault(person, 0) + 1);
        }
        int num = 0;
        while (map.size() > 0) {
            ++num;
            int w = map.lastKey();
            if (map.get(w) == 1)
                map.remove(w);
            else
                map.put(w, map.get(w) - 1);
            Integer w1 = map.floorKey(limit - w);
            if (w1 != null)
                if (map.get(w1) == 1)
                    map.remove(w1);
                else
                    map.put(w1, map.get(w1) - 1);
        }
        return num;
    }


    public static void main(String[] args) {
        int[] data = {2, 1};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(numRescueBoats(data, 3));
    }

    /*
    比如排序数组：{a1, a2, a3, a4}，假设a4 + a1 <= limit, a4 + a2 <= limit。

这时候a4最理想的应当是带走a2，而不是a1，尽量留最小的使得更容易跟a3一起走。
但实际上，既然a1,a2都能跟a4一起走，而a4又大于a3，那么a3必定也能跟a1，a2一起走的。
所以无须考虑这个层次，直接考虑最重的能否跟最轻的一起走即可。
     */
    static class Solution {
        public int numRescueBoats(int[] people, int limit) {
            int ans = 0;
            Arrays.sort(people);
            int light = 0, heavy = people.length - 1;
            while (light <= heavy) {
                if (people[light] + people[heavy] <= limit) {// 最重的能否跟最轻的一起走
                    ++light;
                }
                --heavy;
                ++ans;
            }
            return ans;
        }
    }

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/boats-to-save-people/solution/jiu-sheng-ting-by-leetcode-solution-0nsp/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}