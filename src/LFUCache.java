import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache<K, V> {

    private Map<K, V> cache;
    private Map<K, Integer> counts;
    private Map<Integer, LinkedHashSet<K>> countKeys;
    private int capacity;
    private int minCount = -1;

    public LFUCache(int capacity) throws InstantiationException {
        if (capacity <= 1) throw new InstantiationException("Cache capacity must be > 1");
        this.capacity = capacity;
        cache = new HashMap<>();
        counts = new HashMap<>();
        countKeys = new HashMap<>();
        countKeys.put(1, new LinkedHashSet<>());
    }

    public V get(K key) {
        if (!cache.containsKey(key))
            return null;
        int count = counts.get(key);
        counts.put(key, count + 1); // update count
        countKeys.get(count).remove(key); // remove from old count's set
        if (countKeys.get(count).size() == 0) {
            countKeys.remove(count);
            if (count == minCount)
                ++minCount;
        }
        if (!countKeys.containsKey(count + 1))
            countKeys.put(count + 1, new LinkedHashSet<>());
        countKeys.get(count + 1).add(key);
        return cache.get(key);
    }

    public void set(K key, V value) {
        if (get(key) != null) {
            cache.put(key, value);
            return;
        }
        if (cache.size() >= capacity) {
            LinkedHashSet<K> set = countKeys.get(minCount);
            K evict = set.iterator().next();
            set.remove(evict);
            if (set.size() == 0) {
                countKeys.remove(minCount);
                do {
                    ++minCount;
                } while (!countKeys.containsKey(minCount));
            }
            cache.remove(evict);
            counts.remove(evict);
        }
        cache.put(key, value);
        counts.put(key, 1);
        LinkedHashSet<K> set = countKeys.getOrDefault(1, new LinkedHashSet<>());
        set.add(key);
        countKeys.put(1, set);
        minCount = 1;
    }
}
