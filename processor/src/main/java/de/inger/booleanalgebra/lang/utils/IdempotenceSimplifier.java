package de.inger.booleanalgebra.lang.utils;

import java.util.List;

import de.inger.booleanalgebra.antlr3.treenodes.AssignmentOperator;
import de.inger.booleanalgebra.antlr3.treenodes.BinaryOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.antlr3.treenodes.Variable;

public class IdempotenceSimplifier {

    public static Operand simplify(Operand operand) {
	BinaryOperatorCounter counter = new BinaryOperatorCounter();
	TreeWalker treeWalker = new TreeWalker(counter);
	treeWalker.traverse(operand);
	List<BinaryOperator> operators = counter.getBinaryOperators();
	for (BinaryOperator binaryOperator : operators) {
	    Operand l = binaryOperator.getLeftOperand();
	    Operand r = binaryOperator.getRightOperand();
	    if (!(binaryOperator instanceof AssignmentOperator) && (l instanceof Variable)
		    && (r instanceof Variable)) {
		Variable lv = (Variable) l;
		Variable rv = (Variable) r;
		if (lv.getName().equals(rv.getName())) {
		    if (binaryOperator.getParent() == null) {
			return lv;
		    } else {
			Operand parent = binaryOperator.getParent();
			parent.replaceChild(binaryOperator, lv);
			return simplify(operand);
		    }
		}
	    }
	}
	return operand;
    }

}
