package de.inger.booleanalgebra.lang.utils;

import de.inger.booleanalgebra.antlr3.treenodes.Operand;

public interface TreeVisitor {

    void visit(Operand operand);

}
