package xsw.July;
/*

 */

import java.util.ArrayList;
import java.util.Arrays;

public class 数组中最大数对和的最小值_1877 {

    public static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int answer = 0;
        int l = 0, r = nums.length-1;
        while (l < r){
            answer = Math.max(answer, nums[l++]+nums[r--]);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}