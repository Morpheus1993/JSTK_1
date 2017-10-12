package com.capgemini.jstk.borys;

public interface Game {

	public void mapaRandomGenerator();

	public int sprawdzOtoczenie(int xP, int yP);

	public boolean sprawdzCzyCosZyje();

	public boolean nastepnaIteracja();

	public void rysujMape();

	public void rysujMapePoczatkowa();

}
