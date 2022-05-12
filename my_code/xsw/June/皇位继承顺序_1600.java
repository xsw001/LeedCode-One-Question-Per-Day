package xsw.June;
/*

 */

import java.lang.reflect.Array;
import java.util.*;

public class 皇位继承顺序_1600 {

    static class ThroneInheritance123 {

        List<String> param = new ArrayList<String>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        Map<String, String> father = new HashMap<>();

        public ThroneInheritance123(String kingName) {
            param.add(kingName);
            map.put(kingName, new ArrayList<>());
        }

        public void birth(String parentName, String childName) {
            ArrayList<String> children = map.get(parentName);
            if (children != null && children.size() != 0) {
                String lastChild = children.get(children.size() - 1);
                while (children != null && children.size() != 0) {
                    children = map.get(children.get(children.size() - 1));
                    if (children != null && children.size() != 0)
                        lastChild = children.get(children.size() - 1);
                }
                int index = param.indexOf(lastChild);
                param.add(index + 1, childName);
            } else {
                int index = param.indexOf(parentName);
                param.add(index + 1, childName);
            }
            children = map.get(parentName);
            children.add(childName);
            map.put(parentName, children);
            map.put(childName, new ArrayList<>());
            father.put(childName, parentName);
        }

        public void death(String name) {
            param.remove(name);
            String f = father.get(name);
            ArrayList<String> list = map.get(f);
            if (list != null)
                list.remove(name);
            map.put(f, list);
        }

        public List<String> getInheritanceOrder() {
            return param;
        }
    }

    public static void main(String[] args) {
        ThroneInheritance obj = new ThroneInheritance("king");
        System.out.println(obj.getInheritanceOrder());
        obj.birth("king", "clyde");
        obj.birth("clyde", "shannon");
        obj.birth("shannon", "scott");
        obj.birth("king", "keith");
        System.out.println(obj.getInheritanceOrder());
        obj.birth("clyde", "joseph");
        System.out.println(obj.getInheritanceOrder());
    }

    static class ThroneInheritance {

        String kingName;

        Map<String, List<String>> map = new HashMap<>();

        Set<String> deathSet = new HashSet<>();

        public ThroneInheritance(String kingName) {
            this.kingName = kingName;
            map.put(kingName, new LinkedList<>());
        }

        public void birth(String parentName, String childName) {
            map.get(parentName).add(childName);
            map.put(childName, new LinkedList<>());
        }

        public void death(String name) {
            deathSet.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> result = new LinkedList<>();
            dfs(result, kingName);
            return result;
        }

        private void dfs(List<String> result, String name) {
            if (!deathSet.contains(name)) {
                result.add(name);
            }
            for (String subName : map.get(name)) {
                dfs(result, subName);
            }
        }
    }

}