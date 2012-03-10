package de.inger.booleanalgebra.lang.utils;

import java.util.ArrayList;
import java.util.List;

import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.antlr3.treenodes.Variable;
import de.inger.booleanalgebra.lang.utils.TreeVisitor;

public class VariableCounter implements TreeVisitor {

    private List<Variable> variables = new ArrayList<Variable>();

    @Override
    public void visit(Operand operand) {
	if (operand instanceof Variable) {
	    variables.add((Variable) operand);
	}
    }

    public List<Variable> getVariables() {
	return variables;
    }

}
