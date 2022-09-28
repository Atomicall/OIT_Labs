import java.util.Arrays;
import java.util.NoSuchElementException;

// non thread-safe
public class RingBufferImpl<T> implements RingBuffer<T> {

    private final T[] data;
    private final int capacity;
    private boolean isFull = false;
    private int pushPosition; // ++ when insert element
    private int popPosition; // -- when consume element

    public RingBufferImpl(int capacity) throws IllegalArgumentException {
        if (capacity < 1) throw new IllegalArgumentException();
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
    }

    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element to push is null");
        }
        if (!isFull()) {
            data[pushPosition++] = element;
            if (pushPosition == capacity) {
                pushPosition = 0;
            }
            if (pushPosition == popPosition) {
                isFull = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public T poll() throws NoSuchElementException {
        if (!isEmpty()) {
            T element = data[popPosition];
            data[popPosition++] = null;
            if (popPosition == capacity) {
                popPosition = 0;
            }
            isFull = false;
            return element;
        } else {
            throw new NoSuchElementException("Fifo is empty");
        }
    }

    @Override
    public int size() {
        int size;
        if (pushPosition < popPosition) {
            size = capacity - popPosition + pushPosition;
        } else if (pushPosition > popPosition) {
            size = pushPosition - popPosition;
        } else {
            size = isFull ? capacity : 0;
        }
        return size;

    }

    @Override
    public boolean isFull() {
        return size() >= capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    public T[] toArray() {
        return Arrays.copyOf(data, data.length);
    }
}
