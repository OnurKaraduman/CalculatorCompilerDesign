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

	// alýnan arrayin icindeki parantezlere gore iþlemleri rekursif olarak yapan
	// fonksiyonumuz
	// mesela en içteki parantezi bulup, bunun içindeki iþlemleri yapa yapa en
	// dýþ sarmaldaki paranteze kadar gelecek
	// içine parantezin bulundugu indisi alarak yoluna devam edecek

	// parantez içlerindeki iþlemelri yapan rekursif fonksiyonumuz
	public int recursionFunctionForBracket(int a) {
		ArrayList<String> arr2 = new ArrayList<String>();

		String s = "";
		for (int i = a; i < listAll.size(); i++) {

			// Eger parantez açýlmýþ ise
			if (listAll.get(i).equals(bracketOpen)) {

				i = recursionFunctionForBracket(i + 1);
				// parantezin içinden gelen degeri bir onceki listemizin icine
				// ekliyoruz
				arr2.add(String.valueOf(result));

				// Eger parantez kapanmýþ ise
			} else if (listAll.get(i).equals(bracketClose)) {

				// parantezin içindeki iþlemi yapýp global degisken olan result
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
		// ilk baþta us alma iþlemini gerçekleþtirelim
		// us aldýkça o iþlemi silip sonucu o indise yazalým
		while ((indexForPower = list.lastIndexOf(power)) > -1) {

			resultPower = Math.pow(
					Double.parseDouble(list.get(indexForPower - 1)),
					Double.parseDouble(list.get(indexForPower + 1)));

			// us alma iþlemi 3 adet list elemaný arasýnda oldugu için iþlemi
			// yapýp bu elemanlarý listeden
			// çýkarýyoruz
			list.remove(indexForPower - 1);
			list.remove(indexForPower - 1);
			list.remove(indexForPower - 1);
			list.add(indexForPower - 1, String.valueOf(resultPower));

		}

		int indexForDevision;
		double resultDivision = 0;
		// bolme iþlemlerini yap
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
		// çarpma islemlerini yap
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
		// eger tek iþlem kaldýysa carpma bolme ve us aldýktan sonra
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
