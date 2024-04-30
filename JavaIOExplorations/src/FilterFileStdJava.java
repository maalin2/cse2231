import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Program to copy a text file into another file.
 *
 * @author Mohammed Maalin
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     *            filter-file-name
     */
    public static void main(String[] args) {
        String file = args[0];
        String file2 = args[1];
        String file3 = args[2];

        BufferedReader read;
        BufferedReader filter;
        PrintWriter write;

        try {
            read = new BufferedReader(new FileReader(file));
            filter = new BufferedReader(new FileReader(file3));
            write = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
        } catch (IOException e) {
            System.err.println("Error opening file to read");
            return;
        }

        Set<String> filters = new HashSet<>();
        try {
            String s = filter.readLine();
            while (s != null) {
                filters.add(s);
                s = read.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from filter file");
        }

        try {
            String s = read.readLine();
            while (s != null && !filters.contains(s)) {
                write.println(s);
                s = read.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file");

        }

        try {
            read.close();
            filter.close();
            write.close();
        } catch (IOException e) {
            System.err.println("Error closing file to read from");
        }
    }

}
