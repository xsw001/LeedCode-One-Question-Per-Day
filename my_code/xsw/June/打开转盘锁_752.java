package xsw.June;
/*
752. 打开转盘锁
你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。



示例 1:

输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
输出：6
解释：
可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
因为当拨动到 "0102" 时这个锁就会被锁定。
示例 2:

输入: deadends = ["8888"], target = "0009"
输出：1
解释：
把最后一位反向旋转一次即可 "0000" -> "0009"。
示例 3:

输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
输出：-1
解释：
无法旋转到目标数字且不被锁定。
示例 4:

输入: deadends = ["0000"], target = "8888"
输出：-1


提示：

死亡列表 deadends 的长度范围为 [1, 500]。
目标数字 target 不会在 deadends 之中。
每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
通过次数45,594提交次数91,080
 */

import java.util.*;

public class 打开转盘锁_752 {

    public int openLock(String[] deadends, String target) {
        if (target.equals("0000"))
            return 0;
        HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000"))
            return -1;

        LinkedList<String> list = new LinkedList<String>();
        list.add("0000");
        HashSet<String> visited = new HashSet<>();
        visited.add("0000");

        int ans = 0;
        while (!list.isEmpty()) {
            ++ans;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String cur = list.poll();
                for (String s : next(cur)) {
                    if (target.equals(s))
                        return ans;
                    if (!visited.contains(s) && !dead.contains(s)) {
                        list.add(s);
                        visited.add(s);
                    }
                }
            }
        }
        return -1;
    }

    private Iterable<? extends String> next(String cur) {
        ArrayList<String> list = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            chars[i] = big(c);
            list.add(new String(chars));
            chars[i] = small(c);
            list.add(new String(chars));
            chars[i] = c;
        }
        return list;
    }

    private char small(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    private char big(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }

    public static void main(String[] args) {
        Integer[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        List<Integer> integers = Arrays.asList(data);
        System.out.println(integers);
        ArrayList<String> list = new ArrayList<>();

    }

}