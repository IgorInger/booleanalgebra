package de.inger.booleanalgebra.antlr3.treenodes;

public class AndOperator extends BinaryOperator {

    public AndOperator(Operand left, Operand right) {
	super(left, right);
    }

    @Override
    public String toTreeString() {
	return String.format("%s & %s", getLeft().toTreeString(), getRight().toTreeString());
    }

}
