import java.util.*;
import java.lang.reflect.Array;

// -----------------------------------------------------------------------------
// Problem 1. LRC Cache
// -----------------------------------------------------------------------------
class LRUCache {

    Map<Integer, Integer> cacheMap;;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new LinkedHashMap<>();
    }
    
    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        int value = cacheMap.get(key);    
        cacheMap.remove(key);
        cacheMap.put(key, value);
        return value;
    }
    
    public void put(int key, int value) {
        // VERY IMP: This should be done first
        // If the entry is present already, remove it and add new one so that gets added to the end
        if (cacheMap.containsKey(key)) {
            cacheMap.remove(key);
        }
        // VERY IMP: This should be done later
        if (cacheMap.size() == capacity) {
            // Remove the first entry if we have reached the capacity
            cacheMap.remove(cacheMap.entrySet().iterator().next().getKey());
        }
        cacheMap.put(key, value);
    }

    public void printMap() {
        for (Map.Entry<Integer, Integer> e : cacheMap.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println();
    }
}

// -----------------------------------------------------------------------------
// Problem 2. Insert, Remove, Contains and getRandom in O(1)
// https://stackoverflow.com/questions/5682218/data-structure-insert-remove-contains-get-random-element-all-at-o1
// -----------------------------------------------------------------------------
class InsertRemoveContainsRandom<T> {
    List<T> entries = new ArrayList<>();
    Map<T, Integer> entriesMap = new HashMap<>();
    public void insert(T data) {
        if (entriesMap.containsKey(data)) {
            System.out.println("Duplicate Entry: " + data);
            return;
        }
        entries.add(data);
        entriesMap.put(data, entries.size() - 1);
    }

    public boolean contains(T data) {
        return entriesMap.containsKey(data);
    }

    public void printList() {
        System.out.println(entries);
    }
    
    public void printMap() {
        for(T key : entriesMap.keySet()) {
            System.out.println(key + " : " + entriesMap.get(key));
        }
        System.out.println();
    }

    public void remove(T data) {
        // Swap this entry with the last element in the list
        if (!entriesMap.containsKey(data)) {
            System.out.println("Entry not present: " + data);
            return;
        }
        int index = entriesMap.get(data);
        T lastElement = entries.get(entries.size() - 1);
        entries.set(entries.size() - 1, data);
        entries.set(index, lastElement);

        // Update the map after the swap
        entriesMap.put(lastElement, index);

        // Remove the last element from list and the map
        entries.remove(entries.size() - 1);
        entriesMap.remove(data);
    }

    public T getRandom() {
        Random r = new Random(System.currentTimeMillis());
        return entries.get(r.nextInt(entries.size()));
    }
}

// -----------------------------------------------------------------------------
// Problem 3. Implement at Stack with getMax at O(1)
// 1. Use extra space. Then do with Constant space
// https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
//
// To get constant space
/*
Push(x) : Inserts x at the top of stack.
If stack is empty, insert x into the stack and make minEle equal to x.
If stack is not empty, compare x with minEle. Two cases arise:
If x is greater than or equal to minEle, simply insert x.
If x is less than minEle, insert (2*x – minEle) into the stack and make minEle equal to x. For example, let previous minEle was 3. Now we want to insert 2. We update minEle as 2 and insert 2*2 – 3 = 1 into the stack.

Pop() : Removes an element from top of stack.
Remove element from top. Let the removed element be y. Two cases arise:
If y is greater than or equal to minEle, the minimum element in the stack is still minEle.
If y is less than minEle, the minimum element now becomes (2*minEle – y), so update (minEle = 2*minEle – y). This is where we retrieve previous minimum from current minimum and its value in stack. For example, let the element to be removed be 1 and minEle be 2. We remove 1 and update minEle as 2*2 – 1 = 3.
*/
// -----------------------------------------------------------------------------
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
        if (maxEntries.entries.size() == 0) {
            maxEntries.entries.addFirst(entry);
            return;
        }

        if (maxEntries.entries.getFirst().compareTo(entry) < 0) {
            maxEntries.entries.addFirst(entry);
        }
    }

    public T pop() {
        // If the stack is empty, then return
        if (entries.size() == 0 || maxEntries.entries.size() == 0) {
            return null;
        }

        // poll returns null if the list is empty
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
        // If the stack is empty, then return
        if (entries.size() == 0 || maxEntries.entries.size() == 0) {
            return;
        }

        System.out.println("Max: " + getMax());
        for (int i = 0; i < entries.size(); i++) {
            System.out.print(entries.get(i) + " ");
        }
        System.out.println();
        System.out.println();
    }
}

// -----------------------------------------------------------------------------
// Problem 4. Get ID and Acknowledge Id
// -----------------------------------------------------------------------------
class IdGenerator {
    // If you want this to generate unique Ids across multiple instances then
    // make both fields static
    private static int id;
    private static LinkedHashSet<Integer> unackIds;

    public IdGenerator() {
        id = 0;
        unackIds = new LinkedHashSet<>();
    }

    public synchronized int GetNextId() {
        id++;
        unackIds.add(id);
        return id;
    }
    
    public synchronized void AcknowledgeId(int id) {
        unackIds.remove(id);
    }

    public int GetIdLevel() {
        if (unackIds.size() != 0) {
            return unackIds.iterator().next();
        } else {
            return 0;
        }
    }
}

// -----------------------------------------------------------------------------
// Problem 5. Rate Limiter
// -----------------------------------------------------------------------------
class RateLimiter {
    private static final int capacity = 5;
    private static List<Long> requestQueue;

    public RateLimiter() {
        requestQueue = new ArrayList<>();
    }

    public void cleanRequestQueue(long currentTime) {
        while (requestQueue.get(requestQueue.size() - 1) < currentTime) {
            requestQueue.remove(requestQueue.size() - 1);
        }
    }

    public boolean limitRequests() {
        synchronized(requestQueue) {
            long currentTime = System.currentTimeMillis();
            cleanRequestQueue(currentTime);

            if (requestQueue.size() == capacity) {
                return false;
            } else {
                requestQueue.add(currentTime);
                return true;
            }
        }
    }
}

// -----------------------------------------------------------------------------
// Problem 6. Queue Implementation
// -----------------------------------------------------------------------------
class MyQueue<T> {
    LinkedList<T> queueList = new LinkedList<>();
    int lastPosition = 0;

    public void add(T data) {
        queueList.add(0, data);
        lastPosition++;
    }

    public T remove() {
        if (lastPosition - 1 < 0) {
            return null;
        }
        lastPosition--;
        return queueList.remove(lastPosition);
    }

    public void printQueue() {
        for (int i = 0; i < lastPosition; i++) {
            System.out.print(queueList.get(i) + " ");
        }
        System.out.println();
    }
}

// -----------------------------------------------------------------------------
// Problem 6b. Queue Implementation using circular array
// -----------------------------------------------------------------------------
class MyQueueUsingArray<T> {
    int capacity;
    T[] queue;
    int headIndex = 0;
    int tailIndex = 0;
    int currentSize = 0;

    //public MyQueueUsingArray(Class<T[]> clazz, int capacity) {
    public MyQueueUsingArray(int capacity) {
        this.capacity = capacity;
        //queue = (T[])Array.newInstance(clazz, capacity);
        queue = (T[])new Object[capacity];
    }

    public synchronized void add(T data) {
        if (currentSize == capacity) {
            System.out.println("Reached capacity. Unable to add: " + data);
            return;
        }
        queue[tailIndex++] = data;
        currentSize++;
        if (tailIndex == capacity) {
            tailIndex = 0;
        }
    }

    public synchronized T remove() {
        if (currentSize == 0) {
            System.out.println("Empty queue");
            return null;
        }
        T tmp = queue[headIndex];
        currentSize--;
        headIndex++;
        if (headIndex == capacity) {
            headIndex = 0;
        }
        return tmp;
    }

