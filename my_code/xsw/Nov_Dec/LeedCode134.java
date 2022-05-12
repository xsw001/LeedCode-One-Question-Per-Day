package xsw.Nov_Dec;/*

134. 加油站
在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

说明:

如果题目有解，该答案即为唯一答案。
输入数组均为非空数组，且长度相同。
输入数组中的元素均为非负数。
示例 1:

输入:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

输出: 3

解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
示例 2:

输入:
gas  = [2,3,4]
cost = [3,4,3]

输出: -1

解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
*/

public class LeedCode134 {

    //超时
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            if (gas[i] >= cost[i]) {// 只有第一次有足够的汽油可以让你行驶到下一个加油站，才有可能绕一圈
                /*
                * 见最后一个方法，其实不需要重新排列数组，到了边界时用gas[(j + 1) % n]处理，就像循环链表里的那个
                * */
                int res = helper(i, gas, cost);
                if (res == i)
                    return res;
            }
        }
        return -1;
    }

    private static int helper(int i, int[] gas, int[] cost) {
        //思路： 从进来的节点 i 处重新排列数组，然后遍历
        int[] gasT = new int[gas.length];
        int[] costT = new int[gas.length];
        int start = 0, end = i;
        while (end < cost.length) {
            gasT[start] = gas[end];
            costT[start] = cost[end];
            ++end;
            ++start;
        }
        for (int j = 0; j < i; j++) {
            gasT[start] = gas[j];
            costT[start] = cost[j];
            ++start;
        }
        //重新排列后，依次计算能否到下一站
        int curGas = gasT[0];//curGas 当前的汽油储量
        for (int k = 0; k < gasT.length - 1; k++) {
            curGas = curGas - costT[k];//计算下一站是否能够到达
            if (curGas < 0)//到不了就不用继续了，over
                return -1;
            curGas += gasT[k + 1];//到了，加油
        }
        if (curGas - costT[costT.length - 1] < 0)//因为最后一站没去，让它去，到不了的话就功亏一篑
            return -1;
        return i;//跑了一圈，把起点返回
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4,5};
        int[] cost = {3, 4, 5, 1,2};
        int i = canCompleteCircuit2(gas, cost);
        System.out.println(i);
    }

    //上述方法超时，改用循环链表
    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        ListNode gasHead = new ListNode(gas[0]);//油的头结点
        ListNode costHead = new ListNode(cost[0]);//消耗的头结点
        ListNode gasTail = gasHead;//油的尾结点
        ListNode costTail = costHead;//消耗的尾结点
        int len = gas.length;
        for (int i = 1; i < len; i++) {//建立链表，尾插法
            ListNode temp = new ListNode(gas[i]);
            gasTail.next = temp;
            gasTail = temp;
            temp = new ListNode(cost[i]);
            costTail.next = temp;
            costTail = temp;
        }
        //首尾相连
        gasTail.next = gasHead;
        costTail.next = costHead;
        //用 i 控制跑了多远，最多绕一圈（len)
        for (int i = 0; i < len; i++) {
            int curGas = 0;//curGas 当前的汽油储量
            if (gasHead.val >= costHead.val) {
                //记录一下当前节点，方便没有绕一圈后返回
                gasTail = gasHead;
                costTail = costHead;
                int j = 0;
                for (; j < len; j++) {
                    curGas += gasHead.val - costHead.val;
                    if (curGas < 0)//到不了下一站，就不用继续了，退出此次尝试
                        break;
                    gasHead = gasHead.next;
                    costHead = costHead.next;
                }
                if (j == len)
                    return i;
                else {
                    //没有绕一圈后，回到进来的加油站
                    gasHead = gasTail;
                    costHead = costTail;
                }
            }
            //继续尝试下一个起点
            gasHead = gasHead.next;
            costHead = costHead.next;
        }
        return -1;
    }

    //优化：如果不能环绕一周，就从第一个无法到达的加油站开始继续检查。
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        ListNode gasHead = new ListNode(gas[0]);//油的头结点
        ListNode costHead = new ListNode(cost[0]);//消耗的头结点
        ListNode gasTail = gasHead;//油的尾结点
        ListNode costTail = costHead;//消耗的尾结点
        int len = gas.length;
        for (int i = 1; i < len; i++) {//建立链表，尾插法
            ListNode temp = new ListNode(gas[i]);
            gasTail.next = temp;
            gasTail = temp;
            temp = new ListNode(cost[i]);
            costTail.next = temp;
            costTail = temp;
        }
        //首尾相连
        gasTail.next = gasHead;
        costTail.next = costHead;
        //用 i 控制跑了多远，最多绕一圈（len)
        for (int i = 0; i < len; i++) {
            int curGas = 0;//curGas 当前的汽油储量
            if (gasHead.val >= costHead.val) {// 只有第一次有足够的汽油可以让你行驶到下一个加油站，才有可能绕一圈,进行尝试
                //记录一下当前节点，方便没有绕一圈后返回
                gasTail = gasHead;
                costTail = costHead;
                int j = 0;
                for (; j < len; j++) {
                    curGas += gasHead.val - costHead.val;
                    if (curGas < 0)//到不了下一站，就不用继续了，退出此次尝试
                        break;
                    gasHead = gasHead.next;
                    costHead = costHead.next;
                }
                if (j == len)
                    return i;
                else
                    i += j-1;//从第一个无法到达的加油站开始继续尝试
            }else {
                //继续尝试下一个起点
                gasHead = gasHead.next;
                costHead = costHead.next;
            }
        }
        return -1;
    }

    //最快的答案
    // https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
    public static int canCompleteCircuit3(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }

        return spare < 0 ? -1 : (minIndex + 1) % len;
    }

    //简单的思路，和超时的一样，这就是差距
    public static int canCompleteCircuit4(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                //减去花费的加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                //j 回到了 i
                if (j == i) {
                    return i;
                }
            }
            //最远距离绕到了之前，所以 i 后边的都不可能绕一圈了
            if (j < i) {
                return -1;
            }
            //i 直接跳到 j，外层 for 循环执行 i++，相当于从 j + 1 开始考虑
            i = j;

        }
        return -1;
    }
}
