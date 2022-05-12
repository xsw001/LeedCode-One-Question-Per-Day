package xsw.April.团队赛;
/*
6. 守卫城堡
通过的用户数1
尝试过的用户数3
用户总通过次数1
用户总提交次数3
题目难度Hard
城堡守卫游戏的胜利条件为使恶魔无法从出生点到达城堡。游戏地图可视作 2*N 的方格图，记作字符串数组 grid，其中：

"." 表示恶魔可随意通行的平地；
"#" 表示恶魔不可通过的障碍物，玩家可通过在 平地 上设置障碍物，即将 "." 变为 "#" 以阻挡恶魔前进；
"S" 表示恶魔出生点，将有大量的恶魔该点生成，恶魔可向上/向下/向左/向右移动，且无法移动至地图外；
"P" 表示瞬移点，移动到 "P" 点的恶魔可被传送至任意一个 "P" 点，也可选择不传送；
"C" 表示城堡。
然而在游戏中用于建造障碍物的金钱是有限的，请返回玩家最少需要放置几个障碍物才能获得胜利。若无论怎样放置障碍物均无法获胜，请返回 -1。

注意：

地图上可能有一个或多个出生点
地图上有且只有一个城堡
示例 1

输入：grid = ["S.C.P#P.", ".....#.S"]

输出：3

解释：至少需要放置三个障碍物
image.png

示例 2：

输入：grid = ["SP#P..P#PC#.S", "..#P..P####.#"]

输出：-1

解释：无论怎样修筑障碍物，均无法阻挡最左侧出生的恶魔到达城堡位置
image.png

示例 3：

输入：grid = ["SP#.C.#PS", "P.#...#.P"]

输出：0

解释：无需放置障碍物即可获得胜利
image.png

示例 4：

输入：grid = ["CP.#.P.", "...S..S"]

输出：4

解释：至少需要放置 4 个障碍物，示意图为放置方法之一
image.png

提示：

grid.length == 2
2 <= grid[0].length == grid[1].length <= 10^4
grid[i][j] 仅包含字符 "."、"#"、"C"、"P"、"S"*/

public class hard_6 {

