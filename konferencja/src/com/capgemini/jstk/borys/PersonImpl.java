package com.capgemini.jstk.borys;

import java.util.Date;

// TODO interfejs Person do wyjebania, i zmiana klasy z PersonImp na Person
public class PersonImpl {

	private String imie;
	private String nazwisko;
	private int wiek;
	private Date dataUrodzenia;

	public PersonImpl(String imie, String nazwisko, Date datka, int wiek) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.dataUrodzenia = datka;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public int getWiek() {
		return wiek;
	}

	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}

	@Override
	public String toString() {
		return imie + " " + nazwisko + " " + wiek;
	}

}
