//
// @lc app=leetcode.cn id=1411 lang=cpp
//
// [1411] convert-binary-number-in-a-linked-list-to-integer
//
class Solution {
public:
    int getDecimalValue(ListNode* head) {
        ListNode* cur = head;
        int ans = 0;
        while (cur != nullptr) {
            ans = ans * 2 + cur->val;
            cur = cur->next;
        }
        return ans;
    }
};
// @lc code=end