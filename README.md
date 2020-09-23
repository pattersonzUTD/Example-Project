# Example-Project
Program       StmtList
StmtList      Stmt
              | Stmt StmtList 
Stmt          →	Decl<br />
              |	Asn<br />
              |	ReadVar<br />
              |	PrintVar<br />
Decl          →	var id OptionalAsn ;<br />
Asn		        →	id = Expr ;<br />
ReadVar	      →	read id ;<br />
PrintVar 	    →	print id ;<br />
OptionalAsn	  →	= Expr<br />
              |	λ<br />
Expr		      →	Expr BinaryOp Expr<br />
              |	intlit<br />
              |	id<br />
Binaryop	    →	* <br />
              |	/ <br />
              |	+ <br />
              | -

# Ambiguity
Program       StmtList
StmtList      Stmt
              | Stmt StmtList 
Stmt          →	Decl<br />
              |	Asn<br />
              |	ReadVar<br />
              |	PrintVar<br />
Decl          →	var id OptionalAsn ;<br />
Asn		        →	id = Expr ;<br />
ReadVar	      →	read id ;<br />
PrintVar 	    →	print PrintList ;<br />
PrintList     → OptStr OptVar
              | OptStr OptExpr
OptStr        → stringlit
              | λ
OptVar        → var OptStr OptVarInner id
              | λ
OptVarInner   → OptVar
              | ExprList
ExprList      → Expr ExprList
              | λ
OptExpr       → expr
              | λ
OptionalAsn	  →	= Expr<br />
              |	λ<br />
Expr		      →	Expr BinaryOp Expr<br />
              |	intlit<br />
              |	id<br />
Binaryop	    →	* <br />
              |	/ <br />
              |	+ <br />
              | -
