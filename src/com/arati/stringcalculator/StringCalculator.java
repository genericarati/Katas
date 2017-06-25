package com.arati.stringcalculator;

import java.util.regex.Pattern;

public class StringCalculator {

	public int add(final String addNumbers) throws Exception {
		try {
			if (addNumbers == "") {
				return 0;
			}
			String stringToOperateOn = getStringToOperateOn(addNumbers);
			
			if (validInput(stringToOperateOn,createValidateInputRegEx(stringToOperateOn))) {
				int sum = 0;
				String splitRegEx = regExWithDelimiter(addNumbers);
				String[] numbersSplittedOnComma = stringToOperateOn.split(splitRegEx);

				if (numbersSplittedOnComma.length > 0) {
					for (int index = 0; index < numbersSplittedOnComma.length; index++) {
						if (numbersSplittedOnComma[index] != "") {
							sum += Integer.parseInt(numbersSplittedOnComma[index]);
						}
					}
				}
				return sum;
			}

		} catch (Exception e) {
			throw e;
		}
		return 0;
	}

	public String getStringToOperateOn(final String addNumbers) {
		String newString = addNumbers.replace("\n", "\\n");
		if (isDifferentDelimiter(addNumbers)) {
			newString = newString.substring(2);
		}
		return newString;
	}

	public Pattern createValidateInputRegEx(String addNumbers) {
		String digit = "\\d";
		String newLine = "\\n";
		String validateInputRegEx = "^(" + digit + ")*(((" + newLine + ")*(" + digit + ")*(,)*)|((,)*(" + digit + ")*("
				+ newLine + ")*))*(" + digit + ")+$";
		if (isDifferentDelimiter(addNumbers)) {
			String differentDelimiter = getDelimiter(addNumbers);
			return Pattern.compile(validateInputRegEx.replace(",", differentDelimiter));
		}
		System.out.println(validateInputRegEx);

		return Pattern.compile(validateInputRegEx);
	}

	private String regExWithDelimiter(String addNumbers) {
		String splitRegEx;
		if (isDifferentDelimiter(addNumbers)) {
			splitRegEx = "(".concat(getDelimiter(addNumbers)).concat(")|(\\n)");
		} else {
			splitRegEx = "(,)|(\\n)";
		}
		return splitRegEx;
	}

	private String getDelimiter(String addNumbers) {
		return addNumbers.substring(2, 3);
	}

	private boolean validInput(String addNumbers, Pattern pattern) throws Exception {

		if (!pattern.matcher(addNumbers).matches()) {
			throw new Exception("Invalid input");
		}
		return pattern.matcher(addNumbers).matches();
	}

	public boolean isDifferentDelimiter(String addNumbers) {
		return addNumbers.startsWith("//");
	}

}
