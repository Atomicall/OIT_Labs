public interface RingBuffer<T> {
    T pop();
    boolean push (T element);
    int capacity();
    int size();
    boolean isFull();
    boolean isEmpty();
    T[] toArray();
}