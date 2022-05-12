package Tiger2022.offer.tengyin;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main2 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(2);
        list.add(1);
        System.out.println(minMax(list, 3, 2));
    }

    public static int minMax(ArrayList<Integer> a, int k, int x) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queue.addAll(a);
        for (int i = 0; i < k; i++) {
            queue.add(queue.poll() - x);
        }
        return queue.peek();
    }
}
