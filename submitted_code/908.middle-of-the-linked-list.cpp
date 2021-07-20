//
// @lc app=leetcode.cn id=908 lang=cpp
//
// [908] middle-of-the-linked-list
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
    ListNode* middleNode(ListNode* head) {
        int len = 0;
        ListNode *p = head;
        while(p != NULL){
            ++len;
            p = p->next;
        }
        p = head;
        for(int i=0;i<len/2;++i){
            p=p->next;
        }
        return p;
    }
};
// @lc code=end