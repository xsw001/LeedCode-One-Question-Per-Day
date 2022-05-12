package Tiger2022.offer.tengxun;

import java.util.*;


/*
15. 三数之和
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。



示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：

输入：nums = []
输出：[]
示例 3：

输入：nums = [0]
输出：[]


提示：

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
通过次数891,992提交次数2,546,842
请问您在哪类招聘中遇到此题？
 */
public class Main1 {
    public static void main(String[] args) {
        int[] a = new int[2010];
        int j = 0;
        for (int i = -1000; i < 1000; i++) {
            a[j++] = i;
        }

        long l = System.nanoTime();
        threeSum(a);
        System.out.println(System.nanoTime() - l);

        long ll = System.nanoTime();
        threeSum1(a);
        System.out.println(System.nanoTime() - ll);

        long lll = System.nanoTime();
        threeSum2(a);
        System.out.println(System.nanoTime() - lll);

    }

    //执行结果：
    //超出时间限制
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> set = new HashSet<>();

        for (int l = 0; l < nums.length; ++l) {
            for (int r = nums.length - 1; r > l + 1; --r) {
                int target = -nums[l] - nums[r];
                for (int i = r - 1; i > l; --i) {
                    if (nums[i] == target) {
                        int a = Math.min(Math.min(nums[l], nums[r]), nums[i]);
                        int b = a == nums[l] ? Math.min(nums[r], nums[i]) :
                                a == nums[r] ? Math.min(nums[l], nums[i]) :
                                        Math.min(nums[l], nums[r]);
                        int c = -a - b;
                        String k = " " + a + b + c;
                        if (set.contains(k))
                            continue;
                        set.add(k);
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[l]);
                        list.add(nums[r]);
                        list.add(nums[i]);
                        ans.add(list);
                    }
                }
            }
        }
        return ans;
    }

    // 快了10倍
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        Arrays.sort(nums);
        for (int l = 0; l < nums.length; ++l) {
            for (int r = nums.length - 1; r > l + 1; --r) {
                if (nums[r] == nums[l]) {
                    if (nums[l] == 0) {
                        String k = " 000";
                        if (set.contains(k))
                            break;
                        set.add(k);
                        ans.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
                    } else break;
                }
                int target = -nums[l] - nums[r];
                int left = l + 1, right = r - 1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < target)
                        left = mid + 1;
                    else
                        right = mid;
                }
                if (nums[left] == target) {
                    int a = Math.min(Math.min(nums[l], nums[r]), target);
                    int b = a == nums[l] ? Math.min(nums[r], target) :
                            a == nums[r] ? Math.min(nums[l], target) :
                                    Math.min(nums[l], nums[r]);
                    int c = -a - b;
                    String k = " " + a + b + c;
                    if (set.contains(k))
                        continue;
                    set.add(k);
                    ans.add(new ArrayList<Integer>(Arrays.asList(nums[l], nums[r], target)));
                }
            }
        }
        return ans;
    }

    // 快了30倍
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int first = 0; first < n && nums[first] <= 0; ++first) {
            if (first > 0 && nums[first] == nums[first - 1])
                continue;
            int r = n - 1;
            int target = -nums[first];
            for (int l = first + 1; l < n; l++) {
                if (l > first + 1 && nums[l] == nums[l - 1])
                    continue;
                while (l < r && nums[l] + nums[r] > target) --r;
                if (l == r)
                    break;
                if (nums[l] + nums[r] == target)
                    ans.add(new ArrayList<>(Arrays.asList(nums[l], nums[r], -target)));
            }
        }
        return ans;
    }
}
