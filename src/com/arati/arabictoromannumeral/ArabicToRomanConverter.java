package com.arati.arabictoromannumeral;

public class ArabicToRomanConverter {

	public String convert(Integer arabic) {

		String roman = "";
		Integer[] indexes = { 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romanNumerals = {"XC", "L", "XL", "X", "IX", "V", "IV", "I" };

			for (int i = 0; i < indexes.length; i++) {
				for (int index = arabic; index >= indexes[i]; index -= indexes[i]) {
					roman += romanNumerals[i];
					arabic -= indexes[i];
				}
			}

		return roman;
	}

}
