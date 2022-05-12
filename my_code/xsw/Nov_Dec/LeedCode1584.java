package xsw.Nov_Dec;

/*
1584. 连接所有点的最小费用
给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。

请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
示例 1：

输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
输出：20
解释：

我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
注意到任意两个点之间只有唯一一条路径互相到达。
示例 2：

输入：points = [[3,12],[-2,5],[-4,1]]
输出：18
示例 3：

输入：points = [[0,0],[1,1],[1,0],[-1,1]]
输出：4
示例 4：

输入：points = [[-1000000,-1000000],[1000000,1000000]]
输出：4000000
示例 5：

输入：points = [[0,0]]
输出：0

提示：

1 <= points.length <= 1000
-106 <= xi, yi <= 106
所有点 (xi, yi) 两两不同。
*/


import java.util.*;

public class LeedCode1584 {

    //思路错误
    public static int minCostConnectPoint(int[][] points) {
        int path = 0, l = points.length;
        for (int i = 0; i < l - 1; i++) {
            int max = 10000;
            int index = i + 1;
            for (int j = index; j < l; j++) {
                int val = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                if (max >= val) {
                    max = val;
                    index = j;
                }
            }
            path += max;
            int[] temp = points[i + 1];
            points[i + 1] = points[index];
            points[index] = temp;
        }
        return path;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int i = minCostConnectPoints(points);
        System.out.println(i);

        int[] num = {-100,-98,-1,2,3,4};

        for (int j = 0; j < num.length; j++) {
            if(num[j] < 0)
                num[j] = -num[j];
        }
        System.out.println(Arrays.toString(num));
        Arrays.sort(num);
    }

    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++) {//将图（邻接矩阵或邻接表）转换成点-边式
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        edges.sort((edge1, edge2) -> edge1.len - edge2.len);//按边长度进行排序。
        int ret = 0, num = 1;
        for (Edge edge : edges) {//依次遍历所有的点-边式，取最小值。
            int len = edge.len, x = edge.x, y = edge.y;
            if (dsu.unionSet(x, y)) {//如果该点-边式的两个顶点不同源，则将这两个源（连通分量）合并
                ret += len;
                num++;
                if (num == n) {//直到存在一个连通分量，包含了所有的节点
                    break;
                }
            }
        }
        return ret;
    }

    public static int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }
}

class DisjointSetUnion {
    int[] parent;
    int[] rank;
    int n;

    public DisjointSetUnion(int n) {
        this.n = n;
        this.rank = new int[n];
        Arrays.fill(this.rank, 1);
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    public int find(int x) {//return x == fa[x] ? x : (fa[x] = find(fa[x]));代码常常简写为一行：
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean unionSet(int x, int y) {
        int fx = find(x), fy = find(y);
        if (fx == fy) {
            return false;
        }
        /*
            if (rank[x] <= rank[y])
                parent[x] = y;
            else
                parent[y] = x;
            if (rank[x] == rank[y])
                rank[y]++;                   //如果深度相同且根节点不同，则新的根节点的深度+1
        */
        if (rank[fx] < rank[fy]) {
            int temp = fx;
            fx = fy;
            fy = temp;
        }
        rank[fx] += rank[fy];
        parent[fy] = fx;
        return true;
    }
}

class Edge {
    int len, x, y;

    public Edge(int len, int x, int y) {
        this.len = len;
        this.x = x;
        this.y = y;
    }


}
