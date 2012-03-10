package de.inger.booleanalgebra.antlr3.treenodes;

public abstract class UnaryOperator extends Operator {

    private Operand operand;

    public UnaryOperator(Operand operand) {
	operand.setParent(this);
	setOperand(operand);
    }

    public Operand getOperand() {
	return this.operand;
    }

    public void setOperand(Operand operand) {
	this.operand = operand;
	this.operand.setParent(this);
    }

    @Override
    public void ReplaceChild(Operand oldChild, Operand newChild) {
	if (getOperand() == oldChild) {
	    setOperand(newChild);
	}
    }

}
