import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JavaAlgorithmProblems {
    /*test*/ public boolean foo() {
        return true;
    }

    /*test*/ public int bar() {
        return 5;
    }

    /*test*/ public String abc() {
        return "hello";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Hi");
        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        Collections.nCopies(5, 0);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Object> future = executorService.submit(new Callable<>(){
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get());
        executorService.shutdown();
        MyQueueUsingArray<Integer> queue = new MyQueueUsingArray<>(Integer[].class, 4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.printQueue();
        queue.add(5);
        queue.printQueue();
    }
}

class MyStack<T extends Comparable<T>> {
    LinkedList<T> entries;
    MyStack<T> maxEntries;

    public MyStack() {
        entries = new LinkedList<>();
        maxEntries = null;
    }

    public void push(T entry) {
        entries.addFirst(entry);
        if (maxEntries == null) {
            maxEntries = new MyStack<>();
        }

        T e1 = maxEntries.entries.getFirst();
        T e2 = entry;
        if (e1.compareTo(e2) < 0) {
            maxEntries.entries.addFirst(entry);
        }
    }

    public T pop() {
        // poll returns null if the list is empty
        // If the stack is empty, then return
        if (entries.size() == 0 || maxEntries.entries.size() == 0) {
            return null;
        }

        //if (entries.getFirst() == maxEntries.top()) {
        if (this.top() == maxEntries.top()) {
            maxEntries.entries.pollFirst();
        }
        return entries.pollFirst();
    }

    public T top() {
        return entries.getFirst();
    }

    public T getMax() {
        return maxEntries.top();
    }

    public void printStack() {
        for (int i = 0; i < entries.size(); i++) {
            System.out.print(entries.get(i) + " ");
        }
        System.out.println();
    }
}

class DoubleKey<K extends Comparable<K>> implements Comparable<DoubleKey<K>> {

    private K key1;

    public DoubleKey(K key1) {
        this.key1 = key1;
    }

    public K getFirstKey() {
        return this.key1;
    }


    @Override
    public int compareTo(DoubleKey<K> that) {

        int cmp = this.getFirstKey().compareTo(that.getFirstKey());
        return cmp;
    }
}

class MyQueueUsingArray<T> {
    int capacity;
    T[] queue;
    int headIndex = 0;
    int tailIndex = 0;
    int currentSize = 0;

    @SuppressWarnings("unchecked")
    public MyQueueUsingArray(Class<T[]> clazz, int capacity) {
        this.capacity = capacity;
        queue = (T[])Array.newInstance(clazz, capacity);
    }

    public void add(T data) {
        if (currentSize == capacity) {
            System.out.println("Reached capacity");
            return;
        }
        queue[tailIndex++] = data;
        currentSize++;
        if (tailIndex == capacity) {
            tailIndex = 0;
        }
    }

    public T remove() {
        if (currentSize == 0) {
            System.out.println("Empty queue");
            return null;
        }
        T tmp = queue[headIndex];
        currentSize--;
        headIndex++;
        if (headIndex == capacity) {
            headIndex++;
        }
        return tmp;
    }

    public void printQueue() {
        for (int i = headIndex; i < capacity; i++) {
            System.out.print(queue[headIndex]);
        }
        if (headIndex != 0) {
            for (int i = 0; i <= tailIndex; i++) {
                System.out.print(queue[i]);
            }
        }
        System.out.println();
    }
}

