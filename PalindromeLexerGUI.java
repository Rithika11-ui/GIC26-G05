// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.StringReader;

// public class PalindromeLexerGUI extends JFrame {
//     private JTextArea inputTextArea;
//     private JTextArea outputTextArea;
//     private JButton analyzeButton;
//     private JButton clearButton;

//     public PalindromeLexerGUI() {
//         initializeUI();
//     }

//     private void initializeUI() {
//         setTitle("Palindrome Lexer - Interactive Tool");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout(10, 10));

//         // Create main panel with padding
//         JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//         mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

//         // Input panel
//         JPanel inputPanel = createInputPanel();
//         // Output panel
//         JPanel outputPanel = createOutputPanel();
//         // Button panel
//         JPanel buttonPanel = createButtonPanel();

//         // Add panels to main panel
//         mainPanel.add(inputPanel, BorderLayout.NORTH);
//         mainPanel.add(outputPanel, BorderLayout.CENTER);
//         mainPanel.add(buttonPanel, BorderLayout.SOUTH);

//         add(mainPanel);
//         pack();
//         setSize(800, 600);
//         setLocationRelativeTo(null); // Center the window
//     }

//     private JPanel createInputPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBorder(BorderFactory.createTitledBorder("Input Text"));

//         inputTextArea = new JTextArea(8, 50);
//         inputTextArea.setLineWrap(true);
//         inputTextArea.setWrapStyleWord(true);
//         inputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

//         // Set some default text
//         inputTextArea.setText(
//                 "123 hello world\nracecar madam 12321\ntest@email.com\nA man a plan a canal Panama\nradar level civic\nrotator deed\n12321 456 palindrome\nabc123\nnoon moon");

//         JScrollPane scrollPane = new JScrollPane(inputTextArea);
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//         panel.add(scrollPane, BorderLayout.CENTER);
//         return panel;
//     }

//     private JPanel createOutputPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBorder(BorderFactory.createTitledBorder("Lexical Analysis Results"));

//         outputTextArea = new JTextArea(15, 50);
//         outputTextArea.setEditable(false);
//         outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//         outputTextArea.setBackground(new Color(245, 245, 245));

//         JScrollPane scrollPane = new JScrollPane(outputTextArea);
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//         panel.add(scrollPane, BorderLayout.CENTER);
//         return panel;
//     }

//     private JPanel createButtonPanel() {
//         JPanel panel = new JPanel(new FlowLayout());

//         analyzeButton = new JButton("Analyze Text");
//         analyzeButton.setFont(new Font("Arial", Font.BOLD, 14));
//         analyzeButton.setBackground(new Color(70, 130, 180));
//         // analyzeButton.setForeground(Color.WHITE);
//         analyzeButton.setFocusPainted(false);

//         clearButton = new JButton("Clear All");
//         clearButton.setFont(new Font("Arial", Font.PLAIN, 14));
//         clearButton.setBackground(new Color(220, 220, 220));
//         clearButton.setFocusPainted(false);

//         // Add action listeners
//         analyzeButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 analyzeText();
//             }
//         });

//         clearButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 clearAll();
//             }
//         });

//         // Add keyboard shortcuts
//         InputMap inputMap = analyzeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//         ActionMap actionMap = analyzeButton.getActionMap();

//         inputMap.put(KeyStroke.getKeyStroke("ctrl A"), "analyze");
//         actionMap.put("analyze", new AbstractAction() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 analyzeText();
//             }
//         });

//         panel.add(analyzeButton);
//         panel.add(clearButton);

//         return panel;
//     }

//     private void analyzeText() {
//         String inputText = inputTextArea.getText().trim();

//         if (inputText.isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "Please enter some text to analyze.",
//                     "No Input",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         try {
//             // Create a new lexer instance for the input text
//             PalindromeLexerUI lexer = new PalindromeLexerUI(new StringReader(inputText));
//             StringBuilder result = new StringBuilder();

//             String token;
//             while ((token = lexer.yylex()) != null) {
//                 result.append(token).append("\n");
//             }

//             outputTextArea.setText(result.toString());

//             // Show success message
//             JOptionPane.showMessageDialog(this,
//                     "Text analysis completed successfully!",
//                     "Analysis Complete",
//                     JOptionPane.INFORMATION_MESSAGE);

