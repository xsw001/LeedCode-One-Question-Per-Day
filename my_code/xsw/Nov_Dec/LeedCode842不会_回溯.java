package xsw.Nov_Dec;/*
842. 将数组拆分成斐波那契序列
给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。

形式上，斐波那契式序列是一个非负整数列表 F，且满足：

0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
F.length >= 3；
对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。

返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。

示例 1：

输入："123456579"
输出：[123,456,579]
示例 2：

输入: "11235813"
输出: [1,1,2,3,5,8,13]
示例 3：

输入: "112358130"
输出: []
解释: 这项任务无法完成。
示例 4：

输入："0123"
输出：[]
解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
示例 5：

输入: "1101111"
输出: [110, 1, 111]
解释: 输出 [11,0,11,11] 也同样被接受。
        提示：
        1 <= S.length <= 200
        字符串 S 中只含有数字。
*/

import java.util.ArrayList;
import java.util.List;

public class LeedCode842不会_回溯 {
    //回溯 + 剪枝
    /*使用列表存储拆分出的数，回溯过程中维护该列表的元素，列表初始为空。
    遍历字符串的所有可能的前缀，作为当前被拆分出的数，
    然后对剩余部分继续拆分，直到整个字符串拆分完毕。*/
    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<Integer>();
        backtrack(list, S, S.length(), 0, 0, 0);
        return list;
    }
    public static boolean backtrack(List<Integer> list, String S, int length, int index, int sum, int prev) {
        if (index == length) {
            return list.size() >= 3;//回溯需要带返回值，表示是否存在符合要求的斐波那契式序列。
        }
        long currLong = 0;
        for (int i = index; i < length; i++) {
            // 拆分出的数如果不是 0，则不能以 0 开头，因此如果字符串剩下的部分以 0 开头
            // 就不需要考虑拆分出长度大于 1 的数，因为长度大于 1 的数以 0 开头是不符合要求的
            // 不可能继续拆分得到斐波那契式序列；
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            currLong = currLong * 10 + S.charAt(i) - '0';
            // 如果拆分出的数大于 2^31−1，则不符合要求
            // 长度更大的数的数值也一定更大，一定也大于 2^31-1
            // 因此不可能继续拆分得到斐波那契式序列；
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) currLong;
            //如果列表中至少有 2 个数，并且拆分出的数已经大于最后 2 个数的和，就不需要继续尝试拆分了。
            if (list.size() >= 2) {
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;
                }
            }
            list.add(curr);
            if (backtrack(list, S, length, i + 1, prev + curr, curr)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        return false;//回溯需要带返回值，表示是否存在符合要求的斐波那契式序列。
    }

    public static void main(String[] args) {
        String S = "1101111";
        List<Integer> split = splitIntoFibonacci(S);
        System.out.println(split);
    }
}
