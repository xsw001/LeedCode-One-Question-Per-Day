package xsw.April.个人赛;
/*
3. 魔塔游戏
通过的用户数1070
尝试过的用户数1691
用户总通过次数1091
用户总提交次数4372
题目难度Medium
小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。

小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。

示例 1：

输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]

输出：1

解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。

示例 2：

输入：nums = [-200,-300,400,0]

输出：-1

解释：调整访问顺序也无法完成全部房间的访问。

提示：

1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class medium_3 {

    public static int magicTower(int[] nums) {
        int blood = 1;
        ArrayList<Integer> monster = new ArrayList<>();
        for (int num : nums) {
            blood += num;
            if(num < 0)
                monster.add(num);
        }
        if(blood < 0)
            return -1;
        return monster.size();
    }

    public static void main(String[] args) {
        int[] data = {100, 100, 100, -250, -60, -140, -50, -50, 100, 150};
        System.out.println(magicTower1(data));
    }


    public static int magicTower1(int[] nums) {
        int len = nums.length;
        long last = 0;
        long sum = 1;
        int res = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x, y) -> y - x);
        for (int num : nums) {
            if (num >= 0) {
                sum += num;
            } else {
                priorityQueue.add(-num);
                if (-num > sum) {
                    Integer poll = priorityQueue.poll();
                    res++;
                    sum += poll;
                    last += poll;
                }
                sum += num;
            }
        }
        if (sum <= last) {
            res = -1;
        }
        return res;
    }
    public int magicTower2(int[] nums) {
        long hp = 1;
        for (int i : nums) {
            hp += i;
        }
        if (hp <= 0)
            return -1;
        int res = 0;
        hp = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)
                pq.add(nums[i]);
            hp += nums[i];
            while (!pq.isEmpty() && hp <= 0) {
                int t = pq.poll();
                hp -= t;
                res++;
            }
            if (hp <= 0)
                return -1;
        }
        return res;
    }
}