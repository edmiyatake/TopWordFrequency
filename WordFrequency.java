import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordFrequency  {
    public static void main(String[] args) {
        // param check.
        if (args.length < 2) {
            System.err.println("Usage: java WordFrequency <text_file> <stopwords_file> [number_of_top_words]");
            System.exit(1);
        }

        String textFilePath = args[0];
        String stopwordsFilePath = args[1];
        int N = 25; // default value

        if (args.length >= 3) {
            try {
                N = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number provided for top words. Using default value of 25.");
            }
        }

        Set<String> stopWords = new HashSet<>();
        try {
            List<String> stopWordsLines = Files.readAllLines(Paths.get(stopwordsFilePath));
            for (String line : stopWordsLines) {
                // Split the line into individual words by comma
                String[] parts = line.split(",");
                for (String part : parts) {
                    String word = part.trim().toLowerCase();
                    if (!word.isEmpty()) {
                        stopWords.add(word);
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error reading stopwords file: " + e.getMessage());
            System.exit(1);
        }

        // for debugging
        System.out.println("Loaded stopwords: " + stopWords);

        try {
            // Read the entire text from the input file (assumes UTF-8 encoding)
            String content = new String(Files.readAllBytes(Paths.get(textFilePath)));
            // Normalize the content by converting it to lowercase
            content = content.toLowerCase();

            // just like in Leetcode problems, word frequency is prob easiest with a hashmap
            Map<String, Integer> frequencyMap = new HashMap<>();

            String[] words = content.split("[^a-z]+");

            for (String word : words) {
                // Skip any empty strings (which might be produced by the split).
                if (stopWords.contains(word)) {
                    //System.out.println("Skipping stop word: [" + word + "]");
                    continue;
                }
//                else {
//                    //System.out.println("Processing word: [" + word + "]");
//                }
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }

            // convert into list because it needs to be sorted
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());


            entries.sort((e1, e2) -> {
                int cmp = Integer.compare(e2.getValue(), e1.getValue());
                return (cmp != 0) ? cmp : e1.getKey().compareTo(e2.getKey());
            });

            // Display the top N words along with their frequencies.
            System.out.println("Top " + N + " most frequent words:");
            System.out.println("Word\tFrequency");
            System.out.println("---------------------");

            int count = 0;
            for (Map.Entry<String, Integer> entry : entries) {
                if (count++ >= N) {
                    break;
                }
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }

        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }
}