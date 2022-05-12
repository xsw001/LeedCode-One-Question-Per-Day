package xsw.April.个人赛;
/*
5. 批量处理任务
通过的用户数21
尝试过的用户数80
用户总通过次数21
用户总提交次数230
题目难度Hard
某实验室计算机待处理任务以 [start,end,period] 格式记于二维数组 tasks，表示完成该任务的时间范围为起始时间 start 至结束时间 end 之间，需要计算机投入 period 的时长，注意：

period 可为不连续时间
首尾时间均包含在内
处于开机状态的计算机可同时处理任意多个任务，请返回电脑最少开机多久，可处理完所有任务。

示例 1：

输入：tasks = [[1,3,2],[2,5,3],[5,6,2]]

输出：4

解释：
tasks[0] 选择时间点 2、3；
tasks[1] 选择时间点 2、3、5；
tasks[2] 选择时间点 5、6；
因此计算机仅需在时间点 2、3、5、6 四个时刻保持开机即可完成任务。

示例 2：

输入：tasks = [[2,3,1],[5,5,1],[5,6,2]]

输出：3

解释：
tasks[0] 选择时间点 2 或 3；
tasks[1] 选择时间点 5；
tasks[2] 选择时间点 5、6；
因此计算机仅需在时间点 2、5、6 或 3、5、6 三个时刻保持开机即可完成任务。

提示：

2 <= tasks.length <= 10^5
tasks[i].length == 3
0 <= tasks[i][0] <= tasks[i][1] <= 10^9
1 <= tasks[i][2] <= tasks[i][1]-tasks[i][0] + 1
 */

import java.util.List;

public class hard_5 {
/*

    public int processTasks(int[][] tasks) {
        deque<pair<int,int>> st;
        deque<int> sum;
        sort(tasks.begin(),tasks.end(),[&](const vector<int> &a,const vector<int> &b){return a[0]>b[0];});
        sum.push_back(tasks[0][2]);
        st.push_back({tasks[0][0],tasks[0][0]+tasks[0][2]-1});
        sum.push_back(0);
        st.push_back({1e9+5,1e9+5-1});
        for(auto task:tasks){
            int l=0,r=st.size();
            while(l+1!=r){
                int mid=(l+r)/2;
                if(st[mid].first>task[1])r=mid;
                else l=mid;
            }
            int i=r;//lower_bound(st.begin(),st.end(),pair<int,int>{task[1]+1,0})-task.begin();
            if(i){
                --i;
                //if(i){
                task[2]-=sum[0]-sum[i]+max(0,min(task[1],st[i].second)-st[i].first+1);
                //}
            }
            // cout<<task[0]<<" "<<task[1]<<" "<<task[2]<<endl;
            while(task[2]>0){
                int d=st[0].first-task[0];
                if(task[2]<d){
                    st.push_front({task[0],task[0]+task[2]-1});
                    sum.push_front(sum[0]+task[2]);
                    break;
                }else{
                    task[2]+=st[0].second-st[0].first+1;
                    st.pop_front();
                    sum.pop_front();
                }
            }
        }
        return sum[0];
    }
*/

    public static void main(String[] args) {
        int[] data = {2, 2, 1, 9};
    }

}