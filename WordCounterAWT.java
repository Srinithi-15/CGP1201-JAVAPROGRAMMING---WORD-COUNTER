import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class AdvancedWordCounterAWT extends Frame {

    // Declare GUI components
    private TextArea inputArea;
    private Button analyzeButton;
    private Label resultLabel;
    private TextArea resultArea;

    public AdvancedWordCounterAWT() {
        // Set up the Frame
        setTitle("Advanced Word Counter");
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Create input area
        inputArea = new TextArea("Enter text here...", 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        add(inputArea, BorderLayout.NORTH);

        // Create analyze button
        analyzeButton = new Button("Analyze Text");
        add(analyzeButton, BorderLayout.CENTER);

        // Create result area
        Panel resultPanel = new Panel(new BorderLayout());
        resultLabel = new Label("Results:");
        resultArea = new TextArea("", 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        resultArea.setEditable(false);
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(resultArea, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Add action listener for the button
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeText();
            }
        });

        // Add window listener to close the application
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    // Method to analyze the text
    private void analyzeText() {
        String inputText = inputArea.getText();

        // Count lines
        int lineCount = inputText.split("\n").length;

        // Count words and frequencies
        Map<String, Integer> wordCounts = countWords(inputText);

        // Calculate total characters
        int charCount = inputText.length();

        // Prepare the result string
        StringBuilder result = new StringBuilder();
        result.append("Total Lines: ").append(lineCount).append("\n");
        result.append("Total Words: ").append(getTotalWordCount(wordCounts)).append("\n");
        result.append("Total Characters: ").append(charCount).append("\n\n");
        result.append("Word Frequencies:\n");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Display the result in the result area
        resultArea.setText(result.toString());
    }

    // Method to count words and their frequencies
    private Map<String, Integer> countWords(String inputText) {
        // Remove punctuation and convert to lowercase for case insensitivity
        inputText = inputText.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        // Split the text into words using spaces as delimiters
        String[] words = inputText.split("\\s+");

        // Create a HashMap to store the word count
        Map<String, Integer> wordCounts = new HashMap<>();

        // Loop through the array of words
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            // Increment the word count in the HashMap
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        return wordCounts;
    }

    // Method to get the total word count
    private int getTotalWordCount(Map<String, Integer> wordCounts) {
        int totalCount = 0;
        for (int count : wordCounts.values()) {
            totalCount += count;
        }
        return totalCount;
    }

    // Main method
    public static void main(String[] args) {
        new AdvancedWordCounterAWT();
    }
}
