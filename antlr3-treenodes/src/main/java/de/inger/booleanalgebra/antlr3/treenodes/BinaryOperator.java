package de.inger.booleanalgebra.antlr3.treenodes;

public abstract class BinaryOperator extends Operand implements Operator {

    public BinaryOperator(Operand left, Operand right) {
	setLeft(left);
	setRight(right);
    }

    public void replaceChild(Operand oldChild, Operand newChild) {
	if (getLeft().equals(oldChild)) {
	    setLeft(newChild);
	} else {
	    getLeft().replaceChild(oldChild, newChild);
	}
	if (getRight().equals(oldChild)) {
	    setRight(newChild);
	} else {
	    getRight().replaceChild(oldChild, newChild);
	}
    }

}
