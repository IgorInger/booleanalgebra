package de.inger.booleanalgebra.lang;

import de.inger.booleanalgebra.antlr3.treenodes.BinaryOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Constant;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.antlr3.treenodes.Operator;
import de.inger.booleanalgebra.antlr3.treenodes.UnaryOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Variable;

public interface Visitor {

    void visitOperand(Operand operand);

    void visitOperator(Operator operator);

    void visitUnaryOperator(UnaryOperator unaryOperator);

    void visitBinaryOperator(BinaryOperator binaryOperator);



    void visitVariable(Variable variable);

    void visitConstant(Constant constant);

}
