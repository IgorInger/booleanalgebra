package de.inger.booleanalgebra.antlr3.treenodes;

public class OrOperator extends BinaryOperator {

        public OrOperator(Operand left, Operand right) {
                super(left, right);
        }

        @Override
        public String toTreeString() {
            String value = String.format("%s || %s", getLeftOperand().toTreeString(), getRightOperand().toTreeString());
            if(getParent() != null) {
        	return String.format("(%s)", value);
            }
            return value;
        }

}
