//
// @lc app=leetcode.cn id=817 lang=java
//
// [817] design-hashmap
//
class MyHashMap {
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Pair>();
        }
    }

    public void put(int key, int value) {
        int hash = hash(key);
        LinkedList<Pair> list = data[hash];
        for (Pair pair : list) {
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        list.add(new Pair(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = hash(key);
        LinkedList<Pair> list = data[hash];
        for (Pair pair : list) {
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return -1;
    }


    public void remove(int key) {
        int hash = hash(key);
        LinkedList<Pair> list = data[hash];
        for (Pair pair : list) {
            if (pair.getKey() == key) {
                list.remove(pair);
                return;
            }
        }
    }

    public int hash(int val) {
        return val % BASE;
    }
}

// @lc code=end