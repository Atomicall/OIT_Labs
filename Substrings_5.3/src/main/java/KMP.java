import java.util.ArrayDeque;
import java.util.Queue;

public class KMP {
    protected String pattern;
    protected int[][] dfa;  // deterministic-finite-automaton

    public KMP(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Invalid pattern");
        }
        // Build DFA from pattern
        this.pattern = pattern;
        int patternLength = pattern.length();
        int alphabetSize = 256;
        dfa = new int[alphabetSize][patternLength];
        dfa[pattern.charAt(0)][0] = 1;
        int restartState = 0;
        for (int patternIndex = 1; patternIndex < patternLength; patternIndex++) {
            // Compute dfa[][patternIndex]
            for (int currentChar = 0; currentChar < alphabetSize; currentChar++) {
                dfa[currentChar][patternIndex] = dfa[currentChar][restartState]; // Copy mismatch cases
            }
            dfa[pattern.charAt(patternIndex)][patternIndex] = patternIndex + 1;  // Set match case
            restartState = dfa[pattern.charAt(patternIndex)][restartState];      // Update restart state
        }
    }

    // Search for pattern in text.
    // Returns the index of the first occurrence of the pattern string in the text string or textLength if no such match.
    public int search(String text) {
        int textIndex;
        int patternIndex;
        int textLength = text.length();
        int patternLength = pattern.length();
        for (textIndex = 0, patternIndex = 0; textIndex < textLength && patternIndex < patternLength; textIndex++) {
            patternIndex = dfa[text.charAt(textIndex)][patternIndex];
        }
        if (patternIndex == patternLength) {
            return textIndex - patternLength; // found
        } else {
            return textLength;                // not found
        }
    }

    // Count the occurrences of pattern in the text
    public int count(String text) {
        int count = 0;
        int occurrenceIndex = searchFromIndex(text, 0);
        while (occurrenceIndex != text.length()) {
            count++;
            occurrenceIndex = searchFromIndex(text, occurrenceIndex + 1);
        }
        return count;
    }

    // Finds all the occurrences of pattern in the text
    public Iterable<Integer> findAll(String text) {
        Queue<Integer> offsets = new ArrayDeque<>();
        int occurrenceIndex = searchFromIndex(text, 0);
        while (occurrenceIndex != text.length()) {
            offsets.add(occurrenceIndex);
            occurrenceIndex = searchFromIndex(text, occurrenceIndex + 1);
        }
        return offsets;
    }

    // Searches for the pattern in the text starting at specified index.
    // Most of the code is copied from search() method instead of making search() call this method with textStartIndex 0
    // in order to keep the methods separated for educational purposes.
    protected int searchFromIndex(String text, int textStartIndex) {
        int textIndex;
        int patternIndex;
        int textLength = text.length();
        int patternLength = pattern.length();
        for (textIndex = textStartIndex, patternIndex = 0; textIndex < textLength && patternIndex < patternLength; textIndex++) {
            patternIndex = dfa[text.charAt(textIndex)][patternIndex];
        }
        if (patternIndex == patternLength) {
            return textIndex - patternLength; // found
        } else {
            return textLength;                // not found
        }
    }
}