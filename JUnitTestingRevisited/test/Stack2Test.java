import components.stack.Stack;
import components.stack.Stack2;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack2}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        return new Stack2<>();
    }

    @Override
    protected final Stack<String> constructorRef() {

        return new Stack3<>();
    }

}
