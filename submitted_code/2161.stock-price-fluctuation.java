//
// @lc app=leetcode.cn id=2161 lang=java
//
// [2161] stock-price-fluctuation
//
class StockPrice {
int maxTimestamp;
        HashMap<Integer, Integer> timePriceMap;
        PriorityQueue<int[]> pqMax;
        PriorityQueue<int[]> pqMin;

        public StockPrice() {
            maxTimestamp = 0;
            timePriceMap = new HashMap<Integer, Integer>();
            pqMax = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);
            pqMin = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(maxTimestamp, timestamp);
            timePriceMap.put(timestamp, price);
            pqMax.offer(new int[]{price, timestamp});
            pqMin.offer(new int[]{price, timestamp});
        }

        public int current() {
            return timePriceMap.get(maxTimestamp);
        }

        public int maximum() {
            while (true) {
                int[] priceTime = pqMax.peek();
                int price = priceTime[0], timestamp = priceTime[1];
                if (timePriceMap.get(timestamp) == price) {
                    return price;
                }
                pqMax.poll();
            }
        }

        public int minimum() {
            while (true) {
                int[] priceTime = pqMin.peek();
                int price = priceTime[0], timestamp = priceTime[1];
                if (timePriceMap.get(timestamp) == price) {
                    return price;
                }
                pqMin.poll();
            }
    }
}

// @lc code=end