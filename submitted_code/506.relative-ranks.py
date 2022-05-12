#
# @lc app=leetcode.cn id=506 lang=python3
#
# [506] relative-ranks
#
class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        n = len(score)
        ans = []
        score_copy = score.copy()
        score_map = {}
        score.sort(reverse=True)
        for i in range(n):
            score_map[score[i]] = str(i+1)
        for i in range(n):
            ans.append(score_map[score_copy[i]])
            if ans[i] == '1':
                ans[i] = 'Gold Medal'
            if ans[i] == '2':
                ans[i] = 'Silver Medal'
            if ans[i] == '3':
                ans[i] = 'Bronze Medal'
        return ans
# @lc code=end