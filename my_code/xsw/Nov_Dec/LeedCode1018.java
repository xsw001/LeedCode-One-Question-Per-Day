package xsw.Nov_Dec;

/*
1018. 可被 5 整除的二进制前缀
给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。

返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。

示例 1：

输入：[0,1,1]
输出：[true,false,false]
解释：
输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
示例 2：

输入：[1,1,1]
输出：[false,false,false]
示例 3：

输入：[0,1,1,1,1,1]
输出：[true,false,false,false,true,false]
示例 4：

输入：[1,1,1,0,1]
输出：[false,false,false,false,false]
提示：

1 <= A.length <= 30000
A[i] 为 0 或 1
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeedCode1018 {

    public static List<Boolean> prefixesDivBy5(int[] A) {
        int l = A.length;
        ArrayList<Boolean> list = new ArrayList<>();
        int num = 0, i = 0;
        while (i < l) {
            //考虑到数组 A 可能很长，如果每次都保留 N_i 的值，则可能导致溢出
            //由于只需要知道每个 N_i 是否可以被 5 整除，因此在计算过程中只需要保留余数即可。
            num = ((num * 2) + A[i]) % 5;
            System.out.println(num);
            list.add(num == 0);
            ++i;
        }
        return list;
    }

    public static void main(String[] args) {

        int[] a = {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0};
        List<Boolean> list = prefixesDivBy5(a);
        System.out.println(list);
    }
}
