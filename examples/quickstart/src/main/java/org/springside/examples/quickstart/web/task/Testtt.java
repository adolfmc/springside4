package org.springside.examples.quickstart.web.task;

import java.util.ArrayList;
import java.util.List;

public class Testtt {

	public void listarray() {
		List<String> s = new ArrayList<String>();
		s.add("aa");
		s.add("aa1");

		Object[] array = s.toArray();
		String[] ar = (String[]) array;

	}

	public static void main(String[] args) {
		Testtt t = new Testtt();
		t.listarray();
	}

}
