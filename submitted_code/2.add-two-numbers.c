//
// @lc app=leetcode.cn id=2 lang=c
//
// [2] add-two-numbers
//
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
    struct ListNode *temp1 = l1;
    struct ListNode *temp2 = l2;
    int sum = 0;
    int flag = 0; // 进位标记
    struct ListNode *head = NULL;
    struct ListNode *tail = NULL; 
    struct ListNode *temp = NULL;

    if (!l1 && !l2) {
        return NULL;
    }

    while (temp1 || temp2 || flag) {
        temp = (struct ListNode*)malloc(sizeof(struct ListNode));
        temp->next = NULL; // 必须置NULL
        if (!head) {
            // 第一次保存链表头，用于返回
            head = temp; 
            tail = temp;
        } else {
            // 以后链表尾部后移
            tail->next = temp;
            tail = temp;
        }

        sum += flag; // 先加进位
        if (temp1) {
            sum += temp1->val;
            temp1 = temp1->next;
        }
        if (temp2) {
            sum += temp2->val;
            temp2 = temp2->next;
        }
        flag = sum / 10;
        sum = sum % 10;

        tail->val = sum;
        sum = 0; // 总和清0        
    }

    return head;    
}
// @lc code=end