package com.onurkaraduman.engine;

import java.util.ArrayList;
import java.util.List;

public class ProgressOperation {

	private List<String> operationList = new ArrayList<String>();
	{
		operationList.add("+");
		operationList.add("-");
		operationList.add("*");
		operationList.add("/");
	}
	String bracketOpen = "(";
	String bracketClose = ")";
	String obelus = "/";
	String times = "*";
	String power = "^";
	String plus = "+";
	String minus = "-";
	double result = 0;
	public double resultOfAllText = 0;

	private ArrayList<String> listAll;

	public ProgressOperation(ArrayList<String> listAll) {
		super();
		this.listAll = new ArrayList<String>();
		this.listAll = listAll;
		recursionFunctionForBracket(0);
	}

	// al�nan arrayin icindeki parantezlere gore i�lemleri rekursif olarak yapan
	// fonksiyonumuz
	// mesela en i�teki parantezi bulup, bunun i�indeki i�lemleri yapa yapa en
	// d�� sarmaldaki paranteze kadar gelecek
	// i�ine parantezin bulundugu indisi alarak yoluna devam edecek

	// parantez i�lerindeki i�lemelri yapan rekursif fonksiyonumuz
	public int recursionFunctionForBracket(int a) {
		ArrayList<String> arr2 = new ArrayList<String>();

		String s = "";
		for (int i = a; i < listAll.size(); i++) {

			// Eger parantez a��lm�� ise
			if (listAll.get(i).equals(bracketOpen)) {

				i = recursionFunctionForBracket(i + 1);
				// parantezin i�inden gelen degeri bir onceki listemizin icine
				// ekliyoruz
				arr2.add(String.valueOf(result));

				// Eger parantez kapanm�� ise
			} else if (listAll.get(i).equals(bracketClose)) {

				// parantezin i�indeki i�lemi yap�p global degisken olan result
				// degiskenini set ediyoruz
				result = processWithArrayList(arr2);
				return i + 1;

			}

			if (i >= listAll.size() || listAll.get(i).toString().equals(")")) {
				result = processWithArrayList(arr2);
				// return i + 1;
				break;
			}

			arr2.add(listAll.get(i));
		}
		resultOfAllText = processWithArrayList(arr2);
		return listAll.size();

	}

	public double processWithArrayList(ArrayList<String> list) {
		double resultPower = 0;

		int indexForPower;
		// ilk ba�ta us alma i�lemini ger�ekle�tirelim
		// us ald�k�a o i�lemi silip sonucu o indise yazal�m
		while ((indexForPower = list.lastIndexOf(power)) > -1) {

			resultPower = Math.pow(
					Double.parseDouble(list.get(indexForPower - 1)),
					Double.parseDouble(list.get(indexForPower + 1)));

			// us alma i�lemi 3 adet list eleman� aras�nda oldugu i�in i�lemi
			// yap�p bu elemanlar� listeden
			// ��kar�yoruz
			list.remove(indexForPower - 1);
			list.remove(indexForPower - 1);
			list.remove(indexForPower - 1);
			list.add(indexForPower - 1, String.valueOf(resultPower));

		}

		int indexForDevision;
		double resultDivision = 0;
		// bolme i�lemlerini yap
		while ((indexForDevision = list.lastIndexOf(obelus)) > -1) {

			resultDivision = Double.parseDouble(list.get(indexForDevision - 1))
					/ Double.parseDouble(list.get(indexForDevision + 1));
			list.remove(indexForDevision - 1);
			list.remove(indexForDevision - 1);
			list.remove(indexForDevision - 1);
			list.add(indexForDevision - 1, String.valueOf(resultDivision));
		}

		double resultMultiplication = 0;
		int indexForMultiplication;
		// �arpma islemlerini yap
		while ((indexForMultiplication = list.lastIndexOf(times)) > -1) {

			resultMultiplication = Double.parseDouble(list
					.get(indexForMultiplication - 1))
					/ Double.parseDouble(list.get(indexForMultiplication + 1));
			list.remove(indexForMultiplication - 1);
			list.remove(indexForMultiplication - 1);
			list.remove(indexForMultiplication - 1);
			list.add(indexForMultiplication - 1,
					String.valueOf(resultMultiplication));
		}
		double number1 = 0;
		double number2 = 0;
		double finalResult = 0;
		String operation;
		// eger tek i�lem kald�ysa carpma bolme ve us ald�ktan sonra
		if (list.size() == 1) {
			finalResult = Double.parseDouble(list.get(0));
		} else {

			while (list.size() > 1) {
				String s = list.get(0).toString();
				number1 = Double.parseDouble(s);

				operation = list.get(1).toString();

				s = list.get(2).toString();
				number2 = Double.parseDouble(s);

				if (operation.equals(plus)) {
					finalResult = number1 + number2;
					list.remove(0);
					list.remove(0);
					list.remove(0);
					list.add(0, String.valueOf(finalResult));
				} else if (operation.equals(minus)) {
					finalResult = number1 - number2;
					list.remove(0);
					list.remove(0);
					list.remove(0);
					list.add(0, String.valueOf(finalResult));
				}

			}

		}
		return finalResult;
	}
}
