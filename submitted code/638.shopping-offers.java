//
// @lc app=leetcode.cn id=638 lang=java
//
// [638] shopping-offers
//
    class Solution {

        Map<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();

        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            int n = needs.size();
            List<List<Integer>> filterSpecial = new ArrayList<>();
            for (List<Integer> list : special) {
                int total = 0, sum = 0;
                for (int i = 0; i < n; i++) {
                    total += list.get(i);
                    sum += list.get(i) * price.get(i);
                }
                if (total > 0 && sum > list.get(n))
                    filterSpecial.add(list);
            }
            return dfs(price, needs, filterSpecial, n);
        }

        // 记忆化搜索计算满足购物清单所需花费的最低价格
        public int dfs(List<Integer> price, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
            if (!map.containsKey(curNeeds)) {
                int min = 0;
                for (int i = 0; i < curNeeds.size(); i++) {
                    min += curNeeds.get(i) * price.get(i);
                }
                for (List<Integer> special : filterSpecial) {
                    int spPri = special.get(n);
                    ArrayList<Integer> next = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        if (special.get(i) > curNeeds.get(i))
                            break;
                        next.add(curNeeds.get(i) - special.get(i));
                    }
                    if (next.size() == n) {
                        int nextPri = dfs(price, next, filterSpecial, n);
                        int curPri = nextPri + spPri;
                        min = Math.min(min, curPri);
                    }
                }
                map.put(curNeeds, min);
            }
            return map.get(curNeeds);
        }
    }
// @lc code=end