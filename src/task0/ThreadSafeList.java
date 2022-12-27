package task0;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<E> {

    private E[] arr;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public ThreadSafeList() {
        this.arr = (E[]) new Object[DEFAULT_CAPACITY];
    }

    boolean add(E e) {
        writeLock.lock();
        try {
            if (size == arr.length) {
                E[] oldArray = arr;
                E[] newArray = (E[]) new Object[(int) (oldArray.length * 1.5)];
                System.arraycopy(oldArray, 0, newArray, 0, arr.length);
                arr = newArray;
            }
            arr[size] = e;
            size++;
            return true;
        } catch (Exception exception) {
            return false;
        } finally {
            writeLock.unlock();
        }
    }

    boolean remove(int index) {
        writeLock.lock();
        try {
            E[] oldArray = arr;
            E[] newArray = (E[]) new Object[oldArray.length - 1];
            System.arraycopy(oldArray, 0, newArray, 0, index);
            System.arraycopy(oldArray, index + 1, newArray, index, newArray.length - index);
            arr = newArray;
            size--;
            return true;
        } catch (Exception exception) {
            return false;
        } finally {
            writeLock.unlock();
        }
    }

    E get(int index) {
        readLock.lock();
        try {
            if (index <= size) {
                return arr[index];
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }
}
