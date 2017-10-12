package com.capgemini.jstk.borys;

public class GameMain {

	public static void main(String[] args) {
		GameImpl test = new GameImpl(); // wprowadzic wymiary gdy uzywamy parametrycznego i uzywac generatora
		test.mapaTestowa();    //mapa testowa
		 
		 
		System.out.println("******************* TESTOWA TABLICA ******************* "); 
		test.rysujMape();
		// System.out.println(test.sprawdzOtoczenie(1, 1)); //test szukania sasiadow
		
		/* Petla dla zadanej tablicy  */
		
		while(test.sprawdzCzyCosZyje())
		{
			test.nastepnaIteracja();
			test.rysujMape();
			
		}
		
		
		
		System.out.println("******************* LOSOWE DANE ******************* ");
		
		GameImpl testGen = new GameImpl(4,4); 
		
		testGen.mapaRandomGenerator();
		testGen.rysujMapePoczatkowa();
		/* Petla dla generatora losowego  */
		
		boolean a =true;
		while (testGen.sprawdzCzyCosZyje() && a ) {
			a=	testGen.nastepnaIteracja();
			testGen.rysujMape();
			if(a==false)
				System.out.println("Wygenerowanie petli nieskonczonej, przerwanie");
			
				
		}
		//testGen.rysujMapePoczatkowa();
		
	}

}
