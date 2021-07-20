//
// @lc app=leetcode.cn id=869 lang=java
//
// [869] similar-string-groups
//
class Solution {
public static int numSimilarGroups(String[] strs) {
        int n = strs.length;
        Union union = new Union(n);
        HashMap<Integer, ArrayList<String>> similar = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add(strs[0]);
        similar.put(0, list);
        for (int i = 1; i < n; i++) {
            int flag = 0;
            String t = strs[i];
            for (Integer index : similar.keySet()) {
                ArrayList<String> strings = similar.get(index);
                for (String s : strings) {
                    if (numSimilar(t, s)) {
                        union.union(i, index);
                        strings.add(t);
                        similar.put(index,strings);
                        ++flag;
                        break;
                    }
                }

            }
            if (flag == 0) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(t);
                similar.put(i, temp);
            }
        }
        return union.size;
    }

    private static boolean numSimilar(String a, String b) {
        int len = a.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Union {
        int[] parent;
        int[] rank;
        int size;

        public Union(int n) {
            this.size = n;
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (x = find(parent[x]));
        }

        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb)
                return false;
            if (rank[pa] < rank[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }
            parent[pb] = pa;
            rank[pa]++;
            --size;
            return true;
        }
    }
}
// @lc code=end