    public synchronized void printQueue() {
        //for (int i = headIndex; i < capacity && i <= tailIndex; i++) {
        for (int i = 0; i < capacity; i++) {
            System.out.print(queue[i] + " ");
        }
        /*
        if (headIndex != 0 && tailIndex < headIndex) {
            for (int i = 0; i <= tailIndex; i++) {
                System.out.print(queue[i] + ", ");
            }
        }
        */
        System.out.println();
        System.out.println("H: " + headIndex + "; T: " + tailIndex);
    }
}

// -----------------------------------------------------------------------------
// Problem 7. Implement Rate limit
// https://www.careercup.com/question?id=5147519440912384
// https://stackoverflow.com/questions/667508/whats-a-good-rate-limiting-algorithm
// -----------------------------------------------------------------------------
class RateLimit {
    int qps;
    Queue<Long> entries = new Deque<>();

    public RateLimit(int qps) {
        this.qps = qps;
    }

    public boolean shouldAllowRequest() {
        long currentTime = System.currentTimeMillis();
        if (entries.isEmpty()) {
            entries.add(currentTime);
            return true;
        }
        while (currentTime - entries.element() > 1000) {
            entries.remove();
        }
        if (entries.size() < qps) {
            entries.add(currentTime);
            return true;
        } else {
            return false;
        }
    }
}

// -----------------------------------------------------------------------------
// Main Class
// -----------------------------------------------------------------------------
public class DesignProblems {

    static class ThreadClass implements Runnable {
        private IdGenerator id;

        public ThreadClass (IdGenerator id) {
            this.id = id;
        }

        public void run() {
            System.out.println(id.GetNextId());
        }
    }


