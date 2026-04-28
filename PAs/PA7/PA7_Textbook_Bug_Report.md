# PA7 Extra Credit: HashMap Iterator Bug Report

**Student:** Aiden Wang

## 1. Problem: Snapshot Iterator Flaw
The `entrySet()` implementation utilizes a **Snapshot Iterator** design. It captures the state of the map at a single point in time by copying all entries into an `ArrayList` buffer, decoupling the iterator from live data.
```java
ArrayList<Entry<K,V>> buffer = new ArrayList<>();
for (int h=0; h < capacity; h++)
    if (table[h] != null) buffer.add(table[h]);
```

## 2. Risks: Inconsistency & Memory Overhead
- **Stale Data**: The iterator is "blind" to structural changes (like `remove`) made during iteration, leading to the processing of "ghost" entries that no longer exist in the map.
- **Resource Waste**: Copying the entire map requires **$O(N)$** auxiliary space, which can trigger `OutOfMemoryError` or performance degradation on large datasets.

## 3. Correction: Dynamic Inner Class Iterator
Replace the snapshot with a **Live Iterator** (inner class) that scans the `table` array in real-time using a dynamic cursor.
```java
private class EntryIterator implements Iterator<Entry<K,V>> {
    private int j = -1; 
    public Entry<K,V> next() {
        j++; 
        while (j < capacity && isAvailable(j)) j++; // Skip null/defunct slots
        return table[j];
    }
}
```

## 4. Advantages: Efficiency & Integrity
- **Real-time Accuracy**: Iteration remains synchronized with the current state of the map, ensuring data integrity.
- **O(1) Space Complexity**: Eliminates the memory burden of temporary buffers, ensuring the system remains scalable and efficient.

## 5. Verification
Test results from `PA7_BugDemo.java` confirmed that the original iterator failed to recognize mid-loop removals, continuing to iterate over deleted keys. This proves the "snapshot" is unsafe for dynamic data environments.

