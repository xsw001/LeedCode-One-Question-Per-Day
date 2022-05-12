//
// @lc app=leetcode.cn id=412 lang=java
//
// [412] fizz-buzz
//
class Solution {
    public List<String> fizzBuzz(int n) {
        return IntStream.range(1, n + 1).mapToObj(a -> a % 15 == 0 ? "FizzBuzz" : a % 3 == 0 ? "Fizz" : a % 5 == 0 ? "Buzz" : String.valueOf(a)).collect(Collectors.toList());
    }
}

// @lc code=end