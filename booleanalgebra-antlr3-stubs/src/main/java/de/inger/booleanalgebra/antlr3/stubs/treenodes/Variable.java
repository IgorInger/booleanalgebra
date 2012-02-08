package de.inger.booleanalgebra.antlr3.stubs.treenodes;

public class Variable extends Operand {

	private String name;

	public Variable(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
