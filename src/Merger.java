import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// Class for reading, merging and writing items in readerTuple
// ReaderTuples are stored in a heap.
// Implementing a max heap or min heap depends on comparator:
// ReaderTupleComparatorAscending and
// ReaderTupleComparatorDescending classes

public class Merger<T extends Comparable<T>> {
    /*
        Method returns a list of read files
        @param files are input files (in1.txt, in2.txt, ...)
        @param cls is used to work with types String and Integer
        @param order is responsible for order mode (ascending or descending)
     */
    public List<ReaderTuple<T>> getReaders(List<String> files, Class<T> cls, Order order) {
        List<ReaderTuple<T>> readers = new LinkedList();

        for(String filename : files) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                ReaderTuple<T> readerTuple = new ReaderTuple<T>(reader, cls, order, filename);

                readers.add(readerTuple);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }

        return readers;
    }

    /*
        @readers is list of readerTuples created in getReaders
        @output is a file for writing to
        @comparator is for realisation a max heap or a min heap
     */
    public void merge(List<ReaderTuple<T>> readers, String output, ReaderTupleComparator<T> comparator) {
        try {
            // output is a file for writing to
            FileWriter writer = new FileWriter(output);

            PriorityQueue<ReaderTuple<T>> queue = new PriorityQueue<>(comparator);
            for (ReaderTuple<T> reader : readers) {
                // Check whether a reader contains items. If it doesn't, ignore it
                if (reader.peek() != null) {
                    queue.add(reader);
                }
            }

            // Here we write elements of readerTuple
            // PriorityQueue is a complete binary tree.
            // We implement a Max Heap or a Min Heap depending on comparator
            while (!queue.isEmpty()) {
                ReaderTuple<T> nextReader = queue.poll();
                writer.append(nextReader.peek().toString());
                writer.append(System.lineSeparator());

                // The next element in the ReaderTuple
                nextReader.readNext();
                // If the element is null, ignore this reader
                if (nextReader.peek() != null) {
                    queue.add(nextReader);
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
