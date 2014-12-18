package com.onurkaraduman.engine;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.onurkaraduman.entity.Error;
import com.onurkaraduman.entity.ErrorList;
import com.onurkaraduman.entity.Token;

public class LexicalAnalyser {

	private int TOKEN_TYPE_NUMBER = 0;
	private int TOKEN_TYPE_OPERATION = 1;

	private String bracketOpen = "(";
	private String bracketClose = ")";
	private String plus = "+";
	private String minus = "-";
	private String times = "*";
	private String obelus = "/";
	private String power = "^";
	private String comment = "!";
	private String space = " ";

	private ArrayList<String> operationsAll;
	private String textAll;
	private ArrayList<String> listAll;
	private ErrorList errorList;
	private ArrayList<Token> tokens;

	private ArrayList<String> orderedList;
	private ArrayList<Error> errors;

	public LexicalAnalyser(String textAll) {
		super();
		this.textAll = textAll;
		listAll = new ArrayList<String>();
		errorList = new ErrorList();
		listAll = stringToList(textAll);
		tokens = new ArrayList<Token>();
		errors = new ArrayList<Error>();
		orderedList = new ArrayList<String>();
		addToOperationList();

	}

	private void addToOperationList() {
		operationsAll = new ArrayList<String>();
		operationsAll.add(bracketOpen);
		operationsAll.add(bracketClose);
		operationsAll.add(plus);
		operationsAll.add(minus);
		operationsAll.add(times);
		operationsAll.add(obelus);
		operationsAll.add(power);
		operationsAll.add(comment);
		operationsAll.add(space);
	}

	// step1- once commentleri temizle
	// Commentleri kaldýr
	// Tokenlara ayýr

	public boolean startAnalyse() {
		return assignTokens();
	}

	// gelen string degeri arrayliste donusturuyoruz
	public ArrayList<String> stringToList(String s) {
		char[] c = s.toCharArray();
		listAll = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < c.length; i++) {

			// comment satýrlarýný kaldýr

			list.add(String.valueOf(c[i]));

		}
		return list;
	}

	// sayý olup olmadýgýný kontrol eden fonksiyon
	public boolean isDigit(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// operator iþareti olup olmadýgýný kontrol eden fonksiyon
	public boolean isOperator(String operator) {
		// operator.equals(bracketClose) || operator.equals(bracketOpen)
		if (operator.equals(plus) || operator.equals(minus)
				|| operator.equals(times) || operator.equals(obelus)
				|| operator.equals(power)) {
			return true;
		}
		return false;
	}

	public boolean assignTokens() {
		for (int i = 0; i < listAll.size(); i++) {
			String element = listAll.get(i);
			int tokenType;
			int startPosition;
			// eger boþluk gelmiþ ise
			if (element.equals(comment)) {
				i++;
				element = listAll.get(i);
				while (!element.equals(comment)) {
					
					if (i == listAll.size()) {
						i++;
						return true;
					}
					element = listAll.get(i);
					i++;
				}
			}
			
			if (element.equals(space)) {
				continue;
			} else if (operationsAll.contains(element)) {
				tokenType = TOKEN_TYPE_OPERATION;
				startPosition = i;
			} else if (isDigit(element)) {

				startPosition = i;
				// eger sayý birden cok basamakli ise
				try {
					String numbers = listAll.get(i + 1);
					if (isDigit(numbers)) {
						i++;
					}
					// cok basamaklý sayýyý tam bir sayý olarak al
					while (isDigit(numbers)) {
						i++;
						element += numbers;
						numbers = listAll.get(i);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
				tokenType = TOKEN_TYPE_NUMBER;
			} else {
				System.out.println(errorList.errorMap.get(4));
				return false;
			}
			Token token = new Token(element, startPosition, tokenType);
			tokens.add(token);
			orderedList.add(element);

		}
		return true;
	}

	public boolean controlTokenOrder() {
		for (int i = 0; i < tokens.size(); i++) {
			Error error = new Error();
			Token token = tokens.get(i);
			Token token2;
			if (token.getTokenType() == TOKEN_TYPE_NUMBER) {
				if ((i + 1) >= tokens.size()) {
					break;
				}
				token2 = tokens.get(i + 1);
				if (token2.getTokenType() != TOKEN_TYPE_OPERATION) {
					error.setError(errorList.errorMap.get(1));
					error.setErrorCode(1);
					error.setPosition(token2.getPosition());
					errors.add(error);
				} else if (token2.getTokenValue().equals(bracketOpen)) {
					error.setError(errorList.errorMap.get(1));
					error.setErrorCode(1);
					error.setPosition(token2.getPosition());
					errors.add(error);
				}
			} else if (token.getTokenType() == TOKEN_TYPE_OPERATION) {
				if ((i + 1) >= tokens.size()) {
					error.setError(errorList.errorMap.get(4));
					error.setErrorCode(4);
					error.setPosition(token.getPosition());
					errors.add(error);
					break;
				}
				token2 = tokens.get(i + 1);
				if (i == 0) {
					error.setError(errorList.errorMap.get(4));
					error.setErrorCode(4);
					error.setPosition(token.getPosition());
					errors.add(error);
				}
				boolean isOp = isOperator(token.getTokenValue());
				if (isOp) {
					if (isOperator(token2.getTokenValue())) {
						error.setError(errorList.errorMap.get(4));
						error.setErrorCode(4);
						error.setPosition(token.getPosition());
						errors.add(error);
					}
				}
			}
		}
		if (errors.size() > 0) {
			return false;
		}
		return true;
	}

	public void sysOutErrors() {
		for (int i = 0; i < errors.size(); i++) {
			Error erro = new Error();
			erro = errors.get(i);
			System.out.println("Hata Kodu:" + erro.getErrorCode()
					+ ". Hata posizyon: " + erro.getPosition()
					+ ". Hata Mesaji: " + erro.getError());
		}
	}

	public ArrayList<String> getOrderedList() {
		return orderedList;
	}

	public void setOrderedList(ArrayList<String> orderedList) {
		this.orderedList = orderedList;
	}

}
