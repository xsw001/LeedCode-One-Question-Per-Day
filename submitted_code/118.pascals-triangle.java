//
// @lc app=leetcode.cn id=118 lang=java
//
// [118] pascals-triangle
//
class Solution {
    public List<List<Integer>> generate(int numRows) {        
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) return res;
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(new ArrayList(firstRow));
        int size = res.size();
        while(size < numRows){
            LinkedList<Integer> first = new LinkedList<>();
            first.addFirst(0);
            LinkedList<Integer> second = new LinkedList<>();
            second.addLast(0);
            for(int x: res.get(size-1)){
                first.addFirst(x);
                second.addLast(x);
            }
            List<Integer> newRow = new ArrayList<>();
            for(int i=0; i<first.size(); i++){
                newRow.add(first.get(i) + second.get(i));
            }
            res.add(newRow);
            size++;
        }
        return res;
    }
}
// @lc code=end