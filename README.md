# Example-Project
Program       → StmtList <br />
StmtList      → Stmt <br />
              | Stmt StmtList <br /> 
Stmt          →	Decl<br />
              |	Asn<br />
              |	ReadVar<br />
              |	PrintVar<br />
Decl          →	Type id OptionalAsn ;<br />
Type          → var<br />
              | varF<br />
Asn		        →	id = Expr ;<br />
ReadVar	      →	read id ;<br />
PrintVar 	    →	print id ;<br />
OptionalAsn	  →	= Expr<br />
              |	λ<br />
Expr		      →	Expr BinaryOp Expr<br />
              |	intlit<br />
              | float<br />
              |	id<br />
Binaryop	    →	* <br />
              |	/ <br />
              |	+ <br />
              | -