    public static int guardCastle(String[] grid) {
        return 0;
    }
/*
logo
学习
题库
讨论
竞赛
求职
商店
面试


排名	战队	得分
用时
题目 1 (2)	题目 2 (4)	题目 3 (6)	题目 4 (8)	题目 5 (9)	题目 6 (12)

zhouyuyang
panole
kczno1
inverted cross
41	0:55:06

0:04:33
C++

0:05:49
C++

0:15:55
C++

0:19:48
3
C++

0:24:03
2
C++

0:30:06
C++

sfiction
Avalon
JTJL
Nanohanasou
41	1:10:32

0:33:24
C++

1:01:44
C++

0:46:06
C++

1:04:33
1
C++

0:24:43
C++

1:05:32
C++

yfzcsc
OwenOwl
mcfx
小万邦
41	1:10:53

0:50:53
2
C++

0:44:54
C++

0:43:07
C++

0:24:19
1
C++

0:40:58
1
C++

0:29:15
C++
4
Shik Chen
wwwwodddd
dreamoon_love_AA
AA亲卫队
41	1:14:42

0:07:49
2
C++

0:29:35
1
C++

0:41:40
C++

0:59:42
C++

0:53:53
C++

0:30:11
C++
5
whzzt
sunset
Suspicious Colden
三个内鬼
41	1:25:47

0:06:56
2
C++

0:06:30
C++

0:48:56
1
C++

0:55:47
1
C++

0:49:53
2
C++

0:53:55
C++
6
jsb的爸爸
Restant
SmartLy
zyb 是我儿子
41	1:33:59

0:36:47
C++

0:09:34
C++

0:30:01
C++

0:16:27
C++

1:23:59
2
C++

0:58:05
C++
7
天塘
0.999999
41	1:35:47

0:05:45
Java

0:13:43
Java

0:27:30
Java

0:44:54
Java

1:01:47
Java

1:35:47
Java
8
ACRush
noname3
41	1:38:05

0:20:42
1
C++

0:16:14
C++

0:29:06
C++

0:43:06
1
C++

0:57:01
1
C++

1:13:05
2
C++
9
Milesian
liouzhou_101
吴自华
Steins; Gate
41	1:44:06

0:13:20
1
Python3

0:25:41
C++

0:21:28
C++

0:53:42
1
C++

1:24:06
2
C++

0:30:07
C++
10
猪脑子
Mys_C_K
蓥荣荥荧茔
如此队名，如何AC？
41	1:50:53

0:16:28
1
C++

0:32:59
C++

0:32:58
C++

0:26:24
C++

1:25:53
4
C++

1:06:48
C++
11
Orenji.Sora
Lweb
Herzu
Mapex
41	1:59:10

1:01:42
C++

1:12:38
1
C++

1:03:34
C++

1:34:10
2
C++

1:07:21
2
C++

0:49:22
C++
12
smzzl
fallleaves01
OMG_link
ddl战神
41	2:00:54

0:09:09
1
C++

0:18:50
C++

0:23:13
C++

0:42:55
C++

1:50:54
1
C++

1:10:56
C++
13
jasonvictoryan
aijmas
粤飞粤精彩
41	2:01:28

0:05:01
C++

0:13:05
C++

0:46:45
C++

1:41:28
3
C++

1:27:43
1
C++

0:55:18
C++
14
_____丶蒾纞
Night
wifiii
wifiii
41	2:10:01

0:33:11
1
C++

0:08:45
C++

0:24:16
C++

0:51:28
2
C++

1:10:55
1
C++

1:50:01
C++
15
F0_0H
RUSH_D_CAT
sdcgvhgj
( ￥ _ ￥ )
41	2:10:13

0:41:48
1
C++

1:20:53
1
C++

1:35:13
2
C++

1:06:33
C++

0:44:54
3
C++

1:00:16
C++
16
泛白
yukihana0416
emofunc
战队名称10086
41	2:13:19

0:13:36
C++

0:22:26
C++

0:18:07
C++

1:24:27
3
C++

1:08:07
C++

1:43:19
3
C++
17
Dudu
wssstc
EarringYYR
22/7
41	2:33:54

0:13:00
3
C++

0:28:27
C++

0:43:25
C++

1:26:23
4
C++

1:06:08
3
C++

1:43:54
C++
18
浮沉
-Captain-
Iscream
HDU2020_1
41	2:37:10

0:17:05
2
C++

0:34:22
1
C++

0:48:08
1
C++

1:11:43
C++

2:02:10
3
C++

1:20:52
C++
19
cretaceous℡
YangDavid
计算复杂性爱好者
渡渡鸟幼儿园
41	2:48:16

0:35:42
2
C++

0:46:37
C++

1:29:17
3
C++

0:35:07
C++

2:03:16
2
C++

1:15:54
2
C++
20
andcgt
KIriGiri
outer form
魔法闪闪少女
41	2:48:20

0:24:08
1
C++

0:15:37
C++

0:50:25
C++

1:13:50
3
C++

1:45:02
2
C++

2:03:20
3
C++
•••
力扣 LeetCode
竞赛
LeetBook
讨论社区
求职
Plus 会员
周边商城
企业服务
在线面试
企业测评
招聘
培训
解决方案
商务
社区合作
活动
赞助竞赛
产品推广
关于我们
价值观
工作机会
商务咨询问题反馈加入我们使用条款隐私政策© 2021 领扣网络（上海）有限公司
沪 ICP 备 17051546 号沪公网安备 31011502007040 号沪 ICP 证 B2-20180578人力资源服务许可证上海市互联网违法和不良信息举报中心中国互联网违法和不良信息举报中心
代码

class Solution {
public:
	struct node{int to,next,c;}e[1000010];
	int n,dis[50010],q[50010],l,r,hd[50010],cnt,cur[50010];
	void add(int x,int y,int c)
	{
		e[++cnt]=(node){y,hd[x],c},hd[x]=cnt;
		e[++cnt]=(node){x,hd[y],0},hd[y]=cnt;
	}
	bool bfs()
	{
		for (int i=2 ;i<=n; i++) dis[i]=-1;
		dis[1]=0,q[l=r=1]=1;
		while (l<=r)
		{
			int x=q[l++];
			for (int i=hd[x]; i; i=e[i].next)
				if (dis[e[i].to]==-1&&e[i].c) q[++r]=e[i].to,dis[e[i].to]=dis[x]+1;
		}
		return dis[n]!=-1;
	}
	int dfs(int x,int f)
	{
		if (x==n) return f;
		for (int &i=cur[x]; i; i=e[i].next)
			if (dis[e[i].to]==dis[x]+1&&e[i].c)
			{
				int nw=dfs(e[i].to,min(f,e[i].c));
				if (nw) return e[i].c-=nw,e[i^1].c+=nw,nw;
			}
		return 0;
	}
	long long solve()
	{
		long long ans=0,nw;
		while (bfs())
		{
			for (int i=1; i<=n; i++) cur[i]=hd[i];
			while (nw=dfs(1,1000000000)) ans+=nw;
		}
		return ans;
	}
    int guardCastle(vector<string>& grid) {
    	memset(hd,0,sizeof(hd)),cnt=1;
    	int m=grid[0].length();
    	n=4*m+3;
		for (int i=0; i<2; i++)
			for (int j=0; j<m; j++)
				if (grid[i][j]=='C') add(j*2+i+2,j*2+i+2+2*m,1000000000),add(j*2+i+2+2*m,4*m+3,1000000000); else
				if (grid[i][j]=='P') add(j*2+i+2,j*2+i+2+2*m,1000000000),add(j*2+i+2+2*m,4*m+2,1000000000),add(4*m+2,j*2+i+2,1000000000); else
				if (grid[i][j]=='.') add(j*2+i+2,j*2+i+2+2*m,1); else
				if (grid[i][j]=='S') add(1,j*2+i+2,1000000000),add(j*2+i+2,j*2+i+2+2*m,1000000000);
			for (int j=0; j<m; j++)
			{
				add(j*2+1+2+2*m,j*2+2,1000000000);
				add(j*2+2+2*m,j*2+1+2,1000000000);
			}
			for (int j=1; j<m; j++)
			{
				add(j*2+1+2+2*m,(j-1)*2+1+2,1000000000);
				add((j-1)*2+1+2+2*m,j*2+1+2,1000000000);
				add(j*2+2+2*m,(j-1)*2+2,1000000000);
				add((j-1)*2+2+2*m,j*2+2,1000000000);
			}
		long long ans=solve();
		if (ans>=1000000000) return -1; else return ans;
    }
};
*/

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};

    }

}