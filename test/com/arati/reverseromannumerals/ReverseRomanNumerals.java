package com.arati.reverseromannumerals;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ReverseRomanNumerals {

	ReverseRomanNumeral reverseRomanNumeral = new ReverseRomanNumeral();
	Map<Integer, String> inputMap = new HashMap<>();

	@Before
	public void setUp() {
		
		inputMap.put(1, "I");
		inputMap.put(2, "II");
		inputMap.put(3, "III");
		inputMap.put(4, "IV");
		inputMap.put(5, "V");
		inputMap.put(6, "VI");
		inputMap.put(7, "VII");
		inputMap.put(10, "X");
		inputMap.put(11, "XI");
		inputMap.put(39, "XXXIX");
		inputMap.put(49, "XLIX");
		inputMap.put(50, "L");
		inputMap.put(61, "LXI");
		inputMap.put(94, "XCIV");

	}

	@Test
	public void romanShouldReturnArabic() {
		for (Integer input : inputMap.keySet()) {
			assertEquals(input, reverseRomanNumeral.convert(inputMap.get(input)));
		}
	}
	
	@Test
	public void romanNumeralSplit() {
		String regexMatch = "LXI";
		String regexMatch1 = "XLIX";
		String regex = "(IV)|(IX)|(XL)";
		
		assertEquals("LXI", regexMatch.split(regex)[0]);
		assertEquals(0, regexMatch1.split(regex).length);
		
	}

	
}
