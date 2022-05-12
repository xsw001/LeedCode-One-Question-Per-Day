package Tiger2022.offer.Tesla;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        int a = 156465465;
        int b = 465797998;
        System.out.println(solution(a, b));
        System.out.println(Long.bitCount(a * b));
    }

    public static int solution(int A, int B) {
        BigDecimal a = new BigDecimal(A);
        BigDecimal b = new BigDecimal(B);
        BigDecimal zero = new BigDecimal(0);
        BigDecimal two = new BigDecimal(2);
        BigDecimal m = a.multiply(b);
        int count = 0;
        while (m.compareTo(zero) != 0) {
            BigDecimal[] bigDecimals = m.divideAndRemainder(two);
            if (bigDecimals[1].intValue() == 1) {
                ++count;
            }
            m = bigDecimals[0];
        }
        return count;
    }
}
