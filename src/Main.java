import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DynamicArray<Integer> array = new DynamicArrayImpl<>();
        System.out.println(array.isEmpty());
        for (int i = 1; i < 100; i++) {
            array.add(i);
        }
        System.out.println(array.size());
        System.out.println(array.isEmpty());
        System.out.println(array.get(0));
        array.remove(0);
        System.out.println(array.get(0));
        System.out.println(array.size());
        array.insert(124, 5);
        System.out.println(array.size());
    }
}

interface DynamicArray <T> {
    T get(int index);
    T remove (int index);
    void add(T t);
    boolean isEmpty();
    void insert(T t, int index);
    int size();
}

class DynamicArrayImpl<T> implements DynamicArray<T> {
    @Override
    public String toString() {
        return "DynamicArrayImpl{" + Arrays.toString(array) +
                '}';
    }

    private Object[] array;
    private int index;

    public DynamicArrayImpl() {
        array = new Object[10];
        index = -1;
    }

    public DynamicArrayImpl(int initialCapacity) {
        array = new Object[initialCapacity];
        index = -1;
    }

    @Override
    public T get(int index) {
        if(index > this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public T remove(int index) {
        T deleted = (T) array[index];
        for (int i = index; i < this.index; i++) {
            array[i] =array[i + 1];
        }
        this.index--;
        return deleted;
    }

    @Override
    public void add(T t) {
        if (index + 1 == array.length) {
            changeSize();
        }
        array[++index] = t;
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    @Override
    public void insert(T t, int index) {
        if(this.index +1 == array.length) {
             changeSize();
        }
        for (int i = this.index + 1; i > index; i--) {
            array[i] = array [i - 1];
        }
        array[index] = t;
        this.index++;
    }

    @Override
    public int size() {
        return index + 1;
    }

    private void changeSize() {
        int size = array.length;
        Object[] newArray = new Object[size + size / 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}