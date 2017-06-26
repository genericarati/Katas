package com.arati.stringcalculator;

import java.util.regex.Pattern;

public class StringCalculator {
	
	public final String validateInputRegEx = "^((\\n*\\d*(,)*)|((,)*\\d*\\n*))*\\d+$";

	public int add(final String addNumbers) throws Exception {
		try {
			if (addNumbers == "") {
				return 0;
			}
			Pattern inputRegEx = createValidateInputRegEx(addNumbers);
			String stringToOperateOn = getStringToOperateOn(addNumbers);
			if (validInput(stringToOperateOn,inputRegEx)) {
				int sum = 0;
				String splitRegEx = regExWithDelimiter(addNumbers);
				String[] numbersSplittedOnComma = stringToOperateOn.split(splitRegEx);

				if (numbersSplittedOnComma.length > 0) {
					for (int index = 0; index < numbersSplittedOnComma.length; index++) {
						if (numbersSplittedOnComma[index] != "" && numbersSplittedOnComma[index].length() > 0) {
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
		String newString = addNumbers;
		if (isDifferentDelimiter(addNumbers)) {
			newString = newString.substring(3);
		}
		return newString;
	}

	public Pattern createValidateInputRegEx(String addNumbers) {
		
		if (isDifferentDelimiter(addNumbers)) {
			String differentDelimiter = getDelimiter(addNumbers);
			return Pattern.compile(validateInputRegEx.replace(",", differentDelimiter));
		}

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

	public boolean validInput(String addNumbers, Pattern pattern) throws Exception {

		if (!pattern.matcher(addNumbers).matches()) {
			throw new Exception("Invalid input");
		}
		return pattern.matcher(addNumbers).matches();
	}

	public boolean isDifferentDelimiter(String addNumbers) {
		return addNumbers.startsWith("//");
	}

}
