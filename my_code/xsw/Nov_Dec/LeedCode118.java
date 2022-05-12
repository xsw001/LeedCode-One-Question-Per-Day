package xsw.Nov_Dec;/*
118. 杨辉三角
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

import java.util.ArrayList;
import java.util.List;

public class LeedCode118 {
    public static List<List<Integer>> generate(int numRows) {
        if(numRows==0)
            return null;
        ArrayList<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<Integer>();
        tempList.add(1);
        res.add(tempList);
        for (int i = 1; i < numRows; i++) {
            List<Integer> next = next(tempList);
            res.add(next);
            tempList = next;
        }
        return res;
    }
    public static List<Integer> next(List<Integer> list){
        List<Integer> arrList = new ArrayList<Integer>();
        arrList.add(1);
        for (int i = 0; i < list.size()-1; i++) {
            arrList.add(list.get(i)+list.get(i+1));
        }
        arrList.add(1);
        return arrList;
    }

    public static void main(String[] args) {
        int num  = 5;
        List<List<Integer>> list = generate(num);
        System.out.println(list);
//        List<Integer> arrList = new ArrayList<Integer>();
//        arrList.add(1);
//        arrList.add(1);
//        System.out.println(next(arrList));
    }
}
