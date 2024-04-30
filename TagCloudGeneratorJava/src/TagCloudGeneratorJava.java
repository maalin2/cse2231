import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Generates a tag cloud only using Java components.
 *
 * @author Mohammed Maalin
 * @author Nur Adem
 *
 */
public final class TagCloudGeneratorJava {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TagCloudGeneratorJava() {
    }

    /**
     * Compares entries by value.
     */
    private static final class EntryValueGT
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    }

    /**
     * Characters to separate from words.
     */
    static final String SEPARATORS = " \t\n\r,-.!?[]';:/()";

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code SEPARATORS}) or "separator string" (maximal length string of
     * characters in {@code SEPARATORS}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection entries(SEPARATORS) = {}
     * then
     *   entries(nextWordOrSeparator) intersection entries(SEPARATORS) = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection entries(SEPARATORS) /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of entries(SEPARATORS)  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of entries(SEPARATORS))
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int end = position;
        boolean isSeparator = SEPARATORS.indexOf(text.charAt(position)) != -1;
        while ((end < text.length()) && (isSeparator == (SEPARATORS
                .indexOf(text.charAt(end)) != -1))) {
            end++;
        }
        return text.substring(position, end);
    }

    /**
     * Gets words from a file.
     *
     * @param fileStream
     *            path of file to read
     * @param q
     *            queue to add words to
     */
    private static void getWords(BufferedReader fileStream, Queue<String> q) {
        String s = "";
        try {
            s = fileStream.readLine();
        } catch (IOException e) {
            System.err.println("Problem reading input file");
            return;
        }
        while (s != null) {
            String word = "";
            int position = 0;
            while (position < s.length()) {
                word = nextWordOrSeparator(s, position);
                position += word.length();

                if (!word.matches("[" + Pattern.quote(SEPARATORS) + "]+")) {
                    q.add(word.toLowerCase());
                }
            }

            try {
                s = fileStream.readLine();
            } catch (IOException e) {
                System.err.println("Problem reading input file");
                return;
            }
        }
    }

    /**
     * Count number of words.
     *
     * @param words
     *            words to count
     * @return a map with words and their frequency
     */
    private static Map<String, Integer> countWords(Queue<String> words) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (map.containsKey(word)) {
                int count = map.get(word);
                map.replace(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }

        return map;
    }

    /**
     * Get a set of top N words.
     *
     * @param wordFreq
     *            words and their frequency
     * @param n
     *            how many words to find
     * @return a set with top N words
     */
    private static Set<String> findTopNWords(Map<String, Integer> wordFreq,
            int n) {
        Comparator<Map.Entry<String, Integer>> c = new EntryValueGT();

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(
                wordFreq.entrySet());
        Collections.sort(entries, c);

        LinkedHashMap<String, Integer> sortedFreq = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            Map.Entry<String, Integer> entry = entries.remove(0);
            sortedFreq.put(entry.getKey(), entry.getValue());
        }

        Set<String> set = sortedFreq.keySet();
        return set;
    }

    /**
     * Sorts the top n words.
     *
     * @param topNWords
     *            words to sort alphabetically
     * @return the top n words sorted alphabetically
     */
    private static Set<String> alphabetizeWords(Set<String> topNWords) {
        Set<String> set = new TreeSet<>();

        List<String> list = new ArrayList<>(topNWords);
        Collections.sort(list);

        for (String word : list) {
            set.add(word);
        }

        return set;
    }

    /**
     * Calculate the font size words should appear in the tag cloud.
     *
     * @param sortedTopNWords
     *            alphabetically sorted top N words
     * @param wordFreq
     *            the frequencies of all the words
     * @return a map with the sorted words and their font sizes
     */
    private static Map<String, Integer> fontSize(Set<String> sortedTopNWords,
            Map<String, Integer> wordFreq) {
        Map<String, Integer> map = new LinkedHashMap<>();

        Map<String, Integer> topNWordFreq = new LinkedHashMap<>();
        for (String word : sortedTopNWords) {
            topNWordFreq.put(word, wordFreq.get(word));
        }
        List<Integer> freqList = new ArrayList<>(topNWordFreq.values());

        int fontSize = 0;

        final int fMax = 48;
        final int fMin = 11;
        int scale = fMax - fMin;

        int minCount = Collections.min(freqList);
        int maxCount = Collections.max(freqList);
        int count = 0;

        for (Map.Entry<String, Integer> e : topNWordFreq.entrySet()) {
            count = e.getValue();
            fontSize = fMin
                    + ((scale * (count - minCount)) / (maxCount - minCount));
            map.put(e.getKey(), fontSize);
        }

        return map;
    }

    /**
     * Generate first few lines up to the tag cloud div.
     *
     * @param htmlStream
     *            file to print HTML
     * @param inputPath
     *            input file path
     * @param n
     *            number of words in tag cloud
     */
    private static void generateHTMLHeaders(PrintWriter htmlStream,
            String inputPath, int n) {
        htmlStream.println("<html>");
        htmlStream.println("<head>");
        htmlStream.println(
                "<title>Top " + n + " words in " + inputPath + "</title>");
        htmlStream
                .println("<link href=\"http://web.cse.ohio-state.edu/software/"
                        + "2231/web-sw2/assignments/projects/"
                        + "tag-cloud-generator/data/tagcloud.css\""
                        + " rel=\"stylesheet\" type=\"text/css\">");
        htmlStream.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        htmlStream.println("</head>");
        htmlStream.println("<body>");
        htmlStream.println("<h2>Top " + n + " words in " + inputPath + "</h2>");
        htmlStream.println("<hr>");
    }

    /**
     * Print the tag cloud.
     *
     * @param htmlStream
     *            HTML file
     * @param fontSizes
     *            words and calculated font sizes
     * @param wordFreq
     *            frequency of words
     * @param sortedTopNWords
     *            top n words
     */
    private static void outputTagCloud(PrintWriter htmlStream,
            Map<String, Integer> fontSizes, Map<String, Integer> wordFreq,
            Set<String> sortedTopNWords) {
        htmlStream.println("<div class=\"cdiv\">");
        htmlStream.println("<p class=\"cbox\">");

        for (String s : sortedTopNWords) {
            htmlStream.println("<span style=\"cursor:default\" class=\"f"
                    + fontSizes.get(s) + "\" title=\"count: " + wordFreq.get(s)
                    + "\">" + s + "</span>");
        }

        htmlStream.println("</p>");
        htmlStream.println("</div>");
    }

    /**
     * Print the HTML footers after the div.
     *
     * @param htmlStream
     *            HTML file
     */
    private static void generateHTMLFooters(PrintWriter htmlStream) {
        htmlStream.println("</body>");
        htmlStream.println("</html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        String inputPath = "";
        System.out.print("Give valid input file ");
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            inputPath = input.readLine();
        } catch (

        IOException e) {
            System.err.println("Error getting name of input file");
            return;
        }

        String outputPath = "";
        System.out.print("Give valid output file ");
        try {
            outputPath = input.readLine();
        } catch (IOException e) {
            System.err.println("Error getting name of output file");
            return;
        }

        int n = 0;
        String nString = "";
        System.out.print("Give number of words in tag cloud ");
        try {
            nString = input.readLine();
        } catch (IOException e) {
            System.err.println("Error getting number of words in tag cloud");
            return;
        }
        n = Integer.parseInt(nString);

        try {
            input.close();
        } catch (IOException e) {
            System.err.println("problem closing sys input");
        }

        BufferedReader fileStream;
        Queue<String> words = new PriorityQueue<>();
        try {
            fileStream = new BufferedReader(new FileReader(inputPath));
            getWords(fileStream, words);

        } catch (IOException e) {
            System.err.println("Problem opening input file");
            return;
        }

        try {
            fileStream.close();
        } catch (IOException e) {
            System.err.println("Problem closing input file");
        }

        Map<String, Integer> wordFreq = countWords(words);
        Set<String> topNWords = findTopNWords(wordFreq, n);
        Set<String> sortedTopNWords = alphabetizeWords(topNWords);
        Map<String, Integer> fontSizes = fontSize(sortedTopNWords, wordFreq);

        PrintWriter htmlStream;
        try {
            htmlStream = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputPath)));
        } catch (IOException e) {
            System.err.println("Error opening output file");
            return;
        }
        generateHTMLHeaders(htmlStream, inputPath, n);
        outputTagCloud(htmlStream, fontSizes, wordFreq, sortedTopNWords);
        generateHTMLFooters(htmlStream);

        htmlStream.close();
    }
}
