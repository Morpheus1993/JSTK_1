package com.capgemini.jstk.borys;


import java.util.List;

public interface Person {


	public List<PersonImpl> sortowanieNazwisk(List<PersonImpl> lista);

	public List<PersonImpl> wyszukiwanieNazwisk(List<PersonImpl> lista, char N);

	public List<List<PersonImpl>> stworzsPodList(int dzielnik, List<PersonImpl> list);
	

}
