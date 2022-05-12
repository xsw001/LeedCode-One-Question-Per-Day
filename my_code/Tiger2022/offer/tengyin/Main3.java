package Tiger2022.offer.tengyin;

public class Main3 {


    public static void main(String[] args) {
    }

    public static int minCnt(String s) {
        int length = s.length();
        int ans = 0;
        int i = 0;
        for (; i < length && s.charAt(i) == '0'; i++) {
            ++ans;
        }
        while (i < length){
            if(s.charAt(i) == '1')
                ++ans;
            ++i;
        }
        return ans - 1;
    }
}
