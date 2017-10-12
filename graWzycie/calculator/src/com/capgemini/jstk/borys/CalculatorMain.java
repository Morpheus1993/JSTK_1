
package com.capgemini.jstk.borys;

//import java.util.ArrayList;
//import java.util.List;

public class CalculatorMain {

	public static void main(String[] args) {
		//List<Long> listaLiczb = new ArrayList<Long>();
		//List<String> listaOperacji = new ArrayList<String>();

		CalculatorImpl test = new CalculatorImpl();
		String dane = test.pobieranieStringa();
		//listaLiczb = test.wyznaczLiczby(dane);
		//listaOperacji = test.wyznaczDzialania(dane);

		//System.out.println("Wynik: " + test.wykonajOperajce(listaOperacji, listaLiczb));
		System.out.println("Wynik: " + test.wyznaczWynik(dane));

	}

}
