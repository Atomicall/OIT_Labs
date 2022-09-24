import java.util.Arrays;
public class RingBufferImpl<T> implements RingBuffer<T>{
// non thread-safe
    private final int capacity;
    private final T[] data;

    private int pushPosition; // ++ when insert element
    private int popPosition; // -- when consume element
    public RingBufferImpl(int capacity) {
        if (capacity < 1 ) throw new IllegalArgumentException();
        this.capacity = capacity;
        this.data = (T[])new Object[capacity];
        this.pushPosition = -1;
        this.popPosition = 0;
    }

    @Override
    public boolean push(T element) {
        if (!isFull()){
            data[++pushPosition % capacity] = element;
            return true;
        }
        return false;

    }
    @Override
    public T pop() {
        if (!isEmpty()){
            T element = data[popPosition % capacity];
            data[popPosition++ % capacity] = null;
            return element;
        }
        else return null;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return pushPosition - popPosition + 1;
    }

    @Override
    public boolean isFull() {
        return size()>= capacity;
    }

    @Override
    public boolean isEmpty() {
        return pushPosition < popPosition;
    }

    public T[] toArray(){
        return Arrays.copyOf( data, data.length);
    }

}
