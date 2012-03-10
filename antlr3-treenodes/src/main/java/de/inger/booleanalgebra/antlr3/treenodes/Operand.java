package de.inger.booleanalgebra.antlr3.treenodes;

public abstract class Operand {

    private Operand parent;

    public Operand() {
    }

    public Operand getParent() {
	return parent;
    }

    public void setParent(Operand parent) {
	this.parent = parent;
    }

    public abstract void ReplaceChild(Operand oldChild, Operand newChild);
}
