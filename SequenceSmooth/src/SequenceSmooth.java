import components.sequence.Sequence;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        SimpleWriter s = new SimpleWriter1L();
        Sequence<Integer> s2Copy = s2.newInstance();
        s.println(s2Copy);
        for (int i = 0; i < s1.length(); i++) {
            if (s1.length() > i + 1) {
                //negative check
                //both odd both negative
                int entry1 = s1.entry(i) / 2;
                int entry2 = s1.entry(i + 1) / 2;

                int avg = (entry1 + entry2); //a/2 + b/2
                s.println(avg);

                if ((entry1 >= 0 && entry2 >= 0) && (entry1 % 2 != 0)
                        && (entry2 % 2 != 0)) {
                    avg++;
                } else if ((entry1 < 0 && entry2 < 0) && (entry1 % 2 != 0)
                        && (entry2 % 2 != 0)) {
                    avg--;
                } //both odd and negative

                s.println(avg);
                //s.println(Integer.MAX_VALUE);
                s2Copy.add(s2Copy.length(), avg);
                s.println(s2Copy);
            }

        }
        s2.transferFrom(s2Copy);
        s.close();

    }

}