package com.arati.stringcalculator;

public class StringCalculator {

	public int add(String addNumbers) throws Exception {
		String splitRegEx = regExWithDelimiter(addNumbers);
		try {
			if (addNumbers == "") {
				return 0;
			}
			if (validInput(addNumbers,createValidateInputRegEx(addNumbers))) {
				int sum = 0;
				String[] numbersSplittedOnComma = addNumbers.split(splitRegEx);

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

	public String createValidateInputRegEx(String addNumbers) {
		String validateInputRegEx = "^\\d*(((\\n)*\\d*(,)*)|((,)*\\d*(\\n)*))*\\d+$";
		if (isDifferentDelimiter(addNumbers)) {
			String differentDelimiter = getDelimiter(addNumbers);
			return validateInputRegEx.replace(",", differentDelimiter);
		}
		return validateInputRegEx;
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

	private boolean validInput(String addNumbers, String validateInputRegEx) throws Exception {
		if (!addNumbers.matches(validateInputRegEx)) {
			throw new Exception("Invalid input");
		} else
			return addNumbers.matches(validateInputRegEx);
	}

	public boolean isDifferentDelimiter(String addNumbers) {
		return addNumbers.startsWith("//");
	}

}
