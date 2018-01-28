import java.util.*;

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

public class DesignProblems {
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
    }
}
