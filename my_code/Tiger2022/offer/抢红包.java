package Tiger2022.offer;
/*

 */

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class 抢红包 {

    @Test
    public void test() throws Exception {
        for (Integer i : divideRedPackage(1000, 10)) {
            System.out.println("抢到" + new BigDecimal(i) + "元");
        }
        System.out.println("------------------");
        for (Integer i : divideRedPackage2(1000, 10)) {
            System.out.println("抢到" + new BigDecimal(i) + "元");
        }
    }

    // 二倍均值法
    public List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        ArrayList<Integer> ans = new ArrayList<>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // 随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            ans.add(amount);
        }
        ans.add(restAmount);
        return ans;
    }

    // 线段切割法
    public List<Integer> divideRedPackage2(Integer totalAmount, Integer totalPeopleNum) {
        ArrayList<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(totalAmount);
        while (boards.size() <= totalPeopleNum) {
            int index = new Random().nextInt(totalAmount - 1) + 1;
            if (boards.contains((index)))
                continue;
            boards.add(index);
        }
        Collections.sort(boards);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < boards.size() - 1; i++) {
            list.add(boards.get(i + 1) - boards.get(i));
        }
        return list;
    }
}
