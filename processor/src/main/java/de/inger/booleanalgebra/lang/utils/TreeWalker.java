package de.inger.booleanalgebra.lang.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.inger.booleanalgebra.antlr3.treenodes.BinaryOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.antlr3.treenodes.Operator;
import de.inger.booleanalgebra.antlr3.treenodes.UnaryOperator;

public class TreeWalker {

    private List<TreeVisitor> visitors = new ArrayList<TreeVisitor>();

    private static final Logger LOGGER = LogManager.getLogger(TreeWalker.class);

    public TreeWalker() {
    }

    public TreeWalker(TreeVisitor visitor) {
	visitors.add(visitor);
    }

    public void addTreeVisitor(TreeVisitor treeVisitor) {
	visitors.add(treeVisitor);
    }

    public void removeTreeVisitor(TreeVisitor treeVisitor) {
	visitors.remove(treeVisitor);
    }

    public void traverse(Operand operand) {
	if (operand instanceof Operator) {
	    traverseOperator((Operator) operand);
	} else {
	    visit(operand);
	}
    }

    private void traverseOperator(Operator operator) {
	if (operator instanceof UnaryOperator) {
	    traverseUnaryOperator((UnaryOperator) operator);
	}
	if (operator instanceof BinaryOperator) {
	    traversBinaryOperator((BinaryOperator) operator);
	}
    }

    private void traversBinaryOperator(BinaryOperator operator) {

	if (operator.getLeft() != null) {
	    traverse(operator.getLeft());
	}
	visit(operator);
	if (operator.getRight() != null) {
	    traverse(operator.getRight());
	}
    }

    private void traverseUnaryOperator(UnaryOperator operator) {
	if (operator.getOperand() != null) {
	    traverse(operator.getOperand());
	}
	visit(operator);
    }

    private void visit(Operand operand) {
	LOGGER.debug(operand);
	for(TreeVisitor treeVisitor : visitors) {
	    treeVisitor.visit(operand);
	}
    }

}
