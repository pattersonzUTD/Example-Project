JAVA=java
JAVAC=javac
JFLEX=$(JAVA) -jar jflex-1.8.2/lib/jflex-full-1.8.2.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: run

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
				$(JAVAC) -cp $(CP) $*.java

FILE=		ExampleScanner.java      parser.java    sym.java \
				ExampleParserTest.java \
				Asn.java Decl.java Expr.java OptionalAsn.java \
				PrintVar.java Program.java ReadVar.java Stmt.java \
				StmtList.java Token.java BinaryOp.java

dump: parserD.java $(FILE:java=class)

run: testParse.txt lexTest.txt orderPrec.txt

testParse.txt: all
		$(JAVA) -cp $(CP) ExampleParserTest testParse.txt > testParse-output.txt
		cat -n testParse-output.txt

lexTest.txt: all
		$(JAVA) -cp $(CP) ExampleParserTest lexTest.txt > lexTest-output.txt
		cat -n lexTest-output.txt

orderPrec.txt: all
		$(JAVA) -cp $(CP) ExampleParserTest orderPrec.txt > orderPrec-output.txt
		cat -n orderPrec-output.txt

all: ExampleScanner.java parser.java $(FILE:java=class)

clean:
		rm -f *.class *~ *.bak ExampleScanner.java parser.java sym.java

ExampleScanner.java: exampleGrammar.jflex
		$(JFLEX) exampleGrammar.jflex

parser.java: exampleTokens.cup
		$(CUP) -interface -progress < exampleTokens.cup

parserD.java: exampleTokens.cup
		$(CUP) -interface -dump < exampleTokens.cup
