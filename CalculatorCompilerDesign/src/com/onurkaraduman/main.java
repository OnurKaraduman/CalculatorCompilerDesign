package com.onurkaraduman;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle.Control;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.onurkaraduman.engine.LexicalAnalyser;
import com.onurkaraduman.engine.ProgressOperation;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out
				.println("Islemlerinizi istediginiz gibi yazabilirsiniz\nComment baslangic isareti:!\nComment sonlandirma isareti:!\nGecerli islemler:üs(^), topla(+), ciýkarma(-), carpma(*), bolme(/), parantez()");
		System.out.println("\nIsleminizi giriniz:\n");
		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		ArrayList<String> list = new ArrayList<String>();
		LexicalAnalyser lexAnalyzer = new LexicalAnalyser(a);
		if (lexAnalyzer.startAnalyse()) {
			System.out.println("Ýlk Analiz Basarili");
			if (!lexAnalyzer.controlTokenOrder()) {
				System.out.println("Analiz Hatasi");
				lexAnalyzer.sysOutErrors();
			} else {
				list = lexAnalyzer.getOrderedList();
				ProgressOperation po = new ProgressOperation(list);

				System.out.println(po.resultOfAllText);
			}

		} else
			System.out.println("Analiz Hatasi");
	}
}
