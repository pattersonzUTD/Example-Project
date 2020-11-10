/*-***
 * This grammar is defined for the example grammar defined in the
 *project part 1 instructions
 */

/*
 * NOTE: make sure that the java cup runtime file is in the same directory as this file
 * it is also alright if the runtime location is added to to the classpath, but just
 * putting in the same file is far easier.
 */
import java_cup.runtime.*;



%%
/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class ExampleScanner

%{

Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*-*
 * PATTERN DEFINITIONS:
 */

tab           = \\t
newline		    = \\n
slash			    = \\
letter        = [A-Za-z]
digit         = [0-9]
id   			    = {letter}+ 
intlit	      = {digit}+
floatend      = (f|F)
floatlit      = {intlit}{floatend}|{intlit}?.{intlit}{floatend}
inlinecomment = {slash}{slash}.*\n
whitespace    = [ \n\t\r]
escapequote     = {slash}\"
stringchar      = [[[^\\]&&[^\"]]&&[[^\n]&&[^\t]]]|{newline}|{tab}|{escapequote}|{slash}{slash}
stringlit       = \"{stringchar}*\"



%%
/**
 * LEXICAL RULES:
 */
read               { return newSym(sym.READ, "read"); }
print		           { return newSym(sym.PRINT, "print"); }
"*"                { return newSym(sym.TIMES, "*"); }
"+"                { return newSym(sym.PLUS, "+"); }
"-"                { return newSym(sym.MINUS, "-"); }
"/"                { return newSym(sym.DIVIDE, "/"); }
"="                { return newSym(sym.EQ, "="); }
";"                { return newSym(sym.SEMI, ";"); }
"("                { return newSym(sym.LPAREN, "("); }
")"                { return newSym(sym.RPAREN, ")"); }
","                { return newSym(sym.COMMA, ","); }
"{"                { return newSym(sym.LBRACE, "{"); }
"}"                { return newSym(sym.RBRACE, "}"); }
"["                { return newSym(sym.LBRKT, "["); }
"]"                { return newSym(sym.RBRKT, "]"); }
void               { return newSym(sym.VOID, "void"); }
var		             { return newSym(sym.VAR, "var"); }
return             { return newSym(sym.RETURN, "return"); }
varf               { return newSym(sym.VARF, "varf"); }
{id}               { return newSym(sym.ID, yytext()); }
{intlit}           { return newSym(sym.INTLIT, new Integer(yytext())); }
{inlinecomment}    { /* For this stand-alone lexer, print out comments. */}
{whitespace}       { /* Ignore whitespace. */ }
{stringlit}        { return newSym(sym.STR, new String(yytext())); }
{floatlit}         { return newSym(sym.FLOATLIT, new Double(yytext().substring(0,yytext().length()-1))); }
.                  { System.out.println("Illegal char, '" + yytext() +
                    "' line: " + yyline + ", column: " + yychar); } 