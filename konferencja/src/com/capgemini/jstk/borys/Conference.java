package com.capgemini.jstk.borys;

import java.util.List;
import java.util.Scanner;

public class Conference {
	
	

	public Conference() {
		wyborUzytkownika();
	}

	/**
	 * Metoda pobiera od uzytkownika litere lub liczbe i na tej podstawie
	 * wykonywane sa dalsze dzialania programu
	 */
	private void wyborUzytkownika() {

		Scanner wybor = new Scanner(System.in);
		List<PersonImpl> listaZdanymiWejsciowymi = PersonHelper.odczyt("konferencja.csv");

		/* MENU WYBORU */
		String x = "";
		int n = listaZdanymiWejsciowymi.size();
		System.out.print("Wprowadz litere lub cyfre (cyfre jako dzielnik " + n + " ): ");
		if (wybor.hasNextInt()) {

			int dzielnik = wybor.nextInt();
			// System.out.println(x);
			if (dzielnik != 0 && n % dzielnik == 0 && dzielnik < n) {
				System.out.println("Utworzono  " + dzielnik + " list po " + n/dzielnik + " elementow" );
				List<PersonImpl> listaSortowanaNazwisk = PersonHelper.sortowanieNazwisk(listaZdanymiWejsciowymi);
				List<List<PersonImpl>> ListaListPosortowanych = PersonHelper.stworzsPodList(dzielnik, listaSortowanaNazwisk);
				for (int i = 0; i < ListaListPosortowanych.size(); i++) {
						PersonHelper.zapisDoPliku(ListaListPosortowanych.get(i), ("podLista "+i));
						System.out.println("------------------------------------------------------------------------------");
						PersonHelper.wyswietlListe(ListaListPosortowanych.get(i));
				}
			} else {
				System.err.println("Wprowadziles niepoprawna liczbe, wybierz ponownie co chcesz zrobic.");
				wyborUzytkownika();
			}

		} else if (wybor.hasNextLine()) {

			x = wybor.nextLine();
			char y = x.charAt(0);
			System.out.println("Sortuje po " + y);

			List<PersonImpl> listaZnazwiskami = PersonHelper.wyszukiwanieNazwisk(listaZdanymiWejsciowymi, y);
			List<PersonImpl> listaZnazwiskamiSortowana = PersonHelper.sortowanieImion(listaZnazwiskami);
			PersonHelper.zapisDoPliku(listaZnazwiskamiSortowana, "uczestnicy_litera");
			PersonHelper.wyswietlListe(listaZnazwiskami);
			if(listaZnazwiskami.size()==0)
				System.out.println("brak elementow na liscie");

		}
		wybor.close();
	}

}
