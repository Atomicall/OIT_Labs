//1.3 Творческие задания, 1.3.39 - Кольцевой буфер
// При переполнении перезапись не производится
import java.util.Arrays;

public class main {
    private final static int capacity = 5;
    public static void main(String[] args) {
        RingBuffer<Integer> b = new RingBufferImpl<>(capacity);
        for (int i = 0; i < capacity+1; i++) {
            if (!b.push(i)){
                System.out.printf("Failed to push:%d, buffer is full, capacity is %d\n",
                        i, capacity);
            };
        }
        System.out.println(Arrays.toString(b.toArray()));
        System.out.println("Pop:" + b.pop());
        System.out.println("Pop:" + b.pop());
        System.out.println(Arrays.toString(b.toArray()));
        b.push(capacity +1);
        b.push(capacity + 2);
        System.out.println(Arrays.toString(b.toArray()));
    }
}
