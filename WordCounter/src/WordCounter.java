import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Reads a text file and generates an HTML table with words sorted
 * alphabetically and number of appearances in the text.
 *
 * @author Mohammed Maalin
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int end = position;
        boolean isSeparator = separators.contains(text.charAt(position));
        while (end < text.length()
                && isSeparator == separators.contains(text.charAt(end))) {
            end++;
        }
        return text.substring(position, end);

    }

    /**
     * Reads text file and generates a queue with all words present, including
     * duplicate entries.
     *
     * @param txtPath
     *            path to a text file
     * @return a queue with all the words found in the text
     *
     * @ensures generateQueueFromText(txtPath) = [every single word found in the
     *          text file excluding separators (These could be tabs, spaces,
     *          commas, newlines, periods and dashes)]
     */
    public static Queue<String> generateQueueFromText(String txtPath) {
        SimpleReader txt = new SimpleReader1L(txtPath);

        Queue<String> words = new Queue1L<>();

        Set<Character> separators = new Set1L<>();
        separators.add('\t');
        separators.add(' ');
        separators.add(',');
        separators.add('\n');
        separators.add('.');
        separators.add('-');

        String line = "";
        while (!txt.atEOS()) {
            line = txt.nextLine();

            int position = 0;
            String nextWord = "";
            while (position < line.length()) {
                nextWord = nextWordOrSeparator(line, position, separators);
                position += nextWord.length();

                if (!separators.contains(nextWord.charAt(0))) {
                    words.enqueue(nextWord);
                }
            }
        }
        txt.close();

        return words;
    }

    /**
     * Creates a map of all of the words in the text and number of occurrences,
     * uses that map to remove duplicates, and then sorts queue alphabetically.
     *
     * @param wordQueue
     *            queue of words generated from generateQueueFromText to be
     *            sorted
     * @return map with words and counts
     * @updates wordQueue
     * @ensures generateMapAndSortQueue(wordQueue) = [every word in the queue
     *          and the number of times it occurs in the original queue] &&
     *          wordQueue is sorted
     */
    public static Map<String, Integer> generateMapAndSortQueue(
            Queue<String> wordQueue) {
        Map<String, Integer> wordMap = new Map1L<>();

        for (String word : wordQueue) {
            if (wordMap.hasKey(word)) {
                wordMap.replaceValue(word, wordMap.value(word) + 1);
            } else {
                wordMap.add(word, 1);
            }
        }

        Queue<String> sortedWordQueue = new Queue1L<>();
        for (Map.Pair<String, Integer> p : wordMap) {
            sortedWordQueue.enqueue(p.key());
        }
        sortedWordQueue.sort(String.CASE_INSENSITIVE_ORDER);
        wordQueue.transferFrom(sortedWordQueue);

        return wordMap;

    }

    /**
     *
     * Generates the code for the page featuring a title and alphabetical table
     * of words in the original text and number of occurrences.
     *
     * @updates html
     * @param html
     *            stream of the html webpage
     * @param txtPath
     *            path to text file
     * @param wordMap
     *            map of words and number of occurrences (not sorted)
     * @param wordQueue
     *            every word in the text file, excluding duplicates and sorted
     *            alphabetically
     * @requires html.isOpen
     * @ensures html = [a correct, alphabetically sorted webpage with a table of
     *          words and counts]
     */
    public static void generateTableWebPage(SimpleWriter html, String txtPath,
            Map<String, Integer> wordMap, Queue<String> wordQueue) {
        html.println("<html>" + "\n<head>");
        html.println("<title>Words Counted in " + txtPath + "</title>");
        html.println("</head>" + "\n<body>");
        html.println("<h2>Words Counted in " + txtPath + "</h2>" + "<hr />");
        html.println("<table border=\"1\">");
        html.println("<tr>");
        html.println("<th>Words</th>");
        html.println("<th>Counts</th>");

        for (String word : wordQueue) {
            html.println("<tr>\n" + "<td>" + word + "</td>\n" + "<td>"
                    + wordMap.value(word) + "</tr>");
        }
        html.println("</tr>");
        html.println("</body>" + "\n</html>");
    }

    /**
     *
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.println("Enter the filepath of a input text file: ");
        String txtPath = in.nextLine();

        out.println("Enter the filepath of an output HTML file: ");
        String htmlPath = in.nextLine();
        SimpleWriter html = new SimpleWriter1L(htmlPath);

        Queue<String> words = generateQueueFromText(txtPath);
        Map<String, Integer> countedWords = generateMapAndSortQueue(words);
        generateTableWebPage(html, txtPath, countedWords, words);

        html.close();
        out.close();
        in.close();
    }

}
