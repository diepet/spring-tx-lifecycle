package it.diepet.spring.tx.eventdispatcher.test.util;

import java.util.ArrayList;
import java.util.List;

public class StringCollector {

	private static List<String> stringList = new ArrayList<String>();

	public static void reset() {
		stringList.clear();
	}

	public static void add(String s) {
		stringList.add(s);
	}

	public static List<String> getList() {
		return stringList;
	}

}
