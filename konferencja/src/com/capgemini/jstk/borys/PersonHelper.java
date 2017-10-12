package com.capgemini.jstk.borys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PersonHelper {

	/**
	 * Metoda wyznaczajaca wiek na podstawie podanego obiektu typu kalendarz
	 * (wykonuje logike biznesowa ?)
	 * 
	 * @param kalendarz
	 * @return zwraca liczbe lat pojedynczej osoby typu int
	 */
	public static int wyznaczWiek(Calendar kalendarz) {

		return LocalDate.now().getYear() - kalendarz.get(Calendar.YEAR);

	}

	/**
	 * Odczytuje dane z podanego pliku "plik.csv" a nastepnie umieszcza je w
	 * liscie w identycznej kolejnosci
	 * 
	 * @return zwraca liste obiektow "Person" odczytana z pliku wejsciowego
	 */
	public static List<PersonImpl> odczyt(String nazwaPliku) {
		FileReader fr = null;
		String linia = "";

		List<PersonImpl> lista2 = new ArrayList<>();

		try {
			fr = new FileReader(nazwaPliku);
		} catch (FileNotFoundException e1) {
			System.out.println("blad otwarcia pliku");
			System.exit(1);
		}

		BufferedReader bfr = new BufferedReader(fr);

		try {
			while ((linia = bfr.readLine()) != null) {

				String[] splita = linia.split(",");
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				String input = splita[2];

				Date t;
				Calendar kalendarz = Calendar.getInstance();
				try {

					t = ft.parse(input);// konwersja
					kalendarz.setTime(t);
					// int wiek = wyznaczWiek(kalendarz);
					int wiek = PersonHelper.wyznaczWiek(kalendarz);
					lista2.add(new PersonImpl(splita[0], splita[1], t, wiek));

					// System.out.println(t);
				} catch (ParseException e) {
					System.out.println("blad z uzyciem metody parase  " + ft);
				}
			}

		} catch (IOException e) {
			System.out.println("blad odczytu z pliku!");
			System.exit(2);
		}

		try {
			fr.close();
		} catch (IOException e1) {
			System.out.println("blad zamykania pliku");
			System.exit(3);
		}
		return lista2;

	}

	/**
	 * Metoda zapisuje podana liste do plku o nazwie "nazwaPliku" w formacie csv
	 * 
	 * @parm pobiera liste zawierajaca obiekty typu person oraz string z nazwa
	 *       pliku bez jego rozszerzenia
	 */

	public static void zapisDoPliku(List<PersonImpl> lista, String nazwaPliku) {
		String dane = "";

		FileWriter fw = null;

		try {
			fw = new FileWriter(nazwaPliku + ".csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter bw = new BufferedWriter(fw);
		try {
			for (int i = 0; i < lista.size(); i++) {
				dane = lista.get(i).getImie() + "," + lista.get(i).getNazwisko() + "," + lista.get(i).getWiek();
				bw.write(dane);
				bw.newLine();
				// System.out.println(dane);
				dane = "";
				// System.out.println("zapisano linie");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda implementujaca sortowanie (bubble sort) listy według alfabetycznej
	 * kolejnosci nazwisk
	 * 
	 * @parm przyjmuje liste zawierajaca obiekty typu Person
	 * 
	 * @return zwraca posortowana liste zgodnie z przyjeteym kryterium
	 */

	public static List<PersonImpl> sortowanieNazwisk(List<PersonImpl> lista) {
		for (int i = (lista.size()); i > 1; i--) {
			for (int j = 0; j < i - 1; j++) {

				if (lista.get(j).getNazwisko().compareTo(lista.get(j + 1).getNazwisko()) > 0)

				{
					PersonImpl temp = lista.get(j);
					lista.set(j, lista.get(j + 1)); // wczesniejsze problemy
													// tutaj polegały na
													// bezmyslnym uzywaniu
													// metody add zamiast set
					lista.set(j + 1, temp);

				}
			}
		}

		return lista;
	}

	/**
	 * Metoda wykonujaca sortowanie listy według alfabetycznej kolejnosci imion
	 * 
	 * @parm przyjmuje liste zawierajaca obiekty typu Person
	 * 
	 * @return zwraca posortowana liste zgodnie z przyjeteym kryterium
	 */

	public static List<PersonImpl> sortowanieImion(List<PersonImpl> lista) {
		lista.sort(Comparator.comparing(PersonImpl::getImie));
		return lista;
	}

	/**
	 * Metoda wyszukuje osoby ktorych nazwiska zaczynaja sie na podana litere
	 * 
	 * @parm lista obiektow typu person oraz litera na podstawie ktorej
	 *       dokonujemy filtracji listy
	 * @return zwraca nowa liste zawierajaca tylko obiekty w ktorych nazwiko
	 *         spelnia warunki wyszukiwania
	 */
	public static List<PersonImpl> wyszukiwanieNazwisk(List<PersonImpl> lista, char N) {
		List<PersonImpl> nowaLista = new ArrayList<>();
		N = Character.toUpperCase(N);
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNazwisko().charAt(0) == N) {
				nowaLista.add(lista.get(i));
			}

		}
		return nowaLista;
	}

	/**
	 * Metoda wyswietlajaca w konsoli liste osob wraz z ich danymi
	 * 
	 * @parm przyjmuje liste przechowujaca obiekty typu Person
	 */
	public static void wyswietlListe(List<PersonImpl> persons) {
		for (PersonImpl personImpl : persons) {
			System.out.println(personImpl.toString());
		}
	}

	/**
	 * Metoda dzieli wprowadzona liste na rowne podlisty zgodnie z wprowadzonym
	 * dzielnikiem
	 * 
	 * @param dzielnik
	 *            oraz lista obiektow typu personimpl
	 * @return zwraca liste lis obiektow typu personimpl
	 */

	public static List<List<PersonImpl>> stworzsPodList(int dzielnik, List<PersonImpl> list) {
		List<List<PersonImpl>> listaOsob = new ArrayList<>();
		if (dzielnik != 0 && list.size() % dzielnik == 0) {
			int rozmiar = list.size() / dzielnik;
			for (int i = 0; i < dzielnik; i++) {
				listaOsob.add(list.subList(i * rozmiar, (i + 1) * rozmiar));
			}
		}
		return listaOsob;
	}

}
