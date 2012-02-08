package de.inger.booleanalgebra.treenodes;

public abstract class TreeNode {

	private TreeNode parent;

	public TreeNode() {
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}
