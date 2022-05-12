package xsw.Nov_Dec;/*
659. 分割数组为连续子序列
给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。

如果可以完成上述分割，则返回 true ；否则，返回 false 。



示例 1：

输入: [1,2,3,3,4,5]
输出: True
解释:
你可以分割出这样两个连续子序列 :
1, 2, 3
3, 4, 5


示例 2：

输入: [1,2,3,3,4,4,5,5]
输出: True
解释:
你可以分割出这样两个连续子序列 :
1, 2, 3, 4, 5
3, 4, 5
示例 3：

输入: [1,2,3,4,4,5]
输出: False
提示：
    输入的数组长度范围为 [1, 10000]
*/

import java.util.*;

public class LeedCode659 {
    //不会，没有实现
    public static boolean isPossible1(int[] nums) {
        int[] count = new int[nums[nums.length-1]+1];
        for (int num : nums) {
            ++count[num];
        }
        System.out.println(Arrays.toString(count));
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 1; i < count.length; i++) {
            int[] temp = new int[3];
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,5};
        boolean possible = isPossible3(nums);
        System.out.println(possible);
    }


    //哈希表 + 最小堆
    public static boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums) {
            if (!map.containsKey(x)) {//如果哈希表中不存在以 x 结尾的子序列，表示无法接在后边，则新建一个
                map.put(x, new PriorityQueue<>());
            }
            //如果哈希表中存在以 x-1 结尾的子序列
            // 则取出以 x-1 结尾的最小的子序列长度，将子序列长度加 1 之后作为以 x 结尾的子序列长度
            // 此时，以 x-1 结尾的子序列减少了一个，以 x 结尾的子序列增加了一个。
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();//取出以 x-1 结尾的最小的子序列长度
                //如果取出上一步的长度后，以 x-1 结尾的子序列为空，说明x将接在这个子序列尾部，要更换子序列的结尾
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);//将子序列长度加 1 之后作为以 x 结尾的子序列长度
            } else {
                map.get(x).offer(1);//如果哈希表中不存在以 x-1 结尾的子序列，则新建的以 x 结尾的子序列长度为1
            }
        }
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            PriorityQueue<Integer> queue = map.get(key);
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }

    //贪心
    public static boolean isPossible2(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();//第一个哈希表存储数组中的每个数字的剩余次数
        Map<Integer, Integer> endMap = new HashMap<>();//第二个哈希表存储数组中的每个数字作为结尾的子序列的数量。
        //初始时，每个数字的剩余次数即为每个数字在数组中出现的次数，因此遍历数组，初始化第一个哈希表。
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0) + 1;
            countMap.put(x, count);
        }
        //遍历数组，更新两个哈希表。
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0);
            //只有当一个数字的剩余次数大于 0 时，才需要考虑这个数字是否属于某个子序列,否则，x为一个子序列的第一个数
            if (count > 0) {
                //首先判断是否存在以 x-1 结尾的子序列，
                //即根据第二个哈希表判断 x-1 作为结尾的子序列的数量是否大于 0
                int prevEndCount = endMap.getOrDefault(x - 1, 0);
                //如果大于 0，则将元素 x 加入该子序列中
                if (prevEndCount > 0) {
                    countMap.put(x, count - 1);//由于 x 被使用了一次，因此需要在第一个哈希表中将 x 的剩余次数减 1。
                    // 又由于该子序列的最后一个数字从 x-1 变成了 x，
                    // 因此需要在第二个哈希表中
                    endMap.put(x - 1, prevEndCount - 1);//将 x-1 作为结尾的子序列的数量减 1
                    endMap.put(x, endMap.getOrDefault(x, 0) + 1);// 将 x 作为结尾的子序列的数量加 1
                } else {//x 为一个子序列的第一个数
                    //为了得到长度至少为 3 的子序列，x+1 和 x+2 必须在子序列中
                    //因此需要判断在第一个哈希表中 x+1 和 x+2 的剩余次数是否都大于 0。
                    int count1 = countMap.getOrDefault(x + 1, 0);
                    int count2 = countMap.getOrDefault(x + 2, 0);
                    //当 x+1 和 x+2 的剩余次数都大于 0 时
                    //可以新建一个长度为 3 的子序列 [x,x+1,x+2]
                    //否则，无法得到长度为 3 的子序列 [x,x+1,x+2]，因此无法完成分割，
                    if (count1 > 0 && count2 > 0) {
                        //这三个数都被使用了一次，因此需要在第一个哈希表中将这三个数的剩余次数分别减 1
                        countMap.put(x, count - 1);
                        countMap.put(x + 1, count1 - 1);
                        countMap.put(x + 2, count2 - 1);
                        //由于该子序列的最后一个数字是 x+2，因此需要在第二个哈希表中将 x+2 作为结尾的子序列的数量加 1
                        endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                    } else {
                        //无法完成分割，返回 false。
                        return false;
                    }
                }
            }
        }
        //如果整个数组遍历结束时，没有遇到无法完成分割的情况，则可以完成分割，返回 true。
        return true;
    }

    public static boolean isPossible3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(int num : nums) {
            int len = 0, preCount = 1, next = num;
            while(map.getOrDefault(next, 0) >= preCount) {
                preCount = map.get(next);
                map.put(next, preCount - 1);
                len++;
                next++;
            }
            if(len > 0 && len < 3) return false;
        }
        return true;
    }
}
