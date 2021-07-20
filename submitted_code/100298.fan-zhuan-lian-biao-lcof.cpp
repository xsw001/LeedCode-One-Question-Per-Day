//
// @lc app=leetcode.cn id=100298 lang=cpp
//
// [100298] fan-zhuan-lian-biao-lcof
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
    ListNode* reverseList(ListNode* head) {
        if(!head)
            return NULL;
        ListNode *p = NULL;
        ListNode *he = head;
        ListNode *ne = he->next;
        while(ne){
            he->next = p;
            p = he;
            he = ne;
            ne = ne->next;
        }
        he->next = p;
        return he;
    }
};
// @lc code=end