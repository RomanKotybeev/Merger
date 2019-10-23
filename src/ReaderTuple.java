import java.io.BufferedReader;
import java.io.IOException;

// Class represents a tuple of a file and the first element in the file
// (File, element)
// This class helps to not store all elements in the file. Only the first one

public class ReaderTuple<T extends Comparable<T>> {
    // For checking type. In our case Integer og String
    private final Class<T> cls;

    private final BufferedReader reader;
    // Ascending or descending
    private Order order;

    // Previous and current element
    private T prev;
    private T element;

    // Track the filename and the position of the invalid file
    private String filename;
    private int pos;

    public ReaderTuple(BufferedReader reader, Class<T> cls, Order order, String filename) throws IOException {
        this.cls = cls;
        this.order = order;
        this.reader = reader;
        this.filename = filename;
        pos = 0;
        readNext();
    }

    // The method returns an element
    public T peek() {
        return element;
    }

    // Read an element from the file line by line
    public T readNext() throws IOException {
        prev = element;
        String line = reader.readLine();

        if(line != null) {
            // Checking type of the element
            if(cls.isAssignableFrom(String.class)) {
                element = (T)line;
            } else if(cls.isAssignableFrom(Integer.class)) {
                element = (T)Integer.valueOf(line);
            } else {
                throw new IllegalArgumentException("Type should be integer or string");
            }

            // Check whether an element of a file breaks sorting.
            // If it breaks, stop treat this file and print the position of the element and the file name
            if (prev != null && ((element.compareTo(prev) < 0 && order == Order.Ascending) ||
                                 (element.compareTo(prev) > 0 && order == Order.Descending))) {
                element = null;
                System.out.printf("The order is not valid at position %d in %s\n", pos, filename);
            }
        } else {
            element = null;
        }

        pos++;
        return element;
    }
}




