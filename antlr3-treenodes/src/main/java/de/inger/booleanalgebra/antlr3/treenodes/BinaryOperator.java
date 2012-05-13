package de.inger.booleanalgebra.antlr3.treenodes;

public abstract class BinaryOperator extends Operator {

    public static final int LEFT_OPERAND_INDEX = 0;
    public static final int RIGHT_OPERAND_INDEX = 1;

    public BinaryOperator(Operand left, Operand right) {
	setLeftOperand(left);
	setRightOperand(right);
    }

    public Operand getLeftOperand() {
	return getChild(0);
    }

    public void setLeftOperand(Operand operand) {
	operand.setParent(this);
	if (childCount() == 0) {
	    addChild(operand);
	} else {
	    setChild(0, operand);
	}
    }

    public Operand getRightOperand() {
	return getChild(1);
    }

    public void setRightOperand(Operand operand) {
	operand.setParent(this);
	if (childCount() == 1) {
	    addChild(operand);
	} else {
	    setChild(1, operand);
	}
    }

    @Override
    public void replaceChild(Operand oldChild, Operand newChild) {
	if (getLeftOperand().equals(oldChild)) {
	    setLeftOperand(newChild);
	} else {
	    getLeftOperand().replaceChild(oldChild, newChild);
	}
	if (getRightOperand().equals(oldChild)) {
	    setRightOperand(newChild);
	} else {
	    getRightOperand().replaceChild(oldChild, newChild);
	}
    }

}
