package xsw.Nov_Dec;/*

 */

import java.util.HashSet;
import java.util.LinkedList;

public class 第220场周赛 {

    public static void main(String[] args) {
        int[] nums = {761, -6864, 2455, 2395, 8254, -6096, -6211, -5992, 1378, -5233, 6324, 4684, -1036, 2542, 3860, -9863, 3234, 1972, 9299, 1548, 9491, 6545, 6876, 5928, 7270, -5661, -4473, -5318, 8312, 7670, -9758, -1754, -5001, -5210, 2921, -6700, -1536, 6454, 1185, -87, -4528, -8573, 3314, -378, -1036, -6384, -6626, 8897, -6031, -179, 1562, -1653, -5517, 2809, -738, 6195, 2320, 2908, -3579, 7371, 9486, -2571, -1406, 2400, -5379, -8841, -8695, -2353, 6061, -669, 6216, 7384, 8421, -4843, 8649, -2926, 2524, 9660, -6508, 9959, -4754, -8825, -237, -1809, 4114, -9544, 5980, 3, 7748, 9785, 3262, 6305, -9987, -1493, 290, 8981, -2627, 8981, -9449, -7469, 3415, -3353, 7109, -9933, -532, -505, 2336, -4802, 9743, 6829, -556, 223, -1508, 6837, 8675, 1985, 5661, 545, -53, 6440, -4237, 8717, -7097, -2946, -3633, -8181, 9222, 9297, 7550, 4010, -4145, 5289, 1530, -9994, 5665, -1719, 9469, 5771, -1012, -3641, 9331, -3407, 283, 1982, 7231, -6248, -7506, -3372, -8784, -4670, 9003, 297, -3425, -5400, -2281, 451, 403, -7357, -5514, 795, -1583, 4056, -4223, -8099, -7407, -6267, 8864, 8583, -7941, 3438, 8854, -2768, -4963, -9059, -5988, 4401, 6533, 5211, -7113, -2978, 200, -1865, 1089, -1916, -2147, 2021, -9532, 2628, -3859, -6752, -2740, 1542, -8713, -3730, 1276, 5386, -8549, -1575, -1225, 6955, -2908, 7372, -8996, 4335, -8908, -1496, 9606, 1057, -3577, 9607, 3718, -1551, -1635, -1781, 5726, 8485, -2578, 8269, -5614, -9558, 1339, 549, 4107, 6803, -2998, -8484, -8995, 6020, -1212, -8961, 2808, 7294, -5378, -5451, 8289, -1798, -3203, 3169, 9988, 3518, 7629, -3079, -303, 3104, 4748, -2215, -9783, -1768, -6019, -7860, 335, 6873, 7136, 3181, -6978, -6477, 2302, -3681, -9851, -7429, 3296, 3262, -7535, -8921, 8462, 6039, 2443, -278, 3917, 667, 9029, -357, -6231, 9926, -6696, -3778, 2510, 7005, 1290, 4213, -4976, 9416, 3138, 4559, 6778, -9209, -768, -1844, 5105, 5349, -2946, -822, -906, 4528, 949, 6928, 7331, 4366, -8289, 7608, -3672, -8030, -48, 9975, -3517, 3716, -2883, 5322, 4457, -289, -5437, 4780, 1911, -1820, -4061, 9454, -5958, 2984, 7326, -3962, 1879, 5134, -1328, 2374, 7092, -9813, 6514, -2318, -8562, 5466, -8682, -7441, -7284, -7233, 6538, -3013, 1628, -7237, -82, 5426, 702, 8605, -4794, -5879, -2745, -6452, -5865, -6139, -3501};
        int subarray = maxResult(nums, 246);
        System.out.println(subarray);
    }

    public static String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != '-' && number.charAt(i) != ' ') {
                sb.append(number.charAt(i));
            }
        }
        int len = sb.length();
        int j = 0;
        if (len % 3 != 1) {
            for (int i = 0; i < sb.length(); i++) {
                ++j;
                if (j == 3 && i < sb.length() - 1) {
                    sb.insert(i + 1, '-');
                    ++i;
                    j = 0;
                }
            }
            return sb.toString();
        } else {
            for (int i = 0; i < sb.length(); i++) {
                ++j;
                if (j == 3) {
                    sb.insert(i + 1, '-');
                    ++i;
                    j = 0;
                }
                if (i == sb.length() - 4)
                    break;
            }
            sb.insert(sb.length() - 2, '-');
            return sb.toString();
        }
    }

    public static int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int maxNum = 0;
        for (Integer i : set) {
            maxNum += i;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int temp = 0;
        int max = 0;
        for (int num : nums) {
            if (!list.contains(num)) {
                list.addFirst(num);
                temp += num;
            } else {
                max = Math.max(max, temp);
                if (maxNum == max)
                    return maxNum;
                while (list.peekLast() != num) {
                    temp -= list.peekLast();
                    list.pollLast();
                }
                list.pollLast();
                list.addFirst(num);
            }
        }
        return Math.max(max, temp);
    }

    public static int maxResult(int[] nums, int k) {
        int len = nums.length;
        int end = k;
        int result = nums[0];
        int i = 0;
        while (i < len) {
            end = Math.min(i + k, len - 1);//最多可以往前跳 k 步，但不能跳出数组的边界。
            if(i + 1 > end)
                break;
            int max = findMax(nums, i + 1, end);
            result += nums[max];
            i = max;
        }
        return result;
    }
    private static int findMax(int[] copy, int start, int end) {
//        System.out.println();
        System.out.println(start + "->" + end);
        int max = start;
        int temp = copy[start];
        while (start <= end) {
            //System.out.print(copy[i]+" ");
            if (copy[start] > 0)//遇到第一个正数，返回
                return start;
            if (copy[start] > temp) {//找到一下负数里边的最大值
                max = start;
                temp = copy[start];
            }
            ++start;
        }
        //System.out.print(copy[max]+" ");
        if (start == copy.length)//前提是全负数，然后这一步能到最后，那就直接到最后啦，从中间停留就是浪费生命
            return start-1;
        return max;
    }

}
