public interface RingBuffer<T> {
    T poll();

    boolean add(T element);

    int capacity();

    int size();

    boolean isFull();

    boolean isEmpty();

    T[] toArray();
}