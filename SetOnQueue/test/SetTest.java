import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    //Constructor test
    /** only one is needed. */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Add tests
    /** testing adding to empty set. */
    @Test
    public final void testAddToEmptySet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("hello");

        s.add("hello");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /** testing adding to set. */
    @Test
    public final void testAddRoutineCase() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("hello");
        Set<String> sExpected = this.createFromArgsRef("hello", "world");

        s.add("world");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /** testing adding to beginning of set. */
    @Test
    public final void testAddToLargerSet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4", "5", "6",
                "7", "8", "9");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10");

        s.add("10");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Remove tests
    /**
     * testing removing from singleton set.
     */
    @Test
    public final void testRemovingFromSingleton() {
        /*
         * Set up variables and call method under test
         */
        String x = "hi";
        Set<String> s = this.createFromArgsTest(x);
        Set<String> sExpected = this.createFromArgsRef();

        s.remove(x);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * testing removing from 2-set.
     */
    @Test
    public final void testRemovingRoutine() {
        /*
         * Set up variables and call method under test
         */
        String x = "there";
        Set<String> s = this.createFromArgsTest("hi", x);
        Set<String> sExpected = this.createFromArgsRef("hi");

        s.remove(x);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //removeAny tests
    /**
     * Test removing from singleton set.
     */
    @Test
    public final void testRemoveAnySingleton() {
        //setup
        Set<String> s = this.createFromArgsTest("hi");
        Set<String> sExpected = this.createFromArgsRef();

        //call
        s.removeAny();

        //evaluation
        assertEquals(s, sExpected);
    }

    /**
     * Test removing from 2-set.
     */
    @Test
    public final void testRemoveAnyRoutine() {
        //setup
        Set<String> s = this.createFromArgsTest("1", "2");
        Set<String> sExpected = this.createFromArgsRef("1", "2");

        //call
        String removed = s.removeAny();

        //evaluation
        assertEquals(sExpected.contains(removed), true);
        sExpected.remove(removed);
        assertEquals(s, sExpected);
    }

    /**
     * Test removing from 10-set.
     */
    @Test
    public final void testRemoveAnyFromLargeSet() {
        //setup
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10");

        //call
        String removed = s.removeAny();

        //evaluation
        assertEquals(sExpected.contains(removed), true);
        sExpected.remove(removed);
        assertEquals(s, sExpected);
    }

    //Contains tests
    /**
     * testing empty set if it contains a string.
     */
    @Test
    public final void testContainsAStringInEmptySet() {
        /*
         * Set up variables and call method under test
         */
        String x = "hi";
        Set<String> s = this.createFromArgsTest("");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.contains(x), false);
    }

    /**
     * testing singleton if it contains a string.
     */
    @Test
    public final void testContainsSingleton() {
        /*
         * Set up variables and call method under test
         */
        String x = "hi";
        Set<String> s = this.createFromArgsTest("hola");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.contains(x), false);
    }

    /**
     * testing for a word in different cases.
     */
    @Test
    public final void testContainsCases() {
        /*
         * Set up variables and call method under test
         */
        String x = "hi";
        Set<String> s = this.createFromArgsTest("Hi");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.contains(x), false);
    }

    //Size tests
    /** testing empty Set. */
    @Test
    public final void testsizeOfEmptySet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), 0);
    }

    /** testing 1-Set. */
    @Test
    public final void testsizeOfOneElementSet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("hi");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), 1);
    }

    /** testing 10-Set. */
    @Test
    public final void testsizeOfLongerSet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), 10);
    }

}
