package com.arati.reverseromannumerals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseRomanNumeral {
	private Map<String, Integer> romanToArabicMap;

	public ReverseRomanNumeral() {
		this.romanToArabicMap = romanToArabicMap();

	}

	private Map<String, Integer> romanToArabicMap() {
		Map<String, Integer> romanToInt = new HashMap<String, Integer>();
		romanToInt.put("I", 1);
		romanToInt.put("V", 5);
		romanToInt.put("X", 10);
		romanToInt.put("IV", 4);
		romanToInt.put("IX", 9);
		romanToInt.put("L", 50);
		romanToInt.put("XL", 40);
		romanToInt.put("C", 100);
		romanToInt.put("XC", 90);
		return romanToInt;
	}

	public Integer convert(String romanNumeral) {
		int arabicNumber = romanToArabicFromMap(romanNumeral, this.romanToArabicMap);
		return arabicNumber == 0 ? romanToArabic(romanNumeral, arabicNumber) : arabicNumber;
	}

	private Integer romanToArabicFromMap(String romanNumeral, Map<String, Integer> romanToArabicMap) {
		return romanToArabicMap.containsKey(romanNumeral) ? romanToArabicMap.get(romanNumeral) : 0;
	}

	private int romanToArabic(String romanNumeral, int arabicNumber) {
		String regEx = regExBuilder();
		String nonUniqueRomanNumeral = romanNumeral.split(regEx).length > 0 ? romanNumeral.split(regEx)[0] : "";
		if (nonUniqueRomanNumeral != "") {
			arabicNumber = romanToArabicNonUnique(nonUniqueRomanNumeral, romanToArabicMap);
		}

		final List<String> uniqueNumerals = getUniqueRomanDigits(romanNumeral);
		if (!uniqueNumerals.isEmpty()) {
			for (String uniqueNumeral : uniqueNumerals) {
				arabicNumber += this.romanToArabicMap.get(uniqueNumeral);
			}
		}
		return arabicNumber;
	}
	
	private int romanToArabicNonUnique(String romanNumeral, Map<String, Integer> romanToArabicMap) {
		int arabicNumber = 0;
		for (int index = romanNumeral.length() - 1; index >= 0; index--) {
			char romanDigit = romanNumeral.charAt(index);
			arabicNumber += romanToArabicMap.get(String.valueOf(romanDigit));
		}
		return arabicNumber;
	}
	
	private List<String> getUniqueRomanDigits(String romanNumeral) {
		List<String> uniqueNumerals = uniqueRomaNumerals();

		List<String> uniqueRomanNumerals = new ArrayList<String>();

		for (String uniqueNumeral : uniqueNumerals) {
			if (romanNumeral.indexOf(uniqueNumeral) >= 0) {
				uniqueRomanNumerals.add(uniqueNumeral);
			}
		}
		return uniqueRomanNumerals;
	}

	private String regExBuilder() {
		StringBuilder builder = new StringBuilder();

		for (String uniqueNumeral : uniqueRomaNumerals()) {
			builder.append("(");
			builder.append(uniqueNumeral);
			builder.append(")|");
		}

		return builder.deleteCharAt(builder.length() - 1).toString();

	}

	private List<String> uniqueRomaNumerals() {
		List<String> uniqueNumerals = new ArrayList<String>();
		uniqueNumerals.add("IV");
		uniqueNumerals.add("IX");
		uniqueNumerals.add("XL");
		uniqueNumerals.add("XC");
		return uniqueNumerals;
	}

}
