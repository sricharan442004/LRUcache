# LRU Cache Implementation

This project implements an **LRU Cache** (Least Recently Used) using a **Doubly Linked List** (CDLL) for efficient management of cache items. The cache ensures that the most recently used items are easily accessible, and the least recently used items are evicted when the cache reaches its capacity.

## Caching

**Caching** is the process of storing data in a temporary storage area (cache) so that future requests for that data can be served faster. It helps to reduce the time it takes to access data and reduces the load on underlying data sources.

**Why Cache?**
- **Speed**: Caching provides faster data retrieval as it stores copies of frequently accessed data in memory.
- **Efficiency**: It reduces the need to repeatedly fetch data from slow or expensive resources like databases or APIs.
- **Performance**: With cache in place, systems can handle more requests and perform better under load.

## In-Memory Cache: Redis and Memcache

### Redis
Redis is an in-memory data store, primarily used as a cache, but it can also serve as a message broker and a persistent database. It supports complex data types like strings, hashes, lists, sets, and more. Redis is known for its performance and high availability.

### Memcache
Memcache is a simple, high-performance, distributed memory caching system. It is often used to speed up dynamic web applications by reducing database load. Memcache is typically used to store key-value pairs, but it doesn't offer the persistence features that Redis does.

## Cache Memory in Computer Organization

Cache memory is a small, high-speed storage located between the CPU and the main memory (RAM). It stores frequently accessed data or instructions to speed up the execution of programs. The CPU can access cache memory much faster than it can access RAM.

- **Levels of Cache**: There are typically multiple levels of cache, including L1 (smallest and fastest), L2, and L3 (larger but slower).
- **Cache Hit and Miss**: When the data is found in cache, it's a cache hit; when it's not found, it's a cache miss, and the data is fetched from slower memory.
- **Write Policies**: Cache memory uses write-through or write-back policies to manage how data is written to both cache and main memory.

## Different Cache Replacement Strategies

Cache replacement strategies determine how data is evicted from the cache when the cache reaches its capacity. Here are some of the most commonly used strategies:

* **Least Recently Used (LRU)**
  - Description: The least recently used item is evicted first. This is the strategy used in your project. It assumes that the least recently accessed data is the least likely to be used again soon.
  - Example: If you access data in the order A → B → C, and the cache is full, item A will be removed first.

* **First-In-First-Out (FIFO)**
  - Description: The first item added to the cache is the first one to be evicted. This strategy does not consider the frequency or recency of access, just the order in which data was added.
  - Example: If data is added in the order A → B → C, A will be removed first when the cache is full.

* **Least Frequently Used (LFU)**
  - Description: Items that are accessed less frequently are evicted first. LFU keeps track of how often data is accessed and removes the least frequently used data when the cache is full.
  - Example: If item A is accessed 5 times, B 3 times, and C 1 time, item C will be evicted first.

* **Random Replacement (RR)**
  - Description: A random item is chosen for eviction. This strategy is simple but not always the most efficient.
  - Example: If the cache is full, one of the cached items is chosen randomly to be evicted.

* **Most Recently Used (MRU)**
  - Description: The most recently used item is evicted first. This is the opposite of LRU and may be useful in some specific scenarios.
  - Example: If you access data in the order A → B → C, item C will be removed first.

* **Adaptive Replacement Cache (ARC)**
  - Description: ARC adapts to the workload by dynamically adjusting between LRU and LFU strategies to minimize cache misses.
  - Example: ARC balances between recency and frequency of access to decide which item to evict.

## How to Use the LRU Cache

### Methods:
1. **`get(int key)`**: Returns the value of the key if present in the cache, else returns `-1`.
2. **`put(int key, int value)`**: Inserts a new key-value pair into the cache. If the cache exceeds the capacity, the least recently used item is evicted.
3. **`remove()`**: Removes and returns the least recently used (LRU) item from the cache.
4. **`moveAtFront(CDLLNode nodeToMove)`**: Moves the specified node to the front of the cache to mark it as recently used.
