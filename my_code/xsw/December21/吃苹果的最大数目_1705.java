package xsw.December21;
/*
1705. 吃苹果的最大数目
有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。

你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。

给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。



示例 1：

输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
输出：7
解释：你可以吃掉 7 个苹果：
- 第一天，你吃掉第一天长出来的苹果。
- 第二天，你吃掉一个第二天长出来的苹果。
- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
- 第四天到第七天，你吃的都是第四天长出来的苹果。
示例 2：

输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
输出：5
解释：你可以吃掉 5 个苹果：
- 第一天到第三天，你吃的都是第一天长出来的苹果。
- 第四天和第五天不吃苹果。
- 第六天和第七天，你吃的都是第六天长出来的苹果。


提示：

apples.length == n
days.length == n
1 <= n <= 2 * 104
0 <= apples[i], days[i] <= 2 * 104
只有在 apples[i] = 0 时，days[i] = 0 才成立
通过次数7,847提交次数20,819
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class 吃苹果的最大数目_1705 {

    /*  考虑不周，苹果能保存先不吃
    *   [2,1,10]
        [2,10,1]
    * */
    public int eatenApples1(int[] apples, int[] days) {
        int n = apples.length;
        boolean[] eat = new boolean[20000 + n];
        for (int i = 0; i < n; i++) {
            if (apples[i] == 0)
                continue;
            if (apples[i] == 1)
                eat[i] = true;
            else {
                for (int j = 0; j < days[i]; j++) {
                    eat[i + j] = true;
                }
            }
        }
        int ans = 0;
        for (boolean b : eat) {
            if (b)
                ++ans;
        }
        return ans;
        /*
        int count=0;
        int[] dp = new int[apples.length+20000]; //用dp数组标志已结算的苹果
        for(int i = apples.length-1;i>=0;i--){
            for(int j=i + days[i] - 1;j>=i && apples[i]>0;j--){ //遍历从未开始结算处开始填充苹果
                if(dp[j]==0){
                    dp[j] = Math.max(j - apples[i] + 2, i + 1);
                    count++;
                    apples[i]--;
                }else{
                    j = dp[j] - 1;
                }
            }
        }
        return count;
         */
    }

    @Test
    public void test() {
        int[] data = {2, 1, 10};
        int[] days = {2, 10, 1};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(eatenApples(data, days));
    }

    /*
        计算吃苹果的最大数目分成两个阶段
            第一阶段是第 0 天到第 n - 1 天，即天数在数组下标范围内
            第二阶段是第 n 天及以后，即天数在数组下标范围外。
    */
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        // 只有在 apples[i] = 0 时，days[i] = 0 才成立
        if (n == 1 && (apples[0] == 0 || days[0] == 0))
            return 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int ans = 0;
        //在第一阶段，由于每天树上都可能长出苹果，因此需要对每一天分别计算是否能吃到苹果，并更新优先队列
        for (int i = 0; i < n; i++) {
            //将当天的苹果放进去
            queue.add(new int[]{apples[i], i + days[i] - 1});
            //将优先队列中的所有腐烂日期小于等于当前日期的元素取出，这些元素表示已经腐烂的苹果，无法食用
            while (!queue.isEmpty() && queue.peek()[1] < i)
                queue.poll();
            //如果优先队列不为空，则当天可以吃 1 个苹果
            if (queue.isEmpty())
                continue;
            //将优先队列的队首元素的数量减 1，如果队首元素的数量变成 0 则将丢弃，否则入队
            int[] last = queue.poll();
            if (last[0] > 1) {
                last[0]--;
                queue.add(last);
            }
            ++ans;
        }
        /*在第二阶段，由于树上不会再长出苹果，因此只需要考虑能吃到的苹果数量。
       由于优先队列中的每个元素的数量可能很大，因此需要根据当前日期和优先队列的队首元素的腐烂日期和数量计算能吃到的苹果数量。*/
        while (!queue.isEmpty()) {
            //首先将优先队列中的所有腐烂日期小于等于 n 的元素取出
            while (!queue.isEmpty() && queue.peek()[1] < n)
                queue.poll();
            //如果优先队列不为空，则根据优先队列的队首元素计算能吃到的苹果数量
            if (queue.isEmpty())
                break;
            /*
                假设优先队列的队首元素的腐烂日期是 x，数量是 y，其中 x >= n，则有 y 个苹果，距离腐烂还有 x - n 天
                因此能吃到的苹果数量是 curr=min(x−n+1,y)。
                将优先队列的队首元素 (x, y) 取出并将日期增加 curr
                重复上述操作直到优先队列为空，即可得到吃苹果的最大数目。
             */
            int[] last = queue.poll();
            int num = Math.min(last[0], last[1] - n + 1);
            ans += num;
            n += num;
        }
        return ans;
    }

}