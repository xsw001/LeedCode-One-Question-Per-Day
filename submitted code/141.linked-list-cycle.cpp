//
// @lc app=leetcode.cn id=141 lang=cpp
//
// [141] linked-list-cycle
//
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     int flag;
 *     ListNode *next;
 *     ListNode(int x) : val(x),flag(0), next(NULL) {}
 * };
 */
class Solution {
public:
    bool hasCycle(ListNode *head) {
    if(head==NULL){
        return 0;
    }
    ListNode *p,*q;
	p=head;q=head;
	while(p->next!=NULL&&q->next!=NULL){
		if(p->next==q->next->next){
			return 1;
		}
		p=p->next;q=q->next->next;
        if(p==NULL||q==NULL){
            return 0;
        }
	}
	return 0;
}
};
// @lc code=end