import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Mohammed Maalin
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public final void testConstructor() {
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();

        assertEquals(s, sExpected);
    }

    @Test
    public final void testPushBoundary() {

        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("a");
        s.push("a");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testPushRoutine() {

        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("b", "a");
        s.push("b");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testPushChallenge() {
        Stack<String> s = this.createFromArgsTest("a", "a");
        Stack<String> sExpected = this.createFromArgsRef("a", "a", "a");
        s.push("a");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testPopBoundary() {
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef();
        s.pop();

        assertEquals(s, sExpected);
    }

    @Test
    public final void testPopRoutine() {
        Stack<String> s = this.createFromArgsTest("b", "a");
        Stack<String> sExpected = this.createFromArgsRef("a");
        s.pop();

        assertEquals(s, sExpected);
    }

    @Test
    public final void testPopChallenge() {
        Stack<String> s = this.createFromArgsTest("a", "a");
        Stack<String> sExpected = this.createFromArgsRef("a");
        s.pop();

        assertEquals(s, sExpected);
    }

    @Test
    public final void testLengthBoundary() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef();

        assertEquals(s.length(), sExpected.length());
    }

    @Test
    public final void testLengthRoutine() {
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("a");

        assertEquals(s.length(), sExpected.length());
    }

    @Test
    public final void testLengthChallenge() {
        Stack<String> s = this.createFromArgsTest("a", "a");
        Stack<String> sExpected = this.createFromArgsRef("a", "a");

        assertEquals(s.length(), sExpected.length());
    }

    @Test
    public final void testTopBoundary() {
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("a");

        assertEquals(s.top(), sExpected.top());
    }

    @Test
    public final void testTopRoutine() {
        Stack<String> s = this.createFromArgsTest("a", "b");
        Stack<String> sExpected = this.createFromArgsRef("a", "b");

        assertEquals(s.top(), sExpected.top());
        assertEquals(s.top(), "a");
    }

    @Test
    public final void testTopReplaceBoundary() {
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("b");
        s.replaceTop("b");
        assertEquals(s, sExpected);

    }

    @Test
    public final void testTopReplaceRoutine() {
        Stack<String> s = this.createFromArgsTest("a", "b");
        Stack<String> sExpected = this.createFromArgsRef("c", "b");
        s.replaceTop("c");
        assertEquals(s, sExpected);

    }

    @Test
    public final void testTopReplaceChallenge() {
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("a");
        s.replaceTop("a");
        assertEquals(s, sExpected);
    }
}
