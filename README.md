# LRU Cache
This is a basic Java implementation of a Least-Recently-Used (LRU) Cache. The Cache stores key-value pairs and works with Java Generic objects. I have also included basic functionality tests.

## Data Structures
* `HashMap<K, Node>` -> `V`
* `Node`: abstraction representing the node of a **doubly-linked list**

The doubly-linked list was implemented using the `Node` class created from scratch for learning purposes. Usage of a Java `Deque` (with a `LinkedList`) would achieve equivalent functionality and runtime.

Note: Java provides a [LinkedHashMap](http://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html), which could be used appropriately (as described below) to implement a LRU cache without the need to implement/use the above data structures but this was avoided, again for learning purposes.
```
LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) // set accessOrder to true to mimic LRU
```

## Runtime Analysis
Both `get` and `set` run in O(1) time. This stems from the usage of the `HashMap` to retrive the `Node` storing the value in O(1) time (in case of `get`) and the doubly-linked list, which allows the reordering of nodes in O(1) time (to update `Node` positions for `get` calls and LRU deletion).

## Possible Improvements

* Implement multiple cache layers
* Distributed caching
