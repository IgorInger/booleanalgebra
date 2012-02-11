package de.inger.booleanalgebra.antlr3.treenodes;

import java.util.Collections;
import java.util.Set;

public class Function extends Operand {

    private String name;
    private Set<String> arguments;
    private Operand body;

    public Function(String name, Set<String> arguments, Operand body) {
	setName(name);
	setArguments(arguments);
	setBody(body);
    }

    public Function(String name, Operand body) {
	this(name, Collections.<String> emptySet(), body);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<String> getArguments() {
	return arguments;
    }

    public void setArguments(Set<String> arguments) {
	this.arguments = arguments;
    }

    public Operand getBody() {
	return body;
    }

    public void setBody(Operand body) {
	this.body = body;
    }

}
