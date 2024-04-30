import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Mohammed Maalin
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    // TODO - declare static and instance data members

    /*
     * Constructor ------------------------------------------------------------
     */

    String first = "";
    String last = "";
    int num = 1;
    static Map<String, Integer> nameFreq = new HashMap<>();

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {
        this.first = firstName;
        this.last = lastName;
        if (nameFreq.containsKey(lastName)) {
            nameFreq.replace(lastName, nameFreq.get(lastName) + 1);
        } else {
            nameFreq.put(lastName, 1);
        }
        this.num = nameFreq.get(lastName);
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {
        return this.first.toUpperCase().charAt(0)
                + this.first.substring(1, this.first.length()) + " "
                + this.last.toUpperCase().charAt(0)
                + this.last.substring(1, this.last.length());

    }

    @Override
    public String emailAddress() {
        return this.last + "." + this.num + "@osu.edu";

    }

    @Override
    public String toString() {
        return "Name : " + this.first.toUpperCase().charAt(0)
                + this.first.substring(1, this.first.length()) + " "
                + this.last.toUpperCase().charAt(0)
                + this.last.substring(1, this.last.length()) + ", Email: "
                + this.last + "." + this.num + "@osu.edu";
    }

}