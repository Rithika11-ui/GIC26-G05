# #!/bin/bash

# echo "=== Building Palindrome Lexer UI ==="

# # Remove any previously generated files
# rm -f PalindromeLexerUI.java *.class

# echo "1. Generating lexer from JFlex file..."
# jflex PalindromeLexerUI.jflex

# if [ -f "PalindromeLexerUI.java" ]; then
#     echo "✓ Successfully generated PalindromeLexerUI.java"
    
#     echo "2. Compiling Java files..."
#     javac *.java
    
#     if [ -f "PalindromeLexerGUI.class" ]; then
#         echo "✓ Successfully compiled all classes"
#         echo ""
#         echo "=== Build Successful! ==="
#         echo "To run the UI: java PalindromeLexerGUI"
#     else
#         echo "✗ Compilation failed"
#         echo "Checking what files exist:"
#         ls -la *.class
#     fi
# else
#     echo "✗ JFlex failed to generate Java file"
# fi

#!/bin/bash

echo "=== Building Advanced Palindrome Lexer UI ==="

# Remove any previously generated files
rm -f PalindromeLexerUI.java *.class

echo "1. Generating lexer from JFlex file..."
jflex PalindromeLexerUI.jflex

if [ -f "PalindromeLexerUI.java" ]; then
    echo "✓ Successfully generated PalindromeLexerUI.java"
    
    echo "2. Compiling Java files..."
    javac *.java
    
    if [ -f "PalindromeLexerGUI.class" ]; then
        echo "✓ Successfully compiled all classes"
        echo ""
        echo "=== Build Successful! ==="
        echo "Enhanced Features:"
        echo "- Word palindromes (racecar, madam)"
        echo "- Numeric palindromes (12321, 1234321)" 
        echo "- Alphanumeric palindromes (a1a, 1a1, a1b2b1a)"
        echo "- Regular words and numbers"
        echo "- Email detection"
        echo ""
        echo "To run the UI: java PalindromeLexerGUI"
    else
        echo "✗ Compilation failed"
        echo "Files in directory:"
        ls -la *.java
    fi
else
    echo "✗ JFlex failed to generate Java file"
fi