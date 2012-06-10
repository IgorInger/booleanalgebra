package de.inger.booleanalgebra;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.inger.booleanalgebra.antlr3.treenodes.AssignmentOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Call;
import de.inger.booleanalgebra.antlr3.treenodes.Constant;
import de.inger.booleanalgebra.antlr3.treenodes.EqualsOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Function;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.antlr3.treenodes.OrOperator;
import de.inger.booleanalgebra.antlr3.treenodes.Variable;

public class BooleanLogicInterpreter {

    private static final Log logger = LogFactory.getLog(BooleanLogicInterpreter.class);

    private Map<String, Operand> variables = new HashMap<String, Operand>();

    private Map<String, Operand> functions = new HashMap<String, Operand>();

    private Map<String, Set<String>> functionArguments = new HashMap<String, Set<String>>();

    PrintStream output;

    public BooleanLogicInterpreter() {
	output = System.out;
    }

    public void interprete(List<Operand> operands) {
	for (Operand operand : operands) {
	    interprete(operand, false);
	}
    }

    private Operand interprete(Operand operand, boolean omitOutput) {
	if (operand instanceof AssignmentOperator) {
	    return interpreteAssignmentOperator((AssignmentOperator) operand, false);
	} else if (operand instanceof Variable) {
	    return interpreteVariable((Variable) operand, false);
	} else if (operand instanceof Function) {
	    return interpreteFunction((Function) operand, false);
	} else if (operand instanceof Call) {
	    return interpreteCall((Call) operand, false);
	} else if (operand instanceof OrOperator) {
	    return interpreteOrOperator((OrOperator) operand, false);
	} else if (operand instanceof EqualsOperator) {
	    return interpreteEqualsOperator((EqualsOperator) operand, false);
	} else if (operand instanceof Constant) {
	    return interpreteConstant((Constant) operand, false);
	} else {
	    logger.debug(operand.getClass());
	}
	return operand;
    }

    private Operand interpreteConstant(Constant operand, boolean omitOutput) {
	println(operand.toTreeString());
	return operand;
    }

    private Operand interpreteEqualsOperator(EqualsOperator operand, boolean omitOutput) {
	Operand left = operand.getLeftOperand();
	Operand right = operand.getRightOperand();
	if ((left instanceof Constant) && (right instanceof Constant)) {

	}
	println("? true/false");
	return operand;
    }

    private Operand interpreteOrOperator(OrOperator or, boolean omitOutput) {
	println(or.toTreeString());
	return or;
    }

    private Operand interpreteCall(Call c, boolean omitOutput) {
	String name = c.getName();
	List<Operand> parameters = c.getParameters();
	String signature = String.format("%s@%d", name, parameters.size());
	if (!functions.containsKey(signature)) {
	    logger.error("blabla");
	    return c;
	}
	Operand body = functions.get(signature);
	Set<String> arguments = functionArguments.get(signature);
	for (int i = 0; i < parameters.size(); i++) {
	    parameters.set(i, interprete(parameters.get(i), true));
	}
	Operand operand = buildExpression(body, arguments, parameters);
	return interprete(operand, false);
    }

    private Operand buildExpression(Operand body, Set<String> arguments, List<Operand> parameters) {
	String[] argumentArray = arguments.toArray(new String[0]);
	for (int i = 0; i < argumentArray.length; i++) {
	    String name = argumentArray[i];
	    Operand parameter = parameters.get(i);
	    Variable v = new Variable(name);
	    body.replaceChild(v, parameter);
	}
	return body;
    }

    private Operand interpreteFunction(Function f, boolean omitOutput) {
	String name = f.getName();
	Set<String> arguments = f.getArguments();
	Operand body = f.getBody();
	String signature = String.format("%s@%d", name, arguments.size());
	functions.put(signature, body);
	functionArguments.put(signature, arguments);
	println(f.toTreeString());
	return body;
    }

    private Operand interpreteVariable(Variable v, boolean omitOutput) {
	String name = v.getName();
	if (variables.containsKey(name)) {
	    println(variables.get(name).toTreeString());
	    return variables.get(name);
	} else {
	    println(v.toTreeString());
	    return v;
	}
    }

    private Operand interpreteAssignmentOperator(AssignmentOperator a, boolean omitOutput) {
	Variable variable = (Variable) a.getLeftOperand();
	Operand value = a.getRightOperand();
	variables.put(variable.getName(), value);
	println(String.format("%s = %s", variable.toTreeString(), value.toTreeString()));
	return value;
    }

    private void println(String string) {
	output.println(string);
    }

}
