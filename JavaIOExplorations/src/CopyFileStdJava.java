import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Program to copy a text file into another file.
 *
 * @author Mohammed Maalin
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {
        String file = args[0];
        String file2 = args[1];

        BufferedReader read;
        PrintWriter write;

        try {
            read = new BufferedReader(new FileReader(file));
            write = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
        } catch (IOException e) {
            System.err.println("Error opening file to read");
            return;
        }

        try {
            String s = read.readLine();
            while (s != null) {
                write.println(s);
                s = read.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file");

        }

        try {
            read.close();
            write.close();
        } catch (IOException e) {
            System.err.println("Error closing file to read from");
        }
    }

}
