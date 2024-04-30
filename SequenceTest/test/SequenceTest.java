import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Mohammed Maalin
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    //constructor test cases

    /** only one way to test constructor... */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

//add tests

    /** testing adding to empty sequence. */
    @Test
    public final void testAddToEmptySequence() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("hello");

        s.add(0, "hello");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /** testing adding to sequence. */
    @Test
    public final void testAddRoutineCase() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("hello");
        Sequence<String> sExpected = this.createFromArgsRef("hello", "world");

        s.add(1, "world");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /** testing adding to beginning of sequence. */
    @Test
    public final void testAddToBeginningOfSequence() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("two", "three");
        Sequence<String> sExpected = this.createFromArgsRef("one", "two",
                "three");

        s.add(0, "one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //remove tests
    /**
     * testing removing from 1-sequence.
     */
    @Test
    public final void testRemovingFromSingleElementSeq() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("hi");
        Sequence<String> sExpected = this.createFromArgsRef();

        s.remove(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * testing removing last position.
     */
    @Test
    public final void testRemovingFinalElementFromSeq() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("hi", "there");
        Sequence<String> sExpected = this.createFromArgsRef("hi");

        s.remove(s.length() - 1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

//length tests
    /** testing empty sequence. */
    @Test
    public final void testLengthOfEmptySeq() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.length(), 0);
    }

    /** testing 1-sequence. */
    @Test
    public final void testLengthOfOneElementSeq() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("hi");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.length(), 1);
    }

    /** testing 10-sequence. */
    @Test
    public final void testLengthOfLongerSeq() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.createFromArgsTest("one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine", "ten");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.length(), 10);
    }

}
