package dragon2024.march;

import org.junit.Test;

import java.util.Arrays;

public class m09_移动零283 {

    @Test
    public void test() {
        int[] arr = {1, 2, 5,0,0,12,34};
        moveZeroes1(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void moveZeroes(int[] nums) {
        int l = 0;
        while (l < nums.length && nums[l] != 0){
            l++;
        }
        for (int i = l+1; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                while (l < nums.length && nums[l] != 0){
                    l++;
                }
            }
        }
    }

    public void moveZeroes1(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }
}
