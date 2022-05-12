//
// @lc app=leetcode.cn id=947 lang=java
//
// [947] online-election
//
    class TopVotedCandidate {

        int[] persons, times;
        int[] count;
        int[] temp;

        public TopVotedCandidate(int[] _persons, int[] _times) {
            int l = _persons.length;
            persons = _persons;
            times = _times;
            count = new int[l];
            temp = new int[l];

            int max = 0;
            for (int i = 0; i < persons.length; i++) {
                int person = persons[i];
                temp[person]++;
                if(temp[person] >= max){
                    max = temp[person];
                    count[i] = person;
                }else
                    count[i] = count[i-1];
            }

        }

        public int q(int t) {
            int l = 0, r = times.length;
            while (l < r) {
                int m = (l + r) / 2;
                if (times[m] <= t)
                    l = m + 1;
                else
                    r = m;
            }
            return count[l-1];
        }
    }

/**
* Your TopVotedCandidate object will be instantiated and called as such:
* TopVotedCandidate obj = new TopVotedCandidate(persons, times);
* int param_1 = obj.q(t);
*/
// @lc code=end