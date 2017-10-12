package com.capgemini.jstk.borys;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class CalculatorImpl implements Calculator {
	/*
	 * Metoda pobiera stringa wprowadzonego przez uzytkownika w konsoli oraz
	 * sprawdza jego poprawny format. W przypadku blednego formatu, wywoluje sie
	 * ponownie
	 * 
	 * @return zwraca pobranego stringa
	 */
	public String pobieranieStringa() {
		System.out.println("podaj stringa o poprawnym formacie (zakres operacji ograniczony do pojemnosci INT): ");
		Scanner wejscie = new Scanner(System.in);
		String input = wejscie.nextLine();
		input = usuwanieSpacji(input);
		if (input.equals("")) {
			input = "0";
			wejscie.close();
			return input;
		}
		for (int i = 0; i < input.length(); i++) {
			if (!sprawdzCzyJestLiczbaLubZnakiem((int) input.charAt(i))) {
				System.out.print("zly format, podaj ponownie: ");
				input = pobieranieStringa();
				wejscie.close();
				break;
			}
		}
		wejscie.close();
		return input;
	}

	/**
	 * Metoda sprawdza czy znak nalezacy do wprowadzonego stringa, spelnia
	 * odpowiednie warunki
	 * 
	 * @param znak
	 *            z wprowadzonego stringa
	 * @return true albo false
	 */
	private boolean sprawdzCzyJestLiczbaLubZnakiem(int znak) {
		return (57 >= znak && 48 <= znak) || znak == 43 || znak == 45;
	}

	/*
	 * Metoda usuwa spacje oraz tabulator w stringu wprowadzonym przez
	 * uzytkownika
	 * 
	 * @param string z dzialaniem do wykonania
	 * 
	 * @return zwraca pobranego stringa bez bialych znakow (spacja, tab)
	 */

	private String usuwanieSpacji(String input) {
		// TODO sprawdz to
		return input.replace(" ", "").replace("	", "");
	}

	/*
	 * Metoda umieszcza w nowo stworzonej liscie operacji znaki wyciagniete ze
	 * stringa w kolejnosci wystepowan tych znakow
	 * 
	 * @param string wejsciowy
	 * 
	 * @return zwraca Liste zawierajaca znaki z operacjami do wykonania
	 */

	private List<String> wyznaczDzialania(String input) {
		List<String> listaOperacji = new ArrayList<String>();
		String[] liczby = input.split("\\d+");
		for (int i = 0; i < liczby.length; i++) {
			listaOperacji.add(liczby[i]);
		}
		return listaOperacji;
	}

	/*
	 * Metoda umieszcza w nowo stworzonej liscie liczb, cyfry wyciagniete ze
	 * stringa w kolejnosci ich wystapien
	 * 
	 * @param string wejsciowy
	 * 
	 * @return zwraca Liste zawierajaca cyfry na ktorych wykonamy dzialania
	 */

	private List<Long> wyznaczLiczby(String input) {
		List<Long> listaLiczb = new ArrayList<Long>();
		String[] liczby = input.split("[-+ ]+");
		for (int i = 0; i < liczby.length; i++) {
			listaLiczb.add(Long.parseLong(liczby[i]));
		}

		return listaLiczb;
	}

	/*
	 * Metoda umieszcza w nowo stworzonej liscie liczb, cyfry wyciagniete ze
	 * stringa w kolejnosci ich wystapien
	 * 
	 * @param lista z cyframi oraz lista z operacjami do wykonania
	 * 
	 * @return zwraca liczbe typu int zawierajaca wynik wszystkich operacji
	 */
	/// TODO uproscic
	private Long wykonajOperajce(List<String> listaOperacji, List<Long> listaLiczb) {

		Long wynik = 0L;
		if (listaLiczb.size() == 0)
			wynik = 0L;
		if (listaOperacji.size() == 0) {
			wynik = listaLiczb.get(0);
		} else {
			for (int i = 1; i < listaOperacji.size(); i++) {
				if (wynik == 0 && i == 1) {
					if (listaOperacji.get(i).equals("+")) {
						wynik = listaLiczb.get(i - 1) + listaLiczb.get(i) + wynik;
					} else if (listaOperacji.get(i).equals("-")) {
						wynik = listaLiczb.get(i - 1) - listaLiczb.get(i);
					}
				} else {
					if (listaOperacji.get(i).equals("+")) {
						wynik = listaLiczb.get(i) + wynik;
					}

					else if (listaOperacji.get(i).equals("-")) {
						wynik = wynik - listaLiczb.get(i);

					}
				}
			}
		}
		return wynik;

	}

	/**
	 * Metoda wyznacza wyniki dla kolejnych stringow podanych w liscie operacji
	 * @param lista operacji do wykonania
	 * @return zwraca liste wynikow 
	 */
	public List<Long> wykonajKilkaOperacje(List<String> dzialania) {
		List<Long> wyniki = new ArrayList<>();
		for (String dzialanie : dzialania) {
			wyniki.add(wyznaczWynik(dzialanie));
		}
		return wyniki;
	}

	/**
	 * Metoda wyznacza operacje zawarta w podanym stringu
	 * @param przyjmuje stringa z dzialaniem do wykonania
	 * @return zwraca wynik operacji zawartej w podanym stringu 
	 */
	public Long wyznaczWynik(String dzialanie) {
		dzialanie = usuwanieSpacji(dzialanie);
		if(dzialanie == "")
			return 0L;
		return wykonajOperajce(wyznaczDzialania(dzialanie), wyznaczLiczby(dzialanie));
	}

}
