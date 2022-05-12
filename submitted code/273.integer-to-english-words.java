//
// @lc app=leetcode.cn id=273 lang=java
//
// [273] integer-to-english-words
//
class Solution {

    static String[] dan = {"Hundred", "Thousand", "Million", "Billion"};
    static String[] less20 = {"Zero","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] multiple10 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String numberToWords(int num) {        
        if(num == 0)
            return "Zero";
        ArrayList<Integer> list = new ArrayList<>();
        int t = 1000;
        while (num > 0) {
            list.add(num % t);
            num /= t;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer number = list.get(i);
            String str = transform(number);
            if(str.equals(""))
                continue;
            sb.append(str).append(" ");
            if(i > 0)
                sb.append(dan[i]).append(" ");
        }
        return sb.toString().trim();
    }

    private static String transform(int num) {
        StringBuilder sb = new StringBuilder();
        int l = num / 100;
        num %= 100;
        if (l != 0)
            sb.append(less20[l]).append(" ").append(dan[0]).append(" ");
        if(num == 0)
            return sb.toString().trim();
        if(num < 20)
            sb.append(less20[num]);
        else if (num % 10 == 0)
            sb.append(multiple10[num / 10 - 2]);
        else
            sb.append(multiple10[num / 10 - 2]).append(" ").append(less20[num % 10]);
        return sb.toString().trim();
    }
}
// @lc code=end