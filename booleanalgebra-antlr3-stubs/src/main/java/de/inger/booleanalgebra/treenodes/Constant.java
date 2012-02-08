package de.inger.booleanalgebra.treenodes;

public class Constant extends Operand {

	private boolean value;

	public Constant(boolean value) {
		setValue(value);
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
