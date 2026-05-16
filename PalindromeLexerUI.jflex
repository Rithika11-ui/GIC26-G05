

// %%
// %class PalindromeLexerUI
// %public
// %type String
// %line
// %column

// %{
//     private boolean isPalindrome(String text) {
//         if (text.length() <= 1) return false;
        
//         String cleanText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
//         if (cleanText.length() <= 1) return false;
        
//         int left = 0;
//         int right = cleanText.length() - 1;
        
//         while (left < right) {
//             if (cleanText.charAt(left) != cleanText.charAt(right)) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
//         return true;
//     }
// %}

// Digit = [0-9]
// Letter = [a-zA-Z]
// Whitespace = [ \t\n\r]

// num = [0-9]+
// word = {Letter}+
// email = [a-zA-Z0-9._%-]+"@"[a-zA-Z0-9.-]+"."[a-zA-Z]+

// %%

// {num}        { return "LINE " + yyline + ", COL " + yycolumn + ": NUMBER - " + yytext(); }
// {email}      { return "LINE " + yyline + ", COL " + yycolumn + ": EMAIL - " + yytext(); }
// {word}       { 
//     if (isPalindrome(yytext())) {
//         return "LINE " + yyline + ", COL " + yycolumn + ": PALINDROME - " + yytext();
//     } else {
//         return "LINE " + yyline + ", COL " + yycolumn + ": WORD - " + yytext();
//     }
// }
// {Whitespace} { /* ignore whitespace */ }
// .            { return "LINE " + yyline + ", COL " + yycolumn + ": UNKNOWN - " + yytext(); }
// <<EOF>>      { return null; }
%%
%class PalindromeLexerUI
%public
%type String
%line
%column

%{
    private boolean isPalindrome(String text) {
        if (text.length() <= 1) return false;
        
        // Clean the text: remove non-alphanumeric characters and convert to lowercase
        String cleanText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (cleanText.length() <= 1) return false;
        
        int left = 0;
        int right = cleanText.length() - 1;
        
        while (left < right) {
            if (cleanText.charAt(left) != cleanText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    private String getPalindromeType(String text) {
        String cleanText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        if (cleanText.matches("[0-9]+")) {
            return "NUMERIC_PALINDROME";
        } else if (cleanText.matches("[a-zA-Z]+")) {
            return "WORD_PALINDROME";
        } else if (cleanText.matches("[a-zA-Z0-9]+")) {
            return "ALPHANUMERIC_PALINDROME";
        }
        return "PALINDROME";
    }
%}

Digit = [0-9]
Letter = [a-zA-Z]
Whitespace = [ \t\n\r]

num = [0-9]+
word = {Letter}+
alphanumeric = [a-zA-Z0-9]+
email = [a-zA-Z0-9._%-]+"@"[a-zA-Z0-9.-]+"."[a-zA-Z]+

%%

{num}        { 
    if (isPalindrome(yytext())) {
        return "LINE " + yyline + ", COL " + yycolumn + ": NUMERIC_PALINDROME - " + yytext();
    } else {
        return "LINE " + yyline + ", COL " + yycolumn + ": NUMBER - " + yytext();
    }
}
{email}      { 
    return "LINE " + yyline + ", COL " + yycolumn + ": EMAIL - " + yytext(); 
}
{word}       { 
    if (isPalindrome(yytext())) {
        return "LINE " + yyline + ", COL " + yycolumn + ": WORD_PALINDROME - " + yytext();
    } else {
        return "LINE " + yyline + ", COL " + yycolumn + ": WORD - " + yytext();
    }
}
{alphanumeric} { 
    if (isPalindrome(yytext())) {
        String type = getPalindromeType(yytext());
        return "LINE " + yyline + ", COL " + yycolumn + ": " + type + " - " + yytext();
    } else {
        // Check if it's mixed alphanumeric (not pure word or number)
        if (!yytext().matches("[0-9]+") && !yytext().matches("[a-zA-Z]+")) {
            return "LINE " + yyline + ", COL " + yycolumn + ": ALPHANUMERIC - " + yytext();
        }
        // This shouldn't happen due to previous patterns, but just in case
        return "LINE " + yyline + ", COL " + yycolumn + ": ALPHANUMERIC - " + yytext();
    }
}
{Whitespace} { /* ignore whitespace */ }
.            { return "LINE " + yyline + ", COL " + yycolumn + ": UNKNOWN - " + yytext(); }
<<EOF>>      { return null; }