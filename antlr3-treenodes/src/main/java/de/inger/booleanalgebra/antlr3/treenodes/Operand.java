package de.inger.booleanalgebra.antlr3.treenodes;

public abstract class Operand {

    private Operand parent;

    private Operand left;

    private Operand right;

    public Operand() {
    }

    public Operand getLeft() {
	return left;
    }

    public void setLeft(Operand left) {
	this.left = left;
    }

    public Operand getRight() {
	return right;
    }

    public void setRight(Operand right) {
	this.right = right;
    }


    public Operand getParent() {
	return parent;
    }

    public void setParent(Operand parent) {
	this.parent = parent;
    }

    public abstract void replaceChild(Operand oldChild, Operand newChild);

    public abstract String toTreeString();

}
