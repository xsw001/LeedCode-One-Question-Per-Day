//
// @lc app=leetcode.cn id=100274 lang=cpp
//
// [100274] fei-bo-na-qi-shu-lie-lcof
//
#define N 10 + 1
class Solution {
private:
    std::string author = "zouwxcs@@leetcode-cn" ;
    // 开辟4log(n)的空间
    int arr[N<<2] = {
                    1, 0, 0, 1,   // A^0 = E
                    1, 1, 1, 0} ; // A^1 = A
    // 二阶矩阵乘法
    void mult(int *m1, int *m2, int *product) {
        long long tmp[4] ;
        tmp[0] = m1[0]*(long long)m2[0] + m1[1]*(long long)m2[2] ;
        tmp[1] = m1[0]*(long long)m2[1] + m1[1]*(long long)m2[3] ;
        tmp[2] = m1[2]*(long long)m2[0] + m1[3]*(long long)m2[2] ;
        tmp[3] = m1[2]*(long long)m2[1] + m1[3]*(long long)m2[3] ;
        product[0] = tmp[0]%1000000007 ;
        product[1] = tmp[1]%1000000007 ;
        product[2] = tmp[2]%1000000007 ;
        product[3] = tmp[3]%1000000007 ;
    }
public:
    Solution() {
        // 构造函数，先把A^2, ..., A^N 都求出来
        for (int i = 2; i < N; ++i)
            mult(&arr[(i-1)<<2], &arr[(i-1)<<2], &arr[i<<2]) ;
    }
    int fib(int n) {
        if (n <= 2)
            return n? 1: 0 ;
        int A[4] = {1, 0, 0, 1} ; // E
        n -= 2 ;
        for (int i = 1; n; ++i, n >>= 1) {
            if (n&1)
                mult(A, &arr[i<<2], A) ;
        }
        return (A[0] + A[1])%1000000007 ;
    }
};
// @lc code=end