package de.inger.booleanalgebra.lang.utils;

import java.util.ArrayList;
import java.util.List;

import de.inger.booleanalgebra.antlr3.treenodes.BinaryOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;

public class BinaryOperatorCounter implements TreeVisitor {

    private List<BinaryOperator> binaryOperators = new ArrayList<BinaryOperator>();

    @Override
    public void visit(Operand operand) {
	if (operand instanceof BinaryOperator) {
	    binaryOperators.add((BinaryOperator) operand);
	}
    }

    public List<BinaryOperator> getBinaryOperators() {
	return binaryOperators;
    }

}
