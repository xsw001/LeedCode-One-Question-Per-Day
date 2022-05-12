package xsw.Nov_Dec;

public class UnionFind {
    private final int[] parent;

    public UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        parent[rootX] = rootY;
    }

    //「隔代压缩」性能比较高，虽然压缩不完全，不过多次执行「隔代压缩」也能达到「完全压缩」的效果
    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    //「完全压缩」需要借助系统栈，使用递归的写法。或者先找到当前结点的根结点，然后把沿途上所有的结点都指向根结点，得遍历两次。
    public int findRecur(int x) {
        if(x == parent[x])
            return x;
        else{
            parent[x] = find(parent[x]);  //父节点设为根节点
            return parent[x];         //返回父节点
        }
    }

}
