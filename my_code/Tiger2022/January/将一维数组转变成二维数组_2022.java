package Tiger2022.January;
/*

 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class 将一维数组转变成二维数组_2022 {

    public int[][] construct2DArray(int[] original, int m, int n) {
        int[][] ans = new int[m][n];
        if(original.length != m*n)
            return new int[0][];
        int a = 0, b = 0;
        for(int i : original){
            ans[a][b++] = i;
            if(b == n){
                b = 0;
                ++a;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(Arrays.deepToString(construct2DArray(data, 1, 3)));
    }

}