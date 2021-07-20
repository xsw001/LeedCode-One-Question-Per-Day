//
// @lc app=leetcode.cn id=1282 lang=java
//
// [1282] number-of-valid-words-for-each-puzzle
//
class Solution {
    /**
     * 虽然用时[2238ms]挺久的，但还算是过了
     * 总体思路：
     * 1. 对每个单词进行按字母位置转换 如：bit(abc) = (1 << 1) + (1 << 2) + (1 << 3)
     * 2. 对单词按照1的方式去重处理，对谜面也按照1的方式去处理，并将谜面的首位也按照1的方式进行处理
     * 3. 进行位运算处理 当满足两个条件即可计数
     *     a. (bit(puzzle) & bit(word)) == bit(word)
     *     b. (bit(word) & bit(first(puzzle))) == bit(first(puzzle))
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int puzzlesLen = puzzles.length;
        int wordsLen = words.length;

        // 返回数组
        Integer[] resArray = new Integer[puzzlesLen];
        // 单词的bit存储
        int[] wBit = new int[wordsLen];

        for (int i = 0; i < puzzlesLen; i++) {
            // 计数初始化
            if(resArray[i] == null) resArray[i] = 0;
            // 获取谜面的首字母的bit -> bit(first(bit))
            int firstBit = 1 << (puzzles[i].charAt(0) - 'a');
            // 获取谜面的bit -> bit(puzzle)
            int puzzleBit = word2Bit(puzzles[i], false);
            // 进行计数统计
            for (int j = 0; j < wordsLen; j++) {
                // 对word进行bit处理 -> bit(word)
                if(wBit[j] == 0) wBit[j] = word2Bit(words[j], true);
                // 进行条件处理
                if ((puzzleBit & wBit[j]) == wBit[j] && (wBit[j] & firstBit) == firstBit) {
                    // 满足条件则计数
                    resArray[i]++;
                }
            }
        }
        // 返回对应的内容
        return Arrays.asList(resArray);
    }

    /**
     * 单词bit处理
     */
    public int word2Bit(String word, boolean distinct) {
        int i = 0;
        for (char s : word.toCharArray()) {
            int b = s - 'a';
            if(!distinct){
                i += 1 << b;
            }else{
                if((i >> b) % 2 == 0) i += 1 << b;
            }
        }
        return i;
    }
}
// @lc code=end