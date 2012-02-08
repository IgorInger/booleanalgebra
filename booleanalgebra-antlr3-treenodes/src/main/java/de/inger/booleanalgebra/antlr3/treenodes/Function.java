package de.inger.booleanalgebra.antlr3.treenodes;

import java.util.List;

public class Function extends Operand {

	private String name;
	private List<String> arguments;
	private Operand body;

	public Function(String name, List<String> arguments, Operand body) {
		setName(name);
		setArguments(arguments);
		setBody(body);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	public Operand getBody() {
		return body;
	}

	public void setBody(Operand body) {
		this.body = body;
	}

}
