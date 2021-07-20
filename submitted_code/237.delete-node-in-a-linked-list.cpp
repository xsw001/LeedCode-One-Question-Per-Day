//
// @lc app=leetcode.cn id=237 lang=cpp
//
// [237] delete-node-in-a-linked-list
//
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void deleteNode(ListNode* node) {
        ListNode *he = node->next;
        node->val = he->val;
        node->next = he->next;
        delete(he);
    }
};
// @lc code=end