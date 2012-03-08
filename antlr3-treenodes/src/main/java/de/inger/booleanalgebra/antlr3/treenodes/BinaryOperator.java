package de.inger.booleanalgebra.antlr3.treenodes;

public abstract class BinaryOperator extends Operator {
    
    private Operand leftOperand;
    
    private Operand rightOperand;
    
    public BinaryOperator(Operand left, Operand right) {
    	left.setParent(this);
    	right.setParent(this);
    	setLeftOperand(left);
    	setRightOperand(right);
    }
    
    public Operand getLeftOperand() {
        return leftOperand;
    }
    
    public void setLeftOperand(Operand operand) {
    	this.leftOperand = operand;
    }
    
    public Operand getRightOperand() {
        return rightOperand;
    }
    
    public void setRightOperand(Operand operand) {
    	this.rightOperand = operand;
    }

}