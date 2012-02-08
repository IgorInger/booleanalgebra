package de.inger.booleanalgebra.treenodes;

import java.util.List;

public class Call extends Operand {

	private String name;
	private List<Operand> parameters;

	public Call(String name, List<Operand> parameters) {
		setName(name);
		setParameters(parameters);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parameters
	 */
	public List<Operand> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(List<Operand> parameters) {
		this.parameters = parameters;
	}

}
