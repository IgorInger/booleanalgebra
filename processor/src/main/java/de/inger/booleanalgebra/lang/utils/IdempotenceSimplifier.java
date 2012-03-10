package de.inger.booleanalgebra.lang.utils;

import java.util.List;

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
	    if((l instanceof Variable) && (r instanceof Variable)) {
		if(binaryOperator.getParent() != null) {
		}

		System.out.println("parent => " + binaryOperator.getParent());
	    }
	}
	return operand;

    }

}
