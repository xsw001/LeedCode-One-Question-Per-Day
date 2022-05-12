package Tiger2022.April;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
386. 字典序排数
给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。

你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。



示例 1：

输入：n = 13
输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
示例 2：

输入：n = 2
输出：[1,2]


提示：

1 <= n <= 5 * 104
通过次数51,233提交次数67,869
 */
public class 字典序排数_386 {

    @Test
    public void test() throws Exception {
        System.out.println(lexicalOrder(23));
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ret.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ret;
    }


    // error
    public List<Integer> lexicalOrder1(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9 && i <= n; i++) {
            int j = i;
            list.add(j);
            while (j <= n) {
                j *= 10;
                for (int k = 0; k <= 9; k++) {
                    if (j + k > n)
                        break;
                    list.add(j + k);
                }
                if (list.get(list.size() - 1) >= n)
                    break;
            }
        }
        return list;
    }
}
