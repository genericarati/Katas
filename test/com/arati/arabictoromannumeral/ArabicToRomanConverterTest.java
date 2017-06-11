package com.arati.arabictoromannumeral;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ArabicToRomanConverterTest {
	Map<Integer, String> romanToArabicMap = new HashMap<Integer, String>();

	@Before
	public void setUp() {
		romanToArabicMap.put(1, "I");
		romanToArabicMap.put(2, "II");
		romanToArabicMap.put(3, "III");
		romanToArabicMap.put(4, "IV");
		romanToArabicMap.put(5, "V");
		romanToArabicMap.put(6, "VI");
		romanToArabicMap.put(7, "VII");
		romanToArabicMap.put(9, "IX");
		romanToArabicMap.put(10, "X");
		romanToArabicMap.put(14, "XIV");
		romanToArabicMap.put(33, "XXXIII");
		romanToArabicMap.put(39, "XXXIX");
		romanToArabicMap.put(59, "LIX");
		romanToArabicMap.put(99, "XCIX");
	}

	@Test
	public void convert() {
		ArabicToRomanConverter arabicToRomanConverter = new ArabicToRomanConverter();

		for (Integer i : romanToArabicMap.keySet()) {
			assertEquals(romanToArabicMap.get(i), arabicToRomanConverter.convert(i));
		}
	}

}
