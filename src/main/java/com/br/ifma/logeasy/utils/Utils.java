package com.br.ifma.logeasy.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static <T> List<T> toList(Iterable<T> iter) {
	    List<T> list = new ArrayList<T>();
	    for (T item : iter) {
	        list.add(item);
	    }
	    return list;
	}
}
