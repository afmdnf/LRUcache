import java.util.HashMap;

public class LRUCache<K, V> {

    private HashMap<K, Node<K, V>> cache;
    private Node<K, V> LRU;
    private Node<K, V> MRU;
    private int capacity;
    private int size;

    public LRUCache(int capacity) throws InstantiationException {
        if (capacity <= 1) throw new InstantiationException("Cache capacity must be > 1");
        this.capacity = capacity;
        this.size = 0;
        LRU = new Node<>(null, null, null, null);
        MRU = new Node<>(null, null, null, null);
        cache = new HashMap<>();
    }

    public V get(K key) {
        Node<K, V> tempNode = cache.get(key);
        if (tempNode == null){ // not found in cache
            return null;
        }
        else if (tempNode.key == MRU.key){ // If MRU, leave list as is
            return MRU.value;
        }

        Node<K, V> nextNode = tempNode.next;
        Node<K, V> prevNode = tempNode.previous;
        if (tempNode.key == LRU.key) { // If left-most, update LRU
            nextNode.previous = null;
            LRU = nextNode;
        }
        else { // If middle, update nodes before and after
            prevNode.next = nextNode;
            nextNode.previous = prevNode;
        }
        // Lastly, make node MRU
        tempNode.previous = MRU;
        MRU.next = tempNode;
        MRU = tempNode;
        MRU.next = null;

        return tempNode.value;
    }

    public void set(K key, V value) {
        if (get(key) != null) { // If already exists, do a generic get and then update
            cache.get(key).value = value;
            return;
        }
        Node<K, V> tempNode = new Node<>(MRU, null, key, value);
        if (size == 0)
            LRU = tempNode;
        if (size == capacity){ // Cache full so remove LRU
            cache.remove(LRU.key);
            LRU = LRU.next;
            LRU.previous = null;
        }
        cache.put(key, tempNode); ++size;
        // Update MRU
        MRU.next = tempNode;
        MRU = tempNode;
    }

    private class Node<T, U> {
        Node<T, U> previous;
        Node<T, U> next;
        T key; U value;

        Node(Node<T, U> previous, Node<T, U> next, T key, U value) {
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
}
