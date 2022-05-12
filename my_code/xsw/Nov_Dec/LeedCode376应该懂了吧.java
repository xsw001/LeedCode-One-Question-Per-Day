package xsw.Nov_Dec;/*
376. 摆动序列
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列
第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。
通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

示例 1:

输入: [1,7,4,9,2,5]
输出: 6
解释: 整个序列均为摆动序列。
示例 2:

输入: [1,17,5,10,13,15,10,5,16,8]
输出: 7
解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
示例 3:

输入: [1,2,3,4,5,6,7,8,9]
输出: 2
进阶:
你能否用 O(n) 时间复杂度完成此题?
*/

import java.util.LinkedList;

public class LeedCode376应该懂了吧 {
    //过不了
    //22 / 27 个通过测试用例  解决不了重复元素（[0,0,0,0]，【1, 1, 1, 2, 2, 2, 1, 1, 1, 3, 3, 3, 2, 2, 2】）
    //我这也是贪心的思想，就是实现太复杂，而且还不对
    public static int wiggleMaxLength2(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return len;
        int start = 1;
        while (start < len - 1) {
            if (nums[start] > nums[start - 1] && nums[start + 1] < nums[start])
                break;
            if (nums[start] - nums[start - 1] < 0 && nums[start + 1] - nums[start] > 0)
                break;
            ++start;
        }
        int maxLen = 2;
        int i = start;
        while (i < len - 1) {
            if (i < len - 1 && nums[i] < nums[i - 1]) {
                while (i < len - 1 && nums[i + 1] < nums[i]) {
                    ++i;
                }
                if (i < len - 1)
                    ++maxLen;
            } else if (i < len - 1 && nums[i] > nums[i - 1]) {
                while (i < len - 1 && nums[i + 1] > nums[i]) {
                    ++i;
                }
                if (i < len - 1)
                    ++maxLen;
            }
            ++i;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2, 1, 1, 1, 3, 3, 3, 2, 2, 2};
        //int[] nums = {53, 46, 57, 93, 98, 42, 80, 82, 9, 41, 55, 69, 84, 82, 79, 30, 79};
        int length = wiggleMaxLength_tanxin(nums);
        System.out.println(length);
    }

    //不行
    public static int wiggleMaxLength1(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return len;
        int t1 = help(nums);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[len - 1 - i] = nums[i];
        }
        int t2 = help(arr);
        return Math.max(t1, t2);
    }

    public static int help(int[] nums) {
        int len = nums.length;
        LinkedList<Integer> list = new LinkedList<>();
        int i = len - 1;
        list.addFirst(nums[i--]);
        while (i >= 0 && nums[i] == nums[len - 1]) {
            --i;
        }
        if (i <= 0)
            return 1;
        list.addFirst(nums[i--]);
        boolean greater = false;
        if (list.peekFirst() > list.peekLast())
            greater = true;
        while (i >= 0) {
            if (!greater) {
                if (nums[i] < list.peekFirst()) {
                    list.addFirst(nums[i]);
                    greater = false;
                }
            } else {
                if (nums[i] > list.peekFirst()) {
                    list.addFirst(nums[i]);
                    greater = true;
                }
            }
            --i;
        }
        System.out.println(list);
        return list.size();
    }

    //秒啊
    public static int wiggleMaxLength(int[] nums) {
        int l = nums.length;
        if (nums.length < 2) {
            return l;
        }
        int result = 1;
        int ud = 0;
        for (int i = 1; i < l; i++) {
            if (nums[i] < nums[i - 1] && ud != -1) {
                ud = -1;// ud = -1  表示你既然是ud != -1进来的，那么下次你还是这样你就是进不来
                result++;
            }
            if (nums[i] > nums[i - 1] && ud != 1) {
                ud = 1;
                result++;
            }
        }
        return result;
    }

    //神了
    public static int wiggleMaxLength_dongtai(int[] nums) {
        //down[i] 表示 nums[0:i] 中最后两个数字递减的最长摆动序列长度，只有一个数字时默认为 1。
        //注意到 down 和 up 只和前一个状态有关，所以我们可以优化存储，分别用一个变量即可。
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return nums.length == 0 ? 0 : Math.max(down, up);
    }

    //贪心
    // 我们记录当前序列的上升下降趋势。每次加入一个新元素时
    // 用新的上升下降趋势与之前对比，如果出现了「峰」或「谷」, 答案加一
    // 并更新当前序列的上升下降趋势
    public static int wiggleMaxLength_tanxin(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];//与上一个元素的差
        int ret = prevdiff != 0 ? 2 : 1;//解决了全部是重复元素的问题
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) /*谷*/|| (diff < 0 && prevdiff >= 0/*峰*/)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }
}
