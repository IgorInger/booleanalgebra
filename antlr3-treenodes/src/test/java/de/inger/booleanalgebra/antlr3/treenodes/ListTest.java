package de.inger.booleanalgebra.antlr3.treenodes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ListTest {

    @Test
    public void testList() {
	List<Object> list = new ArrayList<Object>();
	list.add(0, new Object());
	list.add(0, new Object());
    }

}
