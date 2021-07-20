//
// @lc app=leetcode.cn id=1442 lang=java
//
// [1442] number-of-operations-to-make-network-connected
//
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        Union union = new Union(n);
        for (int[] connection : connections) {
            union.union(connection[0], connection[1]);
        }
        return union.sizeN - 1;
    }
}
class Union {
    int[] parent;
    int[] rank;
    int sizeN;

    public Union(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.sizeN = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean union(int x, int y) {
        int pX = find(x), pY = find(y);
        if (pX == pY)
            return false;
        if (rank[pX] < rank[pY]) {
            int temp = pY;
            pY = pX;
            pX = temp;
        }
        parent[pY] = pX;
        ++rank[pX];
        --sizeN;
        return true;
    }
}

// @lc code=end