//         } catch (Exception e) {
//             outputTextArea.setText("Error during analysis: " + e.getMessage());
//             JOptionPane.showMessageDialog(this,
//                     "Error analyzing text: " + e.getMessage(),
//                     "Analysis Error",
//                     JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     private void clearAll() {
//         int result = JOptionPane.showConfirmDialog(this,
//                 "Are you sure you want to clear all text?",
//                 "Confirm Clear",
//                 JOptionPane.YES_NO_OPTION);

//         if (result == JOptionPane.YES_OPTION) {
//             inputTextArea.setText("");
//             outputTextArea.setText("");
//         }
//     }

//     public static void main(String[] args) {
//         // Set system look and feel
//         try {
//             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         // Create and show the UI
//         SwingUtilities.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                 new PalindromeLexerGUI().setVisible(true);
//             }
//         });

//     }
// }
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

public class PalindromeLexerGUI extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton analyzeButton;
    private JButton clearButton;
    private JCheckBox showDetailsCheckBox;

    public PalindromeLexerGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Advanced Palindrome Lexer - Interactive Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Input panel
        JPanel inputPanel = createInputPanel();
        // Output panel
        JPanel outputPanel = createOutputPanel();
        // Button panel
        JPanel buttonPanel = createButtonPanel();

        // Add panels to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setSize(900, 700);
        setLocationRelativeTo(null); // Center the window
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Input Text"));

        inputTextArea = new JTextArea(10, 60);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set enhanced test text with various palindrome types
        inputTextArea.setText(
                "123 hello world\n" +
                        "racecar madam 12321\n" +
                        "test@email.com\n" +
                        "A man a plan a canal Panama\n" +
                        "radar level civic\n" +
                        "rotator deed\n" +
                        "12321 456 palindrome\n" +
                        "abc123 1a1 a1a\n" +
                        "noon moon radar\n" +
                        "1234321 12344321\n" +
                        "a1b2b1a x1y1z\n" +
                        "123abc 456def\n" +
                        "111 222 333\n" +
                        "abcba 12321 1a1\n" +
                        "palindrome example text");

        JScrollPane scrollPane = new JScrollPane(inputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Lexical Analysis Results"));

        outputTextArea = new JTextArea(20, 60);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputTextArea.setBackground(new Color(250, 250, 250));
        outputTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        analyzeButton = createStyledButton("Analyze Text", new Color(70, 130, 180), Color.WHITE);
        clearButton = createStyledButton("Clear All", new Color(220, 100, 100), Color.WHITE);

        showDetailsCheckBox = new JCheckBox("Show Detailed Analysis");
        showDetailsCheckBox.setSelected(true);
        showDetailsCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add action listeners
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeText();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        panel.add(analyzeButton);
        panel.add(clearButton);
        panel.add(showDetailsCheckBox);

        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void analyzeText() {
        String inputText = inputTextArea.getText().trim();

        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter some text to analyze.",
                    "No Input",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Create a new lexer instance for the input text
            PalindromeLexerUI lexer = new PalindromeLexerUI(new StringReader(inputText));
            StringBuilder result = new StringBuilder();

            // Add header
            result.append("=== PALINDROME LEXER ANALYSIS RESULTS ===\n");
            result.append("Generated on: ").append(new java.util.Date()).append("\n");
            result.append("Input length: ").append(inputText.length()).append(" characters\n");
            result.append("=".repeat(50)).append("\n\n");

            String token;
            int tokenCount = 0;
            int palindromeCount = 0;

            while ((token = lexer.yylex()) != null) {
                result.append(token).append("\n");
                tokenCount++;
                if (token.contains("PALINDROME")) {
                    palindromeCount++;
                }
            }

            // Add summary
            result.append("\n").append("=".repeat(50)).append("\n");
            result.append("SUMMARY:\n");
            result.append("- Total tokens found: ").append(tokenCount).append("\n");
            result.append("- Palindromes detected: ").append(palindromeCount).append("\n");
            result.append("- Analysis completed successfully!\n");

            outputTextArea.setText(result.toString());

            // Show success message
            JOptionPane.showMessageDialog(this,
                    "Analysis complete!\nFound " + palindromeCount + " palindromes in " + tokenCount + " tokens.",
                    "Analysis Complete",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            outputTextArea.setText("Error during analysis: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error analyzing text: " + e.getMessage(),
                    "Analysis Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAll() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear all text?",
                "Confirm Clear",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            inputTextArea.setText("");
            outputTextArea.setText("");
        }
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PalindromeLexerGUI().setVisible(true);
            }
        });
    }
}




