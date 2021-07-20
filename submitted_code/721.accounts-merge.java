//
// @lc app=leetcode.cn id=721 lang=java
//
// [721] accounts-merge
//
class Solution {
public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToId = new HashMap<String, Integer>();
        int n = accounts.size();
        UnionFind myUnion = new UnionFind(n);
        for(int i=0; i < n; ++i){
            int num = accounts.get(i).size();
            for(int j = 1 ; j< num;++j){
                String curEmail = accounts.get(i).get(j);
                if(!emailToId.containsKey(curEmail)){
                    emailToId.put(curEmail,i);
                }else{
                    myUnion.union(i,emailToId.get(curEmail));
                }
            }
        }
        Map<Integer, List<String>> idToEmails = new HashMap<>();
        for (String s : emailToId.keySet()) {
            int id = myUnion.find(emailToId.get(s));
            List<String> list = idToEmails.getOrDefault(id, new ArrayList<String>());
            list.add(s);
            idToEmails.put(id,list);
        }
        List<List<String>> res = new ArrayList<>();
        for (Integer id : idToEmails.keySet()) {
            List<String> emails = idToEmails.get(id);
            Collections.sort(emails);
            List<String> tmp = new ArrayList<>();
            tmp.add(accounts.get(id).get(0));
            tmp.addAll(emails);
            res.add(tmp);
        }
        return res;
    }
    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }

    }
}
// @lc code=end