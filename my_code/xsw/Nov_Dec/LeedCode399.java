package xsw.Nov_Dec;

/*
399. 除法求值
给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。

另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。



注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。



示例 1：

输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
解释：
条件：a / b = 2.0, b / c = 3.0
问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
示例 2：

输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
输出：[3.75000,0.40000,5.00000,0.20000]
示例 3：

输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
输出：[0.50000,2.00000,-1.00000,-1.00000]


提示：

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj 由小写英文字母与数字组成
*/

import java.util.*;

public class LeedCode399 {
    //没人能做到不仅时间比我慢，空间还比我大的了
    public static double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<List<String>, Double> map = new HashMap<List<String>, Double>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < equations.size(); i++) {
            map.put(equations.get(i), values[i]);
        }
        equations.sort((o1, o2) -> o1.get(0).charAt(0) - o2.get(0).charAt(0));
        for (int i = 0; i < equations.size(); i++) {
            for (int j = i + 1; j < equations.size(); j++) {
                if (equations.get(i).get(1).equals(equations.get(j).get(0))) {
                    List<String> strings = Arrays.asList(equations.get(i).get(0), equations.get(j).get(1));
                    double a = map.get(equations.get(i));
                    double b = map.get(equations.get(j));
                    map.put(strings, a * b);
                    if (!equations.contains(strings)) {
                        equations.add(i + 1, strings);
                        ++j;
                    }
                } else if (equations.get(i).get(1).equals(equations.get(j).get(1))) {
                    List<String> strings = Arrays.asList(equations.get(i).get(0), equations.get(j).get(0));
                    double a = map.get(equations.get(i));
                    double b = map.get(equations.get(j));
                    map.put(strings, a / b);
                    if (!equations.contains(strings)) {
                        equations.add(i + 1, strings);
                        ++j;
                    }
                } else if (equations.get(i).get(0).equals(equations.get(j).get(0))) {
                    List<String> strings = Arrays.asList(equations.get(i).get(1), equations.get(j).get(1));
                    double a = map.get(equations.get(i));
                    double b = map.get(equations.get(j));
                    map.put(strings, b / a);
                    if (!equations.contains(strings)) {
                        equations.add(j + 1, strings);
                        ++j;
                    }
                }
            }
            set.add(equations.get(i).get(0));
            set.add(equations.get(i).get(1));
        }
        System.out.println(equations);
        System.out.println(map);
        int size = queries.size();
        double[] result = new double[size];
        Arrays.fill(result, -1);
        for (int i = 0; i < size; i++) {
            List<String> query = queries.get(i);
            if (!set.contains(query.get(0)) || !set.contains(query.get(1)))
                continue;
            if (query.get(0).equals(query.get(1))) {
                result[i] = 1;
                continue;
            }
            if (equations.contains(query)) {
                result[i] = map.get(query);
                continue;
            }
            Collections.reverse(query);
            if (equations.contains(query)) {
                result[i] = 1 / map.get(query);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0,4.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("a", "b"));
        double[] doubles = calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(doubles));
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private static class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         *
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
