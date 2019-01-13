# LRU Cache
A basic Java implementation of a Least-Recently-Used (LRU) Cache. The cache stores key-value pairs and works with Java generic <K, V> objects. Also included basic functionality tests.

## Data Structures
* `HashMap<K, Node>`
* `Node`: abstraction representing the node of a **doubly-linked list**, containing `V`

The doubly-linked list was implemented using the `Node` class. Usage of a Java `Deque`/`LinkedList` would achieve equivalent functionality and runtime.

Note: Java provides a [LinkedHashMap](http://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html), which could be used as described below to implement a LRU cache without the need to implement the above data structures but this was avoided in my implementation, for learning purposes.
```
LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) // set accessOrder=true to mimic LRU
```

## Runtime Analysis
Both `get` and `set` run in O(1) time. This stems from the usage of the `HashMap` to retrieve the `Node` storing the value in O(1) time (in case of `get`) and the doubly-linked list, which allows the reordering of nodes in O(1) time (to update `Node` positions for `get` calls and LRU deletion).

## Possible Improvements

* Implement multiple cache layers
* Thread-safe/concurrency support

# LFU Cache
A basic Java implementation of a Least-Frequently-Used (LFU) Cache using 2 maps storing the value and frequency for a particular key and a mapping between frequencies and keys with those frequencies. It also supports O(1) `get` and `set` operations.
