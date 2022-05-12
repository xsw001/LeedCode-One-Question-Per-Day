//
// @lc app=leetcode.cn id=690 lang=java
//
// [690] employee-importance
//
/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<Integer, Employee>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        int total = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(id);
        while (!queue.isEmpty()) {
            int curId = queue.poll();
            Employee employee = map.get(curId);
            total += employee.importance;
            List<Integer> subordinates = employee.subordinates;
            for (int subId : subordinates) {
                queue.offer(subId);
            }
        }
        return total;
    }
}
// @lc code=end