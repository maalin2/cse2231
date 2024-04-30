import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    //Constructor test
    /** only one is needed. */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * map up variables and call method under test
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Add tests
    /** testing adding to empty map. */
    @Test
    public final void testAddToEmptyMap() {
        /*
         * map up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("one", "1");

        s.add("one", "1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /** testing adding to map. */
    @Test
    public final void testAddRoutineCase() {
        /*
         * map up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("one", "1");
        Map<String, String> sExpected = this.createFromArgsRef("one", "1",
                "two", "2");

        s.add("two", "2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /** testing adding to beginning of map. */
    @Test
    public final void testAddToLargermap() {
        /*
         * map up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("one", "1", "two", "2",
                "three", "3", "four", "4");
        Map<String, String> sExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3", "four", "4", "five", "5");

        s.add("five", "5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Remove tests
    /**
     * testing removing from singleton map.
     */
    @Test
    public final void testRemoveEdge() {
        /*
         * map up variables and call method under test
         */
        String x = "one\", \"1\"";
        Map<String, String> s = this.createFromArgsTest(x);
        Map<String, String> sExpected = this.createFromArgsRef();

        s.remove(x);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * testing removing from 2-map.
     */
    @Test
    public final void testRemovingRoutine() {
        /*
         * map up variables and call method under test
         */
        String x = "one\", \"1\"";
        String y = "\"two\", \"2\"";
        Map<String, String> s = this.createFromArgsTest(x + y);
        Map<String, String> sExpected = this.createFromArgsRef(x);

        s.remove(y);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //removeAny tests
    /**
     * Test removing from singleton map.
     */
    @Test
    public final void testRemoveAnyEdgeCase() {
        //setup
        Map<String, String> s = this.createFromArgsTest("one", "1");
        Map<String, String> sExpected = this.createFromArgsRef();

        //call
        s.removeAny();

        //evaluation
        assertEquals(s, sExpected);
    }

    /**
     * Test removing from 2-map.
     */
    @Test
    public final void testRemoveAnyRoutine() {
        //setup
        Map<String, String> s = this.createFromArgsTest("one", "1", "two", "2");
        Map<String, String> sExpected = this.createFromArgsRef("one", "1");

        //call
        Map.Pair<String, String> removed = s.removeAny();

        //evaluation
        assertEquals(sExpected.hasKey(removed.key()), true);
        sExpected.remove(removed.key());
        assertEquals(s, sExpected);
    }

    /**
     * Test removing from 10-map.
     */
    @Test
    public final void testRemoveAnyFromLargemap() {
        //setup
        Map<String, String> s = this.createFromArgsTest("one", "1", "two", "2",
                "three", "3", "four", "4", "five", "5", "six", "6");
        Map<String, String> sExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3", "four", "4", "five", "5");

        //call
        Map.Pair<String, String> removed = s.removeAny();

        //evaluation
        assertEquals(sExpected.hasKey(removed.key()), true);
        sExpected.remove(removed.key());
        assertEquals(s, sExpected);
    }

    //hasKey tests
    /**
     * testing empty map if it has a key.
     */
    @Test
    public final void testHasKeyInEmptymap() {
        /*
         * map up variables and call method under test
         */
        String x = "one\", \"1\"";
        Map<String, String> s = this.createFromArgsTest("");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.hasKey(x.substring(0, 4)), false);
    }

    /**
     * testing singleton if it contains a string.
     */
    @Test
    public final void testContainsSingleton() {
        /*
         * map up variables and call method under test
         */
        String x = "\"two\", \"2\"";
        Map<String, String> s = this.createFromArgsTest("one", "1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.hasKey(x.substring(0, 4)), false);
    }

    /**
     * testing for a word in different cases.
     */
    @Test
    public final void testContainsCases() {
        /*
         * map up variables and call method under test
         */
        String x = "\"hi\", \"\1\"";
        Map<String, String> s = this.createFromArgsTest("Hi", "2");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.hasKey(x.substring(0, 3)), false);
    }

    //Size tests
    /** testing empty map. */
    @Test
    public final void testsizeOfEmptyMap() {
        /*
         * set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), 0);
    }

    /** testing 1-map. */
    @Test
    public final void testsizeOfOneElementMap() {
        /*
         * set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("one", "1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), 1);
    }

    /** testing 10-map. */
    @Test
    public final void testsizeOfLongerMap() {
        /*
         * set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("one", "1", "two", "2",
                "three", "3", "four", "4", "five", "5", "six", "6", "seven",
                "7", "eight", "8", "nine", "9", "ten", "10");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), 10);
    }
}
