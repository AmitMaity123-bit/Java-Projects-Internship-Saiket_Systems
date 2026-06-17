import java.io.*;
import java.util.*;

public class TextFileAnalyzer {

    // ─────────────────────────────────────────────
    //  Statistics Container
    // ─────────────────────────────────────────────

    static class FileStats {
        String  filePath;
        int     lines;
        int     words;
        int     charsWithSpaces;
        int     charsWithoutSpaces;
        int     sentences;
        int     paragraphs;
        int     longestLineLength;
        String  longestLine;
        Map<String, Integer> wordFrequency = new LinkedHashMap<>();

        double avgWordLength() {
            return words == 0 ? 0 : (double) charsWithoutSpaces / words;
        }

        String readingLevel() {
            double awl = avgWordLength();
            if (awl < 4.0) return "Elementary";
            if (awl < 5.0) return "Middle School";
            if (awl < 6.0) return "High School";
            if (awl < 7.0) return "College";
            return "Academic / Technical";
        }
    }

    // ─────────────────────────────────────────────
    //  Core Analysis
    // ─────────────────────────────────────────────

    public static FileStats analyzeFile(String path) throws IOException {
        FileStats stats = new FileStats();
        stats.filePath = path;

        Map<String, Integer> freq = new HashMap<>();
        int blankLineStreak = 0;
        boolean inParagraph = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                stats.lines++;
                stats.charsWithSpaces += line.length() + 1; // +1 for newline

                // Paragraph detection (blank line separates paragraphs)
                if (line.trim().isEmpty()) {
                    if (inParagraph) {
                        stats.paragraphs++;
                        inParagraph = false;
                    }
                } else {
                    inParagraph = true;
                }

                // Longest line
                if (line.length() > stats.longestLineLength) {
                    stats.longestLineLength = line.length();
                    stats.longestLine = line;
                }

                // Sentence count (ends with . ! ?)
                for (char c : line.toCharArray()) {
                    if (c == '.' || c == '!' || c == '?') stats.sentences++;
                }

                // Words & frequency
                String[] tokens = line.trim().split("\\s+");
                for (String token : tokens) {
                    if (token.isEmpty()) continue;
                    // Strip leading/trailing punctuation
                    String word = token.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");
                    if (word.isEmpty()) continue;
                    stats.words++;
                    stats.charsWithoutSpaces += word.length();
                    String key = word.toLowerCase();
                    freq.put(key, freq.getOrDefault(key, 0) + 1);
                }
            }
            if (inParagraph) stats.paragraphs++; // last paragraph with no trailing blank
        }

        // Sort by frequency descending, then alphabetically
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> {
            int cmp = b.getValue().compareTo(a.getValue());
            return cmp != 0 ? cmp : a.getKey().compareTo(b.getKey());
        });
        for (Map.Entry<String, Integer> e : entries) {
            stats.wordFrequency.put(e.getKey(), e.getValue());
        }

        return stats;
    }

    // ─────────────────────────────────────────────
    //  Search
    // ─────────────────────────────────────────────

    public static List<int[]> searchWord(String path, String query) throws IOException {
        // Returns list of {lineNumber, occurrencesOnLine}
        List<int[]> results = new ArrayList<>();
        String lower = query.toLowerCase();
        int lineNum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNum++;
                String lowerLine = line.toLowerCase();
                int count = 0;
                int idx = 0;
                while ((idx = lowerLine.indexOf(lower, idx)) != -1) {
                    count++;
                    idx += lower.length();
                }
                if (count > 0) results.add(new int[]{lineNum, count});
            }
        }
        return results;
    }

    // ─────────────────────────────────────────────
    //  Display
    // ─────────────────────────────────────────────

    private static void printBanner() {
        System.out.println("==============================================");
        System.out.println("|         Amit Maity - Text File Analyzer     |");
        System.out.println("==============================================");
    }

    private static void printStats(FileStats s) {
        System.out.println("\n  --------------------------------------------");
        System.out.println("  |              FILE ANALYSIS               |");
        System.out.println("  --------------------------------------------");
        System.out.printf ("  |  File        : %-27s|%n", truncate(s.filePath, 27));
        System.out.println("  --------------------------------------------");
        System.out.printf ("  |  Lines       : %-10d                |%n", s.lines);
        System.out.printf ("  |  Words       : %-10d                |%n", s.words);
        System.out.printf ("  |  Sentences   : %-10d                |%n", s.sentences);
        System.out.printf ("  |  Paragraphs  : %-10d                |%n", s.paragraphs);
        System.out.println("  --------------------------------------------");
        System.out.printf ("  |  Chars (w/ spaces)   : %-17d|%n", s.charsWithSpaces);
        System.out.printf ("  |  Chars (w/o spaces)  : %-17d|%n", s.charsWithoutSpaces);
        System.out.printf ("  |  Avg word length     : %-17.2f|%n", s.avgWordLength());
        System.out.printf ("  |  Longest line (chars): %-17d|%n", s.longestLineLength);
        System.out.println("  --------------------------------------------");
        System.out.printf ("  |  Reading Level: %-26s|%n", s.readingLevel());
        System.out.println("  --------------------------------------------");

        // Top 10 words
        System.out.println("\n  -- Top 10 Most Frequent Words --------------");
        int rank = 1;
        for (Map.Entry<String, Integer> e : s.wordFrequency.entrySet()) {
            if (rank > 10) break;
            System.out.printf("  %2d. %-20s %d occurrence(s)%n",
                rank++, e.getKey(), e.getValue());
        }

        if (s.longestLine != null) {
            System.out.println("\n  -- Longest Line ---------------------------------");
            System.out.println("  " + truncate(s.longestLine, 70));
        }
    }

    private static String truncate(String s, int max) {
        return s.length() <= max ? s : s.substring(0, max - 3) + "...";
    }

    // ─────────────────────────────────────────────
    //  Create Sample File Helper
    // ─────────────────────────────────────────────

    // private static void createSampleFile(String path) throws IOException {
    //     String sample =
    //         "Java is a high-level, class-based, object-oriented programming language.\n" +
    //         "It was designed to have as few implementation dependencies as possible.\n\n" +
    //         "Java is a general-purpose programming language intended to let programmers\n" +
    //         "write once, run anywhere (WORA). Compiled Java code can run on all platforms\n" +
    //         "that support Java without the need for recompilation.\n\n" +
    //         "Java applications are typically compiled to bytecode that can run on any\n" +
    //         "Java virtual machine (JVM) regardless of the underlying computer architecture.\n\n" +
    //         "The syntax of Java is similar to C and C++, but has fewer low-level facilities\n" +
    //         "than either of them. Java is one of the most popular programming languages in use.\n\n" +
    //         "SaiKet Systems teaches Java programming through hands-on projects and tasks!\n" +
    //         "Practice makes perfect. Keep coding, keep learning, keep growing every day.";
    //     try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
    //         bw.write(sample);
    //     }
    //     System.out.println("  [INFO] Sample file created: " + path);
    // }

    // ─────────────────────────────────────────────
    //  Main
    // ─────────────────────────────────────────────

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printBanner();

        boolean running = true;
        while (running) {
            System.out.println("\n  -- Main Menu -----------------------------");
            System.out.println("  1. Analyze a text file");
            System.out.println("  2. Search word in file");
            System.out.println("  3. Create & analyze sample file");
            System.out.println("  0. Exit");
            System.out.print("  Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": {
                    System.out.print("  Enter file path: ");
                    String path = sc.nextLine().trim();
                    try {
                        FileStats stats = analyzeFile(path);
                        printStats(stats);
                    } catch (FileNotFoundException e) {
                        System.out.println("  [ERROR] File not found: " + path);
                    } catch (IOException e) {
                        System.out.println("  [ERROR] Could not read file: " + e.getMessage());
                    }
                    break;
                }
                case "2": {
                    System.out.print("  Enter file path  : ");
                    String path = sc.nextLine().trim();
                    System.out.print("  Enter search word: ");
                    String query = sc.nextLine().trim();
                    try {
                        List<int[]> results = searchWord(path, query);
                        int total = results.stream().mapToInt(r -> r[1]).sum();
                        System.out.printf("%n  Found \"%s\" %d time(s) across %d line(s):%n",
                            query, total, results.size());
                        for (int[] r : results) {
                            System.out.printf("    Line %4d -> %d occurrence(s)%n", r[0], r[1]);
                        }
                        if (results.isEmpty()) System.out.println("  (Word not found)");
                    } catch (IOException e) {
                        System.out.println("  [ERROR] " + e.getMessage());
                    }
                    break;
                }
                case "3": {
                    //String path = "sample.txt";
                    System.out.print("  Enter file path  : ");
                    String path = sc.nextLine().trim();
                    try {
                        //createSampleFile(path);
                        FileStats stats = analyzeFile(path);
                        printStats(stats);
                    } catch (IOException e) {
                        System.out.println("  [ERROR] " + e.getMessage());
                    }
                    break;
                }
                case "0":
                    System.out.println("\n  Goodbye from SaiKet Text Analyzer!");
                    running = false;
                    break;
                default:
                    System.out.println("  [ERROR] Invalid choice.");
            }
        }
        sc.close();
    }
}