    public static void main(String[] args) {
        // Problem 1. LRU Cache
        {
            System.out.println("\nProblem 1. LRU Cache");
            int capacity = 2;
            LRUCache cache = new LRUCache(capacity);
            cache.put(2, 6);
            cache.printMap();
            System.out.println(cache.get(1));       // returns 1
            cache.put(1, 5);    // evicts key 2
            cache.printMap();
            cache.put(1, 2);    // evicts key 1
            cache.printMap();
            System.out.println(cache.get(1));       // returns -1 (not found)
            System.out.println(cache.get(2));       // returns 3
            /*
            cache.put(1, 1);
            cache.put(2, 2);
            System.out.println(cache.get(1));       // returns 1
            cache.put(3, 3);    // evicts key 2
            System.out.println(cache.get(2));       // returns -1 (not found)
            cache.put(4, 4);    // evicts key 1
            System.out.println(cache.get(1));       // returns -1 (not found)
            System.out.println(cache.get(3));       // returns 3
            System.out.println(cache.get(4));       // returns 4
            */
        }

        // Problem 2. Insert, Remove, Contains and getRandom in O(1)
        {
            System.out.println("\nProblem 2. Insert, Remove, Contains and getRandom in O(1)");
            InsertRemoveContainsRandom<Integer> obj = new InsertRemoveContainsRandom<>();
            obj.insert(10);
            obj.insert(20);
            obj.insert(90);
            obj.insert(50);
            obj.insert(20);
            obj.insert(11);
            obj.insert(12);
            obj.insert(13);
            obj.insert(16);
            obj.insert(14);
            obj.insert(15);
            System.out.println(obj.contains(50));
            System.out.println(obj.contains(20));
            System.out.println(obj.contains(60));
            System.out.println(obj.contains(30));
            System.out.println(obj.contains(150));
            obj.remove(20);
            obj.remove(30);
            obj.remove(15);
            System.out.println(obj.contains(15));
            System.out.println("Random: " + obj.getRandom());
            obj.remove(20);
        }

        // Problem 3. Stack with getMax at O(1)
        {
            System.out.println("\nProblem 3. Stack with getMax at O(1)");
            MyStack<Integer> myStack = new MyStack<>();
            myStack.push(3);
            myStack.push(1);
            myStack.push(2);
            myStack.push(6);
            myStack.push(7);
            myStack.printStack();
            myStack.pop();
            myStack.pop();
            myStack.pop();
            myStack.pop();
            myStack.pop();
            myStack.pop();
            myStack.printStack();
            myStack.pop();
            myStack.push(3);
            myStack.push(2);
            myStack.push(5);
            myStack.printStack();
        }

        // Problem 4. Id Generator
        try
        {
            System.out.println("Problem 4. Id Generator");
            IdGenerator id = new IdGenerator();
            IdGenerator id2 = new IdGenerator();
            System.out.println("Next Id: " + id.GetNextId());
            System.out.println("Next Id2: " + id2.GetNextId());
            System.out.println("Next Id: " + id.GetNextId());
            System.out.println("Next Id: " + id.GetNextId());
            System.out.println("Next Id2: " + id2.GetNextId());
            System.out.println("Next Id: " + id.GetNextId());
            System.out.println("Id Level2: " + id2.GetIdLevel());
            System.out.println("Id Level: " + id.GetIdLevel());
            id.AcknowledgeId(3);
            id2.AcknowledgeId(2);
            System.out.println("Id Level: " + id.GetIdLevel());
            id.AcknowledgeId(1);
            System.out.println("Id Level2: " + id2.GetIdLevel());

            Thread th1 = new Thread(new Runnable() {
                public void run() {
                    System.out.println(id.GetNextId());
                }
            });
            th1.start();

            Thread th2 = new Thread(new ThreadClass(id));
            Thread th3 = new Thread(new ThreadClass(id));
            th2.start();
            th3.start();
            th1.join();
            th2.join();
            th3.join();


        } catch (Exception e) {
        }

        // Problem 5. Rate Limiter
        {
            System.out.println("Problem 5. Rate Limiter");
            RateLimiter ob = new RateLimiter();
        }

        // Problem 6. MyQueue
        {
            System.out.println("Problem 6. MyQueue");
            MyQueue<Integer> myQueue = new MyQueue();
            myQueue.add(1);
            myQueue.add(2);
            myQueue.add(3);
            myQueue.add(4);
            myQueue.printQueue();
            myQueue.remove();
            myQueue.printQueue();
            myQueue.remove();
            myQueue.printQueue();
            myQueue.remove();
            myQueue.printQueue();
            myQueue.remove();
            myQueue.printQueue();
        }

        // Problem 6b. MyQueue using circular array
        {
            System.out.println("Problem 6b. MyQueue using circular array");
            //MyQueueUsingArray<Integer> queue = new MyQueueUsingArray<>(Integer[].class, 4);
            MyQueueUsingArray<Integer> queue = new MyQueueUsingArray<>(4);
            Thread th1 = new Thread(new Runnable() {
                public void run() {
                    queue.add(1);
                    queue.add(2);
                    queue.printQueue();
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    /*
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    queue.add(6);
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    */
                }
            });
            Thread th2 = new Thread(new Runnable() {
                public void run() {
                    queue.add(3);
                    queue.printQueue();
                    /*
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    queue.add(7);
                    queue.printQueue();
                    */
                }
            });
            Thread th3 = new Thread(new Runnable() {
                public void run() {
                    queue.add(4);
                    queue.add(5);
                    queue.printQueue();
                    /*
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    System.out.println("R: " + queue.remove());
                    queue.printQueue();
                    */
                }
            });
            th1.setName("th1");
            th2.setName("th2");
            th3.setName("th3");
            th1.start();
            th2.start();
            th3.start();
            /*
            queue.add(1);
            queue.add(2);
            queue.add(3);
            queue.add(4);
            queue.printQueue();
            queue.add(5);
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            queue.add(5);
            queue.add(6);
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            System.out.println("R: " + queue.remove());
            queue.printQueue();
            */
        }
    }

    // Problem 7. Implement Rate limit
    {
    }
}

