package com.capgemini.jstk.borys.testy;

import org.junit.Before;
import org.junit.Test;


import com.capgemini.jstk.borys.CalculatorImpl;

import org.junit.Assert;

public class CalculatorImplTest {

	private CalculatorImpl testowanyObiekt;

	@Before
	public void init() {
		testowanyObiekt = new CalculatorImpl();
	}

	@Test
	public void shouldCheackScoreForEmptyInput() {
		// given
		String dzialanie = "";
		// when
		long wynik = testowanyObiekt.wyznaczWynik(dzialanie);
		// then
		Assert.assertEquals(0, wynik);
	}

	@Test
	public void shouldCheackPositiveReturnedScore() {
		// given
		String dzialanie = "2+2";
		// when
		long wynik = testowanyObiekt.wyznaczWynik(dzialanie);
		// then
		Assert.assertEquals(4, wynik);
		
		
	}
	@Test 
	public void shouldReturnMinusScore()
	{
		String dzialanie = "2-4";
		// when
		long wynik = testowanyObiekt.wyznaczWynik(dzialanie);
		// then
		Assert.assertEquals(-2, wynik);
	}

	
	@Test 
	public void shouldReturnScoreWithSpace()
	{
		String dzialanie = "2 - 4	+ 2";
		// when
		long wynik = testowanyObiekt.wyznaczWynik(dzialanie);
		// then
		Assert.assertEquals(0, wynik);
	}
	
	@Test
	public void shouldReturnLongScore()
	{
		String dzialanie = "222222222 - 222222222222";
		// when
		long wynik = testowanyObiekt.wyznaczWynik(dzialanie);
		// then
		Assert.assertEquals(-222000000000L, wynik);
	}
	
	@Test
	public void shouldReturnOnlyNumber()
	{
		String dzialanie = "2";
		// when
		long wynik = testowanyObiekt.wyznaczWynik(dzialanie);
		// then
		Assert.assertEquals(2L, wynik);
	}

	

}
