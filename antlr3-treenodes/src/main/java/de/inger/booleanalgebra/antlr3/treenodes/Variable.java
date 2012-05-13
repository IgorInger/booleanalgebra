package de.inger.booleanalgebra.antlr3.treenodes;

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

    @Override
    public void replaceChild(Operand oldChild, Operand newChild) {
    }

    @Override
    public String toTreeString() {
	return name;
    }

    @Override
    public boolean equals(Object obj) {
	if(obj == null) {
	    return false;
	}
	if(this == obj) {
	    return true;
	}
	if(!(obj instanceof Variable)) {
	    return false;
	}
	Variable a = (Variable) obj;
	if(getName().equals(a.getName())) {
	    return true;
	}
	return false;
    }

}
