package xsw.Nov_Dec;/*
767. 重构字符串
给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

若可行，输出任意可行的结果。若不可行，返回空字符串。

示例 1:

输入: S = "aab"
输出: "aba"
示例 2:

输入: S = "aaab"
输出: ""
注意:

S 只包含小写字母并且长度在[1, 500]区间内。
*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LeedCode767 {
    //  35 / 62 个通过测试用例  死于"lovvv"
    public static String reorganizeString1(String S) {
        int[] words = new int[26];
        int len = S.length();

        for (int i = 0; i < len; i++) {
            ++words[S.charAt(i) - 'a'];
        }

        if (len % 2 == 1)
            ++len;
        String res = "";
        for (int word : words) {
            if (word > len / 2)
                return "";
        }

        while(res.length() != S.length()) {
            for (int i = 0; i < words.length; i++) {
                if(words[i]!=0) {
                    res += String.valueOf((char)(i+'a'));
                    --words[i];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String S = "blflxll";
        String string = reorganizeString3(S);
        System.out.println(string);
    }

    // 44/62 个通过测试用例 死于blflxll
    public static String reorganizeString(String S) {
        int[] words = new int[26];
        int len = S.length();

        for (int i = 0; i < len; i++) {
            ++words[S.charAt(i) - 'a'];
        }

        if (len % 2 == 1)
            ++len;
        for (int word : words) {
            if (word > len / 2)
                return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            while (words[i] != 0) {
                builder.append(String.valueOf((char) (i + 'a')));
                --words[i];
            }
        }
        System.out.println(builder);
        ArrayList<String> list = new ArrayList<String>();
        int start = 0, end = builder.length() - 1;
        while (end > start) {
            String s = String.valueOf(builder.charAt(start)) + String.valueOf(builder.charAt(end));
            list.add(s);
            ++start;
            --end;
        }
        if (S.length() % 2 == 1)
            list.add(String.valueOf(builder.charAt(start)));
        String result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (list.get(i).length() == 1) {
                if (result.startsWith(list.get(i)))
                    result += list.get(i);
                else
                    result = list.get(i) + result;
            }
            else if (!result.endsWith(String.valueOf(list.get(i).charAt(0))))
                result += list.get(i);
            else
                result = list.get(i) + result;
        }

        return result;
    }

    // 0ms 大佬
    public static String reorganizeString2(String S) {
        //把字符串S转化为字符数组
        char[] alphabetArr = S.toCharArray();
        //记录每个字符出现的次数
        int[] alphabetCount = new int[26];
        //字符串的长度
        int length = S.length();
        //统计每个字符出现的次数
        for (int i = 0; i < length; i++) {
            alphabetCount[alphabetArr[i] - 'a']++;
        }
        int max = 0, alphabet = 0, threshold = (length + 1) >> 1;
        //找出出现次数最多的那个字符
        for (int i = 0; i < alphabetCount.length; i++) {
            if (alphabetCount[i] > max) {
                max = alphabetCount[i];
                alphabet = i;
                //如果出现次数最多的那个字符的数量大于阈值，说明他不能使得
                // 两相邻的字符不同，直接返回空字符串即可
                if (max > threshold)
                    return "";
            }
        }
        //到这一步说明他可以使得两相邻的字符不同，我们随便返回一个结果，res就是返回
        //结果的数组形式，最后会再转化为字符串的
        char[] res = new char[length];
        int index = 0;
        //先把出现次数最多的字符存储在数组下标为偶数的位置上
        while (alphabetCount[alphabet]-- > 0) {
            res[index] = (char) (alphabet + 'a');
            index += 2;
        }
        System.out.println(Arrays.toString(res));
        //然后再把剩下的字符存储在其他位置上
        for (int i = 0; i < alphabetCount.length; i++) {
            while (alphabetCount[i]-- > 0) {
                if (index >= res.length) {
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
            }
        }
        return new String(res);
    }

    //官方的
    public static String reorganizeString3(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
            if (maxCount > (length + 1) / 2) {
                return "";
            }
        }
        //--------------基于最大堆的贪心算法----------------
        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> counts[o2 - 'a'] - counts[o1 - 'a']);
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }//[l, f, b, x]
        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
        /*
        //---------------基于计数的贪心算法-------------------
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
        */
    }
}
