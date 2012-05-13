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
import de.inger.booleanalgebra.antlr3.treenodes.Function;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;
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
	    interprete(operand);
	}
    }

    private void interprete(Operand operand) {
	if (operand instanceof AssignmentOperator) {
	    interpreteAssignmentOperator((AssignmentOperator) operand);
	} else if(operand instanceof Variable) {
	    interpreteVariable((Variable) operand);
	} else if(operand instanceof Function) {
	    interpreteFunction((Function) operand);
	} else if (operand instanceof Call) {
	    interpreteCall((Call) operand);
	}
	else {
	    logger.debug(operand.toTreeString());
	}
    }

    private void interpreteCall(Call c) {
	String name = c.getName();
	List<Operand> parameters = c.getParameters();
	String signature = String.format("%s@%d", name, parameters.size());
	if(!functions.containsKey(signature)) {
	    logger.error("blabla");
	}
	Operand body = functions.get(signature);
	Set<String> arguments = functionArguments.get(signature);
	Operand operand = buildExpression(body, arguments, parameters);
	interprete(operand);
    }

    private Operand buildExpression(Operand body, Set<String> arguments, List<Operand> parameters) {
	String[] argumentArray = arguments.toArray(new String[0]);
	for(int i = 0; i < argumentArray.length; i++) {
	    String name = argumentArray[i];
	    Operand parameter = parameters.get(i);
	    Variable v = new Variable(name);
	    body.replaceChild(v, parameter);
	}
	return body;
    }

    private void interpreteFunction(Function f) {
	String name = f.getName();
	Set<String> arguments = f.getArguments();
	Operand body = f.getBody();
	String signature = String.format("%s@%d", name, arguments.size());
	functions.put(signature, body);
	functionArguments.put(signature, arguments);
    }

    private void interpreteVariable(Variable v) {
	String name = v.getName();
	if(variables.containsKey(name)) {
	    output.println(variables.get(name));
	}
    }

    private void interpreteAssignmentOperator(AssignmentOperator a) {
	Variable variable = (Variable) a.getLeftOperand();
	Operand value = a.getRightOperand();
	variables.put(variable.getName(), value);
    }


}
