import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Nur Adem
 * @author Mohammed Maalin
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.add("green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue");
        m.add("blue");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAdd() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "green", "pink");
        m.add("green");
        m.add("blue");
        m.add("pink");
        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstOneEntry() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String temp = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(temp, "green");
    }

    @Test
    public final void testRemoveFirstWithMoreEntries() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "green", "black", "pink");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "green", "pink");
        String temp = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(temp, "black");
    }

    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(m.order(), ORDER);
    }

    @Test
    public final void testCheckReturnedValueRemoveFirst() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "pink", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "pink");
        m.changeToExtractionMode();
        String removed = m.removeFirst();
        assertEquals(removed, "blue");
    }

    @Test
    public final void testAddDuplicate() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green");
        m.add("green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testchangeToExtractionModeBoundary() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testchangeToExtractionModeSmallMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "gren");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "gren");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testchangeToExtractionModeEquality() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testchangeToExtractionModeRoutine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "a", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testchangeToExtractionModeCases() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "C", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "c", "C");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstBaseCase() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.removeFirst();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstNextSmallest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "c",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "c");
        m.removeFirst();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstEquality() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "c",
                "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "c");
        m.removeFirst();
        assertEquals(mExpected, m);
        System.out.println(m.size());
    }

    @Test
    public final void testIsInInsertionModeChange() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "c");
        m.changeToExtractionMode();
        assertFalse(m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeRoutine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "c");
        assertTrue(m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertTrue(m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        assertFalse(m.isInInsertionMode());
    }

    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "a");
        assertEquals(m.order(), ORDER);
    }

    @Test
    public final void testSizeInsertionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(m.size(), 0);
    }

    @Test
    public final void testSizeInsertionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green");
        m.changeToExtractionMode();
        assertEquals(m.size(), 2);
    }

    @Test
    public final void testSizeExtractionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        assertEquals(m.size(), 0);
    }

    @Test
    public final void testSizeExtractionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "green");
        assertEquals(m.size(), 2);
    }
}
