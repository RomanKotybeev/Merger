import java.util.LinkedList;
import java.util.List;


public class SortIt<T extends Comparable<T>> {

    public static void main(String[] args) {
        // Default values
        Order order = Order.Ascending;
        InputType inputType = InputType.Invalid;

        // To here we will write elements
        String output = null;
        boolean outputIsRead = false;

        // inputs: in1.txt, in2.txt, ...
        List<String> inputs = new LinkedList<>();

        // Choosing modes
        for (String arg : args) {
            switch(arg) {
                case "-i":
                    inputType = InputType.Integer;
                    break;
                case "-s":
                    inputType = InputType.String;
                    break;
                case "-a":
                    order = Order.Ascending;
                    break;
                case "-d":
                    order = Order.Descending;
                    break;
                default:
                    // It works only if the first file is for output
                    // The rests are inputs (in1.txt, in2.txt, in3.txt, ...)
                    if (!outputIsRead) {
                        output = arg;
                        outputIsRead = true;
                    } else {
                        inputs.add(arg);
                    }
            }
        }

        // For order mode. How we can compare elements in readerTuple
        ReaderTupleComparator comparator;
        switch (order) {
            case Descending:
                comparator = new ReaderTupleComparatorDescending<>();
                break;
            default:
                comparator = new ReaderTupleComparatorAscending<>();
        }

        // Read and merge
        switch (inputType) {
            case Integer:
                Merger<Integer> mergerInt = new Merger<>();
                List<ReaderTuple<Integer>> readersInt = mergerInt.getReaders(inputs, Integer.class, order);
                mergerInt.merge(readersInt, output, comparator);
                break;
            case String:
                Merger<String> mergerStr = new Merger<>();
                List<ReaderTuple<String>> readersStr = mergerStr.getReaders(inputs, String.class, order);
                mergerStr.merge(readersStr, output, comparator);
                break;
            default:
                System.out.println("The input type is not specified (-i/-s)");
        }
    }
}
