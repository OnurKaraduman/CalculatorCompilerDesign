package com.onurkaraduman;

import java.util.HashMap;
import java.util.Map;

public class ErrorList {

	Map<Integer, String> errorMap;
	
	
	private int ERRORLISTSIZE = 10; 

	public ErrorList() {
		// TODO Auto-generated constructor stub
		errorMap = new HashMap<Integer, String>();
		errorMap.put(1, "missing operation sign");
		errorMap.put(2, "wrong character");
		errorMap.put(3, "missing number");
	}
}