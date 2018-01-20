import java.util.*;

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

public class LRUCacheImplementation {
    public static void main(String[] args) {
        System.out.println("LRU Cache");
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
}
