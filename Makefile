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
				ExampleTypeCheckerTest.java \
				Asn.java Decl.java Expr.java OptionalAsn.java \
				PrintVar.java Program.java ReadVar.java Stmt.java \
				StmtList.java Token.java BinaryOp.java Type.java

dump: parserD.java $(FILE:java=class)

run: testParse.txt lexTest.txt orderPrec.txt invalidCoerc.txt invalidRedef.txt undefUse.txt validCoerc.txt

testParse.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest testParse.txt > testParse-output.txt
		cat -n testParse-output.txt

lexTest.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest lexTest.txt > lexTest-output.txt
		cat -n lexTest-output.txt

orderPrec.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest orderPrec.txt > orderPrec-output.txt
		cat -n orderPrec-output.txt

invalidRedef.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest invalidRedef.txt > invalidRedef-output.txt
		cat -n invalidRedef-output.txt

invalidRedef.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest invalidRedef.txt > invalidRedef-output.txt
		cat -n invalidRedef-output.txt

undefUse.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest undefUse.txt > undefUse-output.txt
		cat -n undefUse-output.txt

validCoerc.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest validCoerc.txt > validCoerc-output.txt
		cat -n validCoerc-output.txt

all: ExampleScanner.java parser.java $(FILE:java=class)

clean:
		rm -f *.class *~ *.bak ExampleScanner.java parser.java sym.java

ExampleScanner.java: exampleGrammar.jflex
		$(JFLEX) exampleGrammar.jflex

parser.java: exampleTokens.cup
		$(CUP) -interface -progress < exampleTokens.cup

parserD.java: exampleTokens.cup
		$(CUP) -interface -dump < exampleTokens.cup
