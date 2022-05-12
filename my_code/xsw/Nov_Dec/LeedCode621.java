package xsw.Nov_Dec;/*
621. 任务调度器
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。



示例 1：

输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
示例 2：

输入：tasks = ["A","A","A","B","B","B"], n = 0
输出：6
解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
诸如此类
示例 3：

输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
输出：16
解释：一种可能的解决方案是：
     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A


提示：

1 <= task.length <= 104
tasks[i] 是大写英文字母
n 的取值范围为 [0, 100]
*/

import java.util.*;

public class LeedCode621 {

    //残次品，没写出来
    public static int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        if (n == 0)
            return len;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        System.out.println(map);
        int num = 0;
        while (!map.isEmpty()) {
            Set<Character> keySet = map.keySet();
            if (keySet.size() > n) {
                for (Character key : keySet) {
                    Integer value = map.get(key);
                    if (value != 0) {
                        ++num;
                        map.put(key, value - 1);
                    }
                    if (map.get(key) == 0)
                        map.remove(key);
                }
                System.out.println(map);
            }
        }
        return num;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C'};
        int n = 2;
        int interval = leastInterval2(tasks, n);
        System.out.println(interval);
    }

    public static int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        // 任务总数
        int m = freq.size();
        //可以用二元组 (nextValid_i, rest_i) 表示第 i 个任务
        List<Integer> nextValid = new ArrayList<Integer>();//表示其因冷却限制，最早可以执行的时间
        List<Integer> rest = new ArrayList<Integer>();//表示其剩余执行次数
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        //初始化，初始时，所有的inextValid 均为 1，而 rest 为任务 i 在数组 tasks 中出现的次数。
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            nextValid.add(1);//inextValid 均为 1
            rest.add(value);//出现的次数
        }
        //System.out.println(nextValid);
        //System.out.println(rest);


        int time = 0;// 记录当前的时间
        for (int i = 0; i < tasks.length; ++i) {
            ++time;
            int minNextValid = Integer.MAX_VALUE;
            /*
            而对于 time 的更新，我们可以选择将其不断增加 1，模拟每一个时间片。
            但这会导致我们在 CPU 处于待命状态时，对二元组进行不必要的遍历。
            为了减少时间复杂度，我们可以在每一次遍历之前，将 time 更新为所有 nextValid_i 中的最小值
            直接「跳过」待命状态，保证每一次对二元组的遍历都是有效的。
            需要注意的是，只有当这个最小值大于 time 时，才需要这样快速更新。
            */
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0) {
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            time = Math.max(time, minNextValid);
            int best = -1;
            /*
            需要选择不在冷却中并且剩余执行次数最多的那个任务，
            也就是说，我们需要找到满足 nextValid_i ≤ time 的并且 rest_i  最大的索引 i。
            */
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                    if (best == -1 || rest.get(j) > rest.get(best)) {
                        best = j;
                    }
                }
            }
            //更新nextValid、rest，记录任务 i 下一次冷却结束的时间以及剩余执行次数
            //如果更新后的 rest_i=0，那么任务 i 全部做完，我们在遍历二元组时也就可以忽略它了。
            nextValid.set(best, time + n + 1);
            rest.set(best, rest.get(best) - 1);
        }
        return time;
    }

    public static int leastInterval3(char[] tasks, int n) {
        int[] buckets = new int[26];
        for(int i = 0; i < tasks.length; i++){
            buckets[tasks[i] - 'A']++;
        }
        Arrays.sort(buckets);
        int maxTimes = buckets[25];//maxTimes为出现次数最多的那个任务出现的次数
        int maxCount = 1;//maxCount为一共有多少个任务和出现最多的那个任务出现次数一样。
        for(int i = 25; i >= 1; i--){
            if(buckets[i] == buckets[i - 1])
                maxCount++;
            else
                break;
        }
        int res = (maxTimes - 1) * (n + 1) + maxCount;
        return Math.max(res, tasks.length);
    }
}
