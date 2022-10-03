//1.3 Творческие задания, 1.3.39 - Кольцевой буфер
// При переполнении перезапись не производится

import java.util.Arrays;

public class Main {
    private final static int capacity = 5;

    public static void main(String[] args) {
        RingBuffer<Integer> b = new RingBufferImpl<>(capacity);
        for (int i = 0; i < capacity + 1; i++) {
            if (!b.add(i)) {
                System.out.printf("Failed to add: %d, buffer is full\nCapacity is %d, buffer size is %d \n", i, capacity, b.size());
                System.out.println("b.isFull(): " + b.isFull());
            }
        }
        try {
            System.out.println(Arrays.toString(b.toArray()));
            System.out.println("Poll:" + b.poll());
            System.out.println("Poll:" + b.poll());
            System.out.println(Arrays.toString(b.toArray()));
            System.out.println("Poll:" + b.poll());
            System.out.println("Poll:" + b.poll());
            System.out.println(Arrays.toString(b.toArray()));
            System.out.println("Add: 10: " + b.add(10));
            System.out.println("Add: 20: " + b.add(20));
            System.out.println(Arrays.toString(b.toArray()));
            System.out.println("Poll:" + b.poll());
            System.out.println("Poll:" + b.poll());
            System.out.println("Poll:" + b.poll());
            System.out.println(Arrays.toString(b.toArray()));
            System.out.println("b.isEmpty(): " + b.isEmpty());
            System.out.println("Trying to Poll from empty fifo:");
            System.out.println(b.poll());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (b.add(i)) {
                b.poll();
            }
        }
        System.out.println("Int32 test done");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (b.add(i)) {
                b.poll();
            }
        }
        System.out.println("Int32 test done");
    }
}
