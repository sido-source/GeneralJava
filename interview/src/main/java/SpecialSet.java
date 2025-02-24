import java.util.*;

class SpecialSet extends AbstractSet<Integer> implements Set<Integer> {
    private final List<Integer> list = new ArrayList<>();
    private final Map<Integer, Integer> indexMap = new HashMap<>();

    @Override
    public boolean add(Integer value) {
        if (contains(value)) return false;
        indexMap.put(value, list.size());
        list.add(value);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Integer indexBoxed = indexMap.remove(o);
        if (indexBoxed == null) return false;
        int index = indexBoxed;
        int last = list.size() - 1;
        Integer lastElement = list.remove(last);
        if (index != last) {
            indexMap.put(lastElement, index);
            list.set(index, lastElement);
        }
        return true;
    }

    public boolean removeByIndex(int index) {
        if (index < 0 || index >= list.size()) return false;
        Integer elementToRemove = list.get(index);  // Get the element at the specified index
        return remove(elementToRemove);             // Reuse the existing remove logic
    }

    @Override
    public boolean contains(Object o) {
        return indexMap.containsKey(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    public List<Integer> toList() {
        return new ArrayList<>(list);  // Convert the set into a list
    }

    public static List<Integer> processArray(int[] arr) {
        SpecialSet specialSet = new SpecialSet();

        for (int value : arr) {
            if (value < 0) {
                specialSet.add(value);  // Add negative values to the set
            } else if (value > 0) {
                // Remove the n-th element if within bounds
                specialSet.removeByIndex(value - 1);  // Convert to zero-based index by subtracting 1
            }
            // Zero values are ignored
        }

        // Return the remaining elements in the special set as a list
        System.out.println(specialSet.toList());
        return specialSet.toList();
    }
}
