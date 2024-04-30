import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    //test case for no-argument constructor
    @Test
    public final void testNoArgumentConstructor() {

        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    //test cases for int constructor
    @Test
    public final void testIntConstructor() {

        NaturalNumber s = this.constructorTest(1);
        NaturalNumber sExpected = this.constructorRef(1);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testIntConstructorEdge() {

        NaturalNumber s = this.constructorTest(0);
        NaturalNumber sExpected = this.constructorRef(0);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testIntConstructorSecondEdge() {

        NaturalNumber s = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber sExpected = this.constructorRef(Integer.MAX_VALUE);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testIntConstructorRoutine() {

        NaturalNumber s = this.constructorTest(534);
        NaturalNumber sExpected = this.constructorRef(534);

        assertEquals(sExpected, s);
    }

    //test cases for string constructor

    @Test
    public final void testStringConstructorZero() {

        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testStringConstructorRoutine() {

        NaturalNumber s = this.constructorTest("384");
        NaturalNumber sExpected = this.constructorRef("384");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testStringConstructorIntMaxToString() {

        NaturalNumber s = this
                .constructorTest(String.valueOf(Integer.MAX_VALUE));
        NaturalNumber sExpected = this
                .constructorRef(String.valueOf(Integer.MAX_VALUE));

        assertEquals(sExpected, s);
    }

    //test cases for the naturalnumber constructor
    @Test
    public final void testNNConstructorEdge() {
        NaturalNumber toConstruct = this.constructorTest(0);
        NaturalNumber s = this.constructorTest(toConstruct);
        NaturalNumber sExpected = this.constructorRef(0);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNNConstructorRoutine() {
        NaturalNumber toConstruct = this.constructorTest(384589);
        NaturalNumber s = this.constructorTest(toConstruct);
        NaturalNumber sExpected = this.constructorRef(384589);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNNConstructorChallenge() {
        NaturalNumber toConstruct = this
                .constructorTest(String.valueOf(Integer.MAX_VALUE)
                        + String.valueOf(Integer.MAX_VALUE));
        NaturalNumber s = this.constructorTest(toConstruct);
        NaturalNumber sExpected = this
                .constructorRef(String.valueOf(Integer.MAX_VALUE)
                        + String.valueOf(Integer.MAX_VALUE));

        assertEquals(sExpected, s);
    }

    //test cases for multiplyBy10

    @Test
    public final void testMultiplyBy10Zero() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorTest(1);

        n.multiplyBy10(1);

        assertEquals(n, nExpected);
    }

    @Test
    public final void testMultiplyBy10IntMax() {
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this
                .constructorTest(String.valueOf(Integer.MAX_VALUE) + "1");

        n.multiplyBy10(1);

        assertEquals(n, nExpected);
    }

    @Test
    public final void testMultiplyBy10Routine() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorTest(50);

        n.multiplyBy10(0);

        assertEquals(n, nExpected);
    }

    //test cases for divideBy10
    /** Boundary case. */
    @Test
    public final void testDivideBy10Edge() {
        NaturalNumber n = this.constructorTest(3);
        NaturalNumber nExpected = this.constructorTest(0);

        n.divideBy10();

        assertEquals(n, nExpected);
    }

    /** Routine case. */
    @Test
    public final void testDivideBy10Routine() {

        NaturalNumber n = this.constructorTest(50);
        NaturalNumber nExpected = this.constructorTest(5);

        n.divideBy10();

        assertEquals(n, nExpected);
    }

    /** Edge case. */
    @Test
    public final void testDivideBy10Challenge() {

        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorTest(0);

        n.divideBy10();

        assertEquals(n, nExpected);
    }

    //test cases for isZero
    /** Boundary case. */
    @Test
    public final void testIsZeroZero() {

        NaturalNumber n = this.constructorTest(0);

        assertEquals(n.isZero(), true);
    }

    /** Routine case. */
    @Test
    public final void testIsZeroRoutine() {

        NaturalNumber n = this.constructorTest("347");

        assertEquals(n.isZero(), false);
    }

    /** Cannot test for leading 0s but can test zeros after leading digit. */
    @Test
    public final void testIsZeroChallenge() {

        NaturalNumber n = this.constructorTest("500300000");

        assertEquals(n.isZero(), false);
    }

}
