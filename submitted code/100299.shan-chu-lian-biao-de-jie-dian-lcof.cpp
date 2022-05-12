//
// @lc app=leetcode.cn id=100299 lang=cpp
//
// [100299] shan-chu-lian-biao-de-jie-dian-lcof
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
    ListNode* deleteNode(ListNode* head, int val) {
        if(!head)
            return NULL;
        ListNode *pre = head,*p = pre->next;
        if(pre->val == val)
            return p;
        while(p){
            if(p->val == val){
                pre->next = p->next;
                break;
            }
            pre = p;
            p = p->next;
        }
        return head;
    }
};
// @lc code=end