//
// @lc app=leetcode.cn id=488 lang=java
//
// [488] zuma-game
//
class Solution {
    public HashMap<String, Integer> cache;

    public int findMinStep(String board, String hand) {
        int[] hands = new int[5];
        cache = new HashMap<>();
        for (int i = 0; i < hand.length(); i++) hands[getId(hand.charAt(i))]++;
        return solve(board, hands);
    }

    public int solve(String board, int[] hands) {
        //消消乐
        board = xiaoxiaole(board);
        if (board.length() == 0) return 0;
        //用string方式固定状态放入缓存中
        String node = board + Arrays.toString(hands);
        if (cache.containsKey(node)) return cache.get(node);
        int res = -1;//默认找不到
        for (int i = 0; i < hands.length; i++) {
            //挑选hand，进行回溯
            if (hands[i] == 0) continue;
            hands[i]--;
            for (int j = 0; j < board.length(); j++) {
                //枚举当前球应该放的位置
                int ans = solve(board.substring(0, j) + getChar(i) + board.substring(j), hands);
                if (ans != -1) res = res == -1 ? ans + 1 : Math.min(res, ans + 1);   
            }
            hands[i]++;  //进行回溯
        }
        cache.put(node, res);
        return res;
    }

    private String xiaoxiaole(String board) {
        int s = 0;   
        int length = board.length();
        for (int i = 1; i <= board.length(); i++) {
            if (i == board.length() || board.charAt(i) != board.charAt(i - 1)) {  
                //只有字符不连续才会进入if 当前s下标是连续字符串的第一个，i下标是不连续字符串的第一个
                if (i - s >= 3) return xiaoxiaole(board.substring(0, s) + board.substring(i));
                else s = i;   //更新s下标
            }
        }
        return board;
    }
    //解析那种颜色球的下标
    private int getId(char c) {
        if (c == 'W') return 0;
        else if (c == 'B') return 1;
        else if (c == 'Y') return 2;
        else if (c == 'G') return 3;
        else return 4;
    }
    //获取球的颜色
    private char getChar(int id) {
        if (id == 0) return 'W';
        else if (id == 1) return 'B';
        else if (id == 2) return 'Y';
        else if (id == 3) return 'G';
        else return 'R';
    }
}

// @lc code=end