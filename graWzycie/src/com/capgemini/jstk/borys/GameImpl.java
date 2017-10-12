package com.capgemini.jstk.borys;

public class GameImpl implements Game{

	/**
	 * Pola klasy Game
	 */
	int x;
	int y;
	char zywa = 'O';
	char martwa = '.';
	boolean[][] map;
	int iteracje = 0;
	boolean[][] mapaPoczatkowa;

	/**
	 * Konstruktor do uzycia z testowa mapa
	 */
	public GameImpl() {
		this.x = 6;
		this.y = 6;
		map = new boolean[this.x][this.y];
		this.iteracje = 0;
	}

	/**
	 * Konstruktor parametryczny do inicjalizacji mapy o podanej wielkosci
	 * 
	 * @param dwie liczby int okreslajace rozmiar tablicy logicznej imitujacej
	 * mape
	 */

	public GameImpl(int x, int y) {
		this.x = x;
		this.y = y;
		map = new boolean[x][y];
	}

	/**
	 * Metoda testowa, modyfikujaca mape tzn wstawiajaca wartosci true w wybrane
	 * miejsca, do uzycia z konstruktorrem bezparametrycznym
	 */

	protected void mapaTestowa() {
		map[2][2] = true;
		map[0][1] = true;
		map[1][2] = true;
		map[3][0] = true;
		map[1][1] = true;

	}

	/**
	 * Metoda uzupelniajaca mape poczatkowa wartosciami true/false na podstawie generatora
	 * liczb losowych
	 */

	public void mapaRandomGenerator() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double a = Math.round(Math.random());
				if (a == 1)
					map[i][j] = true;
				else
					map[i][j] = false;

			}
		}
		mapaPoczatkowa = map;

	}

	/**
	 * Metoda wyznaczajaca liczbe sasiadow dla danego badanego punktu
	 * 
	 * @parm wspolrzedne x i y punktu ktorego otoczenie badamy
	 * 
	 * @return zwraca liczbe typu int zawierajaca informacje o ilosci sasiadow
	 */

	public int sprawdzOtoczenie(int xP, int yP)// xP polozenie xowe
												// sprawdzanego, yP tak samo tylko y
	{
		int zywiSasiedzi = 0;
		for (int i = xP - 1; i <= xP + 1; i++) // zaczynamy na lewo od
												// sprawdzanego punktu
		{
			if (i < 0 || i > x - 1)
				continue;
			for (int j = yP - 1; j <= yP + 1; j++) { // zaczynamy jeden wyzej
				if (j < 0 || j > y - 1 || (i == xP && j == yP))
					continue;
				if (map[i][j] == true)
					zywiSasiedzi = zywiSasiedzi + 1;
			}
		}

		return zywiSasiedzi;
	}

	/**
	 * Metoda sprawdzajaca pola na mapie i zwracajaca informacje czy na dowolnym
	 * polu z mapy jest wartosc true
	 * 
	 * @return zwraca wartosc logiczna true jezeli dowolne pole na mapie ma
	 * true, false jezeli zaden nie ma true
	 */

	public boolean sprawdzCzyCosZyje() 
	{
		boolean a = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == true) {
					a = true;
					break;
				}
			}
		}
		return a;
	}

	/**
	 * Metoda wykonujaca sprawdzenie warunkow zycia na calej mapie kolejno dla kazdego
	 * pola (komorki) Modyfikuje ona mape oraz parametr iteracje po wykonaniu
	 * calaj metody
	 * @return zwraca warunek logiczny false gdy wpadniemy w petle identycznych stanow
	 */

	public boolean nastepnaIteracja() {
		boolean[][] temp = new boolean[x][y];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				int a = sprawdzOtoczenie(i, j); 
				if (map[i][j] == true && a < 2) {
					temp[i][j] = false;
				} else if (map[i][j] == true && (a == 2 || a == 3))
					temp[i][j] = true;
				else if (map[i][j] == false && a == 3)
					temp[i][j] = true;
				else if (map[i][j] == true && (a >= 2 && a <= 3))
					temp[i][j] = false;
				a = 0;
			}
		}
		map = temp;
		if(map==temp || sprawdzCzyCosZyje()){
			iteracje = iteracje + 1;
			return false;
			
		}
		else 
		{
			iteracje = iteracje + 1;
			return true;
			
		}
	}
	/**
	 * Metoda rysuje w konsoli aktualna mape, zamieniajac wartosci true i false
	 * na odpowiednie wybrane znaki wyswietla rowniez, aktualna wartosc
	 * parametru iteracje
	 */

	public void rysujMape() {
		System.out.println("###########  iteracja: " + iteracje);
		System.out.println();

		for (boolean[] bs : map) {
			for (boolean b : bs) {
				if (b == true)
					System.out.print(zywa);
				else
					System.out.print(martwa);

			}
			System.out.println();
		}
		System.out.println();
		System.out.println("###########");
	}
	
	public void rysujMapePoczatkowa() {
		System.out.println("###########  ");
		System.out.println();

		for (boolean[] bs : mapaPoczatkowa) {
			for (boolean b : bs) {
				if (b == true)
					System.out.print(zywa);
				else
					System.out.print(martwa);

			}
			System.out.println();
		}
		System.out.println();
		System.out.println("###########");
	}
}
