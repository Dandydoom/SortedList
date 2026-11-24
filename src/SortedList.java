import java.util.ArrayList;

/**
 * SortedList.java
 * Works like an ArrayList of Strings, except the Strings are always kept
 * in sorted ascending (lexicographic) order. A binary search, written by
 * hand, finds where each new String belongs.
 *
 * @author Kirby Fortney
 */
public class SortedList
{
    private ArrayList<String> list = new ArrayList<>();

    /**
     * Adds a String in its sorted position.
     *
     * @param value the String to add
     * @return the index where the String was inserted
     */
    public int add(String value)
    {
        int index = binarySearch(value);
        if (index < 0)
        {
            index = -(index + 1); // not found, so convert to the insertion point
        }
        list.add(index, value);
        return index;
    }

    /**
     * Binary search for a String in the sorted list.
     *
     * @param target the String to find
     * @return the index of target if it is in the list, otherwise
     *         -(insertion point) - 1, where the insertion point is the index
     *         where target would go to keep the list sorted
     */
    public int binarySearch(String target)
    {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high)
        {
            int mid = (low + high) / 2;
            int compare = target.compareTo(list.get(mid));

            if (compare == 0)
            {
                return mid; // found it
            }
            else if (compare > 0)
            {
                low = mid + 1; // target is on the right side
            }
            else
            {
                high = mid - 1; // target is on the left side
            }
        }
        return -(low + 1); // not found, low is where it would go
    }

    public String get(int index)
    {
        return list.get(index);
    }

    public int size()
    {
        return list.size();
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    public void clear()
    {
        list.clear();
    }

    @Override
    public String toString()
    {
        return list.toString();
    }
}
