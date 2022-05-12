#
# @lc app=leetcode.cn id=383 lang=python3
#
# [383] ransom-note
#
class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        for i in 'qwertyuiopasdfghjklzxcvbnm':
            if ransomNote.count(i) > magazine.count(i):
                return False
        return True
# @lc code=end