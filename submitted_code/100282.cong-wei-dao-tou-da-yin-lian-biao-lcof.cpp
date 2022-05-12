//
// @lc app=leetcode.cn id=100282 lang=cpp
//
// [100282] cong-wei-dao-tou-da-yin-lian-biao-lcof
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
    vector<int> reversePrint(ListNode* head) {
        vector<int> res;
        ListNode *cur = head;
        ListNode *pre = NULL,*last = NULL;
        while(cur != NULL){
            last = cur->next;
            cur->next = pre;
            pre = cur;
            cur = last;
        }
        while(pre != NULL){
            res.push_back(pre->val);
            pre = pre->next;
        }
        return res;
    }
};
// @lc code=end