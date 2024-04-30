import components.set.Set;
import components.set.Set1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Mohammed Maalin
 * @author Nur Adem
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */
                int blockLength = s.lengthOfBlock();
                for (int i = 0; i < blockLength; i++) {
                    Statement subtree = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(subtree);
                    s.addToBlock(i, subtree);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement sIf = s.newInstance();
                Condition ifcond = s.disassembleIf(sIf);

                count += countOfPrimitiveCalls(sIf);

                s.assembleIf(ifcond, sIf);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition ifcond = s.disassembleIfElse(s1, s2);

                count += countOfPrimitiveCalls(s1) + countOfPrimitiveCalls(s2);

                s.assembleIfElse(ifcond, s1, s2);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement sWhile = s.newInstance();
                Condition cond = s.disassembleWhile(sWhile);

                count += countOfPrimitiveCalls(sWhile);

                s.assembleWhile(cond, sWhile);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String inst = s.disassembleCall();
                Set<String> primitives = new Set1L<>();
                primitives.add("turnright");
                primitives.add("turnleft");
                primitives.add("move");
                primitives.add("infect");
                primitives.add("skip");
                if (primitives.contains(inst)) {
                    count = 1;
                }

                s.assembleCall(inst);

                break;
            }
            default: {
                // this will never happen...can you explain why? Yes
                break;
            }
        }
        return count;
    }

}
