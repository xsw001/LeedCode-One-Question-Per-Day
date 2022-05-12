package xsw.Nov_Dec;/*135. 分发糖果

老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
相邻的孩子中，评分高的孩子必须获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

示例 1:

输入: [1,0,2]
输出: 5
解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
示例 2:

输入: [1,2,2]
输出: 4
解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
*/


import java.util.Arrays;

public class LeedCode135 {

    public static int candy(int[] ratings) {
        int l = ratings.length;
        if (l < 2)
            return l;
        int[] num = new int[l];
        Arrays.fill(num, 1);
        int res = 0;
        for (int i = 0; i < l - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                num[i + 1] = num[i] + 1;
            }
            if (ratings[i] > ratings[i + 1]) {
                int j = i;
                while(j < l-1 && ratings[j] > ratings[j + 1])
                    ++j;
                int idx = j-1;
                while (idx >= i){
                    num[idx] = num[idx+1]+1;
                    idx--;
                }
                if (i>0 && ratings[i] > ratings[i - 1]) {
                    num[i] = Math.max(num[i-1] + 1,num[i]);
                }
                i = j-1;
            }
        }
        //System.out.println(Arrays.toString(num));
        for (int i : num) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ratings = {1,0,2,2,2,1,1,3,3,3,4,5,6,5,4,5,0,0,0,4,1,0};
        int candy = candy(ratings);
        System.out.println(candy);
    }

}
