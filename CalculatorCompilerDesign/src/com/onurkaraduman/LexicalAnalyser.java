package com.onurkaraduman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyser {

	private String bracketOpen = "(";
	private String bracketClose = ")";
	private String plus = "+";
	private String minus = "-";
	private String times = "*";
	private String obelus = "/";
	private String power = "^";
	private String comment = "!";
	private String space = " ";
	// 0 iken s�ra say�da 1 iken s�ra operatordedir
	private boolean nowNumber = true;
	private boolean toContinueNumber = false;

	private String textAll;
	private ArrayList<String> listAll;
	private ErrorList errorList;

	public LexicalAnalyser(String textAll) {
		super();
		this.textAll = textAll;
		listAll = new ArrayList<String>();
		errorList = new ErrorList();
		listAll = stringToList(textAll);

	}

	// step1- once commentleri temizle
	// Commentleri kald�r
	// Tokenlara ay�r

	public boolean startAnalyse() {
		return controlOrder();
	}

	public boolean controlOrder() {

		for (int i = 0; i < listAll.size(); i++) {
			int whiteSpaceCounter = 0;
			// bo�luklar� temizle
			while (listAll.get(i).equals(" ")) {
				listAll.remove(i);
				whiteSpaceCounter++;
			}
			// eger s�radaki operation i�areti ama okudugumuz yine sayi ise
			// missing operator sign
			// hatas� verecektir
			if (!nowNumber) {
				if (isDigit(listAll.get(i))) {
					System.out.println("Error!!!->(No: 1) "
							+ errorList.errorMap.get(1));
					return false;
				}
				// eger operator gelecek yerde operatorden ve say�dan farkl�
				// bir�ey gelirse
				else if (!isOperator(listAll.get(i))) {
					System.out.println("Error!!!->(No: 2) "
							+ errorList.errorMap.get(2));
					return false;
				}

				// eger en son karakter olarak operator kalm�� ise
				if (i == (listAll.size() - 1)) {
					System.out.println("Error!!!->(No: 3) "
							+ errorList.errorMap.get(3));
					return false;
				}

				nowNumber = true;

			} else {

				if (isOperator(listAll.get(i))) {
					System.out.println("Error!!!->(No: 3) "
							+ errorList.errorMap.get(3));
					return false;
				} else if (isDigit(listAll.get(i))) {
					int counter = i + 1;
					String concatString = listAll.get(i);
					if (counter < listAll.size()) {
						while (isDigit(listAll.get(counter))) {
							String num = listAll.get(counter);
							concatString = concatString.concat(num);
							listAll.remove(counter);
						}
						listAll.set(i, concatString);
						nowNumber = false;
					}

				} else {
					System.out.println("Error!!!->(No: 2) "
							+ errorList.errorMap.get(2));
					return false;
				}

			}

		}
		return true;
	}

	// gelen string degeri arrayliste donusturuyoruz
	public ArrayList<String> stringToList(String s) {
		char[] c = s.toCharArray();
		listAll = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < c.length; i++) {

			// comment sat�rlar�n� kald�r
			if (c[i] == '!') {
				i++;
				while (c[i] != '!') {
					i++;
				}
			} else
				list.add(String.valueOf(c[i]));

		}
		return list;
	}

	// Bo�luklar� temizle
	public ArrayList<String> clearWhiteSpace(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(space)) {
				list.remove(i);
			}
		}
		return list;
	}

	// say� olup olmad�g�n� kontrol eden fonksiyon
	public boolean isDigit(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// operator i�areti olup olmad�g�n� kontrol eden fonksiyon
	public boolean isOperator(String operator) {
		// operator.equals(bracketClose) || operator.equals(bracketOpen)
		if (operator.equals(plus) || operator.equals(minus)
				|| operator.equals(times) || operator.equals(obelus)
				|| operator.equals(power)) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getListAll() {
		return listAll;
	}

	public void setListAll(ArrayList<String> listAll) {
		this.listAll = listAll;
	}

}
