# Example-Project
Program       → StmtList <br />
StmtList      → Stmt <br />
              | Stmt StmtList <br /> 
Stmt          →	Decl<br />
              |	Asn<br />
              |	ReadVar<br />
              |	PrintVar<br />
              | FunStmt<br />
Decl          →	Type id OptionalAsn ;<br />
              | Type id [ intlit ] ;<br />
              | Type id (ArgList) { StmtList return Expr ; }<br />
              | void id (ArgList) { StmtList }<br />
ArgList       → Arg ArgList<br />
              | λ<br />
Arg           → Type id<br />
              | Type id [ ]<br />
Type          → var<br />
              | varF<br />
Asn		        →	id = Expr ;<br />
ReadVar	      →	read id ;<br />
PrintVar 	    →	print id ;<br />
FunStmt       → id ( Params ) ;<br />
              | id ( ) ;
Params        → Expr , Params
              | Expr
OptionalAsn	  →	= Expr<br />
              |	λ<br />
Expr		      →	Expr BinaryOp Expr<br />
              |	intlit<br />
              | float<br />
              |	id<br />
              | id ( Params )
              | id ( )
Binaryop	    →	* <br />
              |	/ <br />
              |	+ <br />
              | -
