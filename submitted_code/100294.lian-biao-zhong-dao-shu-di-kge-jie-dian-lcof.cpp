//
// @lc app=leetcode.cn id=100294 lang=cpp
//
// [100294] lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
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
    ListNode* getKthFromEnd(ListNode* head, int k) {
        int i=0;
        ListNode *p = head;
        while(p && i<k){
            p=p->next;
            i++;
        }
        while(p!=NULL){
            head = head->next;
            p=p->next;
        }
        return head;
    }
};
// @lc code=end