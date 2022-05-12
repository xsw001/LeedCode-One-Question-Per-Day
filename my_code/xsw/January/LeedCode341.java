package xsw.January;
/*

 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeedCode341 {


    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
    }

}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a Single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the Single integer that this NestedInteger holds, if it holds a Single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a Single integer
 *     public List<NestedInteger> getList();
 * }
 */
interface NestedInteger {
    // @return true if this NestedInteger holds a Single integer, rather than a nested list.
    public boolean isInteger();
    // @return the Single integer that this NestedInteger holds, if it holds a Single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a Single integer
    public List<NestedInteger> getList();
}
//执行用时：//3 ms//, 在所有 Java 提交中击败了//91.05%//的用户
class NestedIterator implements Iterator<Integer> {
    ArrayList<Integer> list = new ArrayList<>();
    int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        help(nestedList);
    }
    public void help(List<NestedInteger> nestedList){
        for (NestedInteger i : nestedList) {
            if(i.isInteger())
                list.add(i.getInteger());
            else{
                List<NestedInteger> list = i.getList();
                help(list);
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */