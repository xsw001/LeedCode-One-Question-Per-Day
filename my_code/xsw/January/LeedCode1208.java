package xsw.January;
/*
1208. 尽可能使字符串相等
给你两个长度相同的字符串，s 和 t。

将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。

用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。

如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。

如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。



示例 1：

输入：s = "abcd", t = "bcdf", cost = 3
输出：3
解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
示例 2：

输入：s = "abcd", t = "cdef", cost = 3
输出：1
解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
示例 3：

输入：s = "abcd", t = "acde", cost = 0
输出：1
解释：你无法作出任何改动，所以最大长度为 1。


提示：

1 <= s.length, t.length <= 10^5
0 <= maxCost <= 10^6
s 和 t 都只含小写英文字母。
 */

public class LeedCode1208 {

    //25 / 37 个通过测试用例
    /*
            "krpgjbjjznpzdfy"
            "nxargkbydxmsgby"
            14
        不会解决，怎么往回看
    */
    public static int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int maxLength = 0,temp = 0,maxCostTemp = maxCost;
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == t.charAt(i))
                ++temp;
            else{
                char a = s.charAt(i);
                char b = t.charAt(i);
                int cost = Math.abs(s.charAt(i) - t.charAt(i));
                if (maxCost != 0 && cost <= maxCost){
                    maxCost -= cost;
                    ++temp;
                }else {
                    maxLength = Math.max(maxLength,temp);
                    maxCost = maxCostTemp;
                    temp = 0;
                }
            }
        }
        return Math.max(maxLength,temp);
    }

    public static void main(String[] args) {
        String s = "jznpzdfy", t = "ydxmsgby";
        int cost = 14;
        System.out.println(equalSubstring1(s, t, cost));
    }

    /*
    1,当框内总消耗小于消耗阈值时，延展右侧窗口。
    2,当框内纵消耗大于消耗阈值时，当前长度定为最大值；右侧移动一格，左侧也随之移动一格，维持窗口为最大符合条件的大小。
    3,直到可以继续扩充时，才扩充右侧大小，所以在整个滑动过程中，窗口的大小是依据合法性只增不减
      且直到数据末尾时的窗口大小为所有子串中的最大合法窗口，且右指针与n重合，窗口大小为n-l。
    4,使用窗口合法非递减的性质，利用左右指针保证了算法的正确性。
    */
    public static int equalSubstring1(String s, String t, int maxCost) {
        int left = 0;   // 窗口左边界
        int cost = 0;   // 当前窗口消耗
        // i作为窗口右边界
        for (int i = 0; i < s.length(); i++)
        {
            cost += Math.abs(s.charAt(i) - t.charAt(i));
            // 如果当前窗口消耗大于总开销，则左边界++，移动窗口
            if (cost > maxCost)
            {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
        }
        return s.length() - left;
    }

    public int equalSubstring2(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];//已知一个数组 costs ，求：和不超过 maxCost 时最长的子数组的长度。
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }

    public int findSubArray(int[] nums) {

        int len=nums.length;//数组或字符串长度
        int left=0,right=0; //双指针，表示当前遍历的区间[left, right]，闭区间

        int sums=0;//用于统计子数组/子区间是否有效，根据题目可能会改成求和/计数
        int maxLength = 0; //保存最大的满足题目要求的子数组/子串长度

        while(right<len){ //当右边的指针没有搜索到数组/字符串的结尾
            sums=sums+nums[right];//增加当前右边指针的数字/字符的求和/计数
            /*while(区间[left, right]不符合题意){//此时需要一直移动左指针，直至找到一个符合题意的区间
                sums=sums-array[left];// 移动左指针前需要从counter中减少left位置字符的求和/计数
                left++;//真正的移动左指针，注意不能跟上面一行代码写反
            }*/
            //到内层的while结束时，我们找到了一个符合题意要求的子数组/子串
            maxLength=Math.max(maxLength,right-left+1);
            right++; //移动右指针，去探索新的区间
        }
        return maxLength;//找到符合题意的子数组/子字符串长度
    }

}