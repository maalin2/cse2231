import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        String name = " ";
        while (!name.isEmpty()) {
            out.print("Enter a name ");
            name = in.nextLine();

            String[] nameArr = name.split(" +");
            String first = nameArr[0];
            String last = nameArr[1];

            EmailAccount myAccount = new EmailAccount1(first, last);
            out.println(myAccount.name());
            out.println(myAccount.emailAddress());
            out.println(myAccount);
        }

        in.close();
        out.close();
    }

}
