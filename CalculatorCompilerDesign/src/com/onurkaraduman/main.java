package com.onurkaraduman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		// String userText = scanner.nextLine();
		// LexicalAnalyser lexAnalyser = new LexicalAnalyser(userText);
		// recursionFunctionExample(0);
		String a = scanner.nextLine();
		ArrayList<String> list = new ArrayList<String>();
		//list = stringToList(a);
		LexicalAnalyser lexAnalyzer = new LexicalAnalyser(a);
		if(lexAnalyzer.startAnalyse()){
			System.out.println("Analiz Basarili");
			list = lexAnalyzer.getListAll();
			ProgressOperation po = new ProgressOperation(list);
			System.out.println(po.resultOfAllText);
		}
		else
			System.out.println("Analiz Hatasi");
		

	}

	public static ArrayList<String> stringToList(String s) {
		char[] c = s.toCharArray();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < c.length; i++) {
			list.add(String.valueOf(c[i]));

		}
		return list;
	}

}
