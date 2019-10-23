/*
   Class for making ascending order
   Class has a method for comparing elements.
   It works in that way:
   if el1 > el2 return 1, if el1 == el2 return 0, if el1 < el2 return -1
   null is the maximum
 */
public class ReaderTupleComparatorDescending<T extends Comparable<T>> extends ReaderTupleComparator<T> {
    @Override
    public int compare(ReaderTuple<T> o1, ReaderTuple<T> o2) {
        if (o1.peek() == null && o2.peek() == null) {
            return 0;
        } else if (o1.peek() == null) {
            return 1;
        } else if (o2.peek() == null) {
            return -1;
        }

        // Multiply by 2 to make it in reverse order
        return -1 * o1.peek().compareTo(o2.peek());
    }
}
