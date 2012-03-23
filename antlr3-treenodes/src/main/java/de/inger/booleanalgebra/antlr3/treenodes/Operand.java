package de.inger.booleanalgebra.antlr3.treenodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public abstract class Operand {

    private Operand parent;

    private List<Operand> children;

    public Operand() {
	children = new ArrayList<Operand>();
    }

    public Operand getParent() {
	return parent;
    }

    public void setParent(Operand parent) {
	this.parent = parent;
    }

    public abstract void replaceChild(Operand oldChild, Operand newChild);

    public abstract String toTreeString();

    public Enumeration<Operand> children() {
	return Collections.enumeration(children);
    }

    public boolean addChild(Operand operand) {
	return children.add(operand);
    }

    public void addChild(int index, Operand operand) {
	children.add(index, operand);
    }

    public Operand setChild(int index, Operand operand) {
	return children.set(index, operand);
    }

    public boolean addAllChildren(Collection<? extends Operand> collection) {
	return children.addAll(collection);
    }

    public boolean addAllChildren(int index, Collection<? extends Operand> collection) {
	return children.addAll(index, collection);
    }

    public Operand removeChild(int index) {
	return children.remove(index);
    }

    public boolean removeChild(Operand operand) {
	return children.remove(operand);
    }

    public boolean removeAllChildren(Collection<? extends Operand> collection) {
	return children.removeAll(collection);
    }

    public void clearChildren() {
	children.clear();
    }

    public int childCount() {
	return children.size();
    }

    public Operand getChild(int index) {
	return children.get(index);
    }

}
