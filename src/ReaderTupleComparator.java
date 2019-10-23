import java.util.Comparator;

// This class is necessary to implement comparing elements in the files
// That's why it implements Comparator interface
// We need to have sort in ascending or descending order
public abstract class ReaderTupleComparator<T extends Comparable<T>> implements Comparator<ReaderTuple<T>> {
    @Override
    public abstract int compare(ReaderTuple<T> o1, ReaderTuple<T> o2);
}