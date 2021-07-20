//
// @lc app=leetcode.cn id=206 lang=cpp
//
// [206] reverse-linked-list
//
class Solution {
public:
    ListNode* reverseList(ListNode* head) {
        ListNode *result = new ListNode(0);
        result->next = NULL;
        ListNode *p=head,*q=NULL;
        while(p != NULL){
            q = p->next;
            p->next = result->next;
            result->next = p;
            p=q;
        }
        return result->next;
    }
};
// @lc code=end