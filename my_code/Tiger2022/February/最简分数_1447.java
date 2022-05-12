package Tiger2022.February;
/*
1447. 最简分数
给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。



示例 1：

输入：n = 2
输出：["1/2"]
解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
示例 2：

输入：n = 3
输出：["1/2","1/3","2/3"]
示例 3：

输入：n = 4
输出：["1/2","1/3","1/4","2/3","3/4"]
解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
示例 4：

输入：n = 1
输出：[]


提示：

1 <= n <= 100
通过次数15,328提交次数23,033
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 最简分数_1447 {

    ArrayList<Integer> set;

    public List<String> simplifiedFractions(int n) {
        set = new ArrayList<>();
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        for (int i : arr)
            set.add(i);

        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                if (help(j, i))
                    ans.add(j + "/" + i);
            }
        }
        return ans;
    }

    public boolean help(int a, int b) {
        if (a == 1)
            return true;
        for (int i = 0; i < 25; i++) {
            Integer num = set.get(i);
            if (num > a)
                break;
            if (a % num == 0 && b % num == 0)
                return false;
        }
        return true;
    }

    @Test
    public void test() throws Exception {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};

        System.out.println(simplifiedFractions(5));


    }

}
