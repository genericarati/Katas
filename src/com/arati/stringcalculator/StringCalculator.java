package com.arati.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

	public final String validateInputRegEx = "^((\\n*\\d*(,)*)|((,)*\\d*\\n*))*\\d+$";

	public int add(final String addNumbers) throws Exception {
		try {
			if (addNumbers == "") {
				return 0;
			}
			Pattern inputRegEx = createValidateInputRegEx(addNumbers);
			String stringToOperateOn = getStringToOperateOn(addNumbers);
			if (validInput(stringToOperateOn, inputRegEx)) {
				String splitRegEx = regExWithDelimiter(addNumbers);
				String[] numbersSplittedOnComma = stringToOperateOn.split(splitRegEx);
				return getSum(numbersSplittedOnComma);
			}

		} catch (Exception e) {
			throw e;
		}
		return 0;
	}

	private int getSum(String[] numbersSplittedOnComma) {
		int sum = 0;
		if (numbersSplittedOnComma.length > 0) {
			for (int index = 0; index < numbersSplittedOnComma.length; index++) {
				if (isValidNumberToAdd(numbersSplittedOnComma, index)) {
					sum += Integer.parseInt(numbersSplittedOnComma[index]);
				}
			}
		}
		return sum;
	}

	private boolean isValidNumberToAdd(String[] numbersSplittedOnComma, int index) {
		return numbersSplittedOnComma[index] != "" && numbersSplittedOnComma[index].length() > 0
				&& Integer.parseInt(numbersSplittedOnComma[index]) <= 1000;
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

	public String getDelimiter(String addNumbers) {
		return addNumbers.substring(addNumbers.indexOf('[')+1, addNumbers.indexOf(']'));
	}

	public boolean validInput(String addNumbers, Pattern pattern) throws Exception {
		List<String> listOfNegativeNumbers = getListOfNegativeNumbers(addNumbers);
		if (listOfNegativeNumbers.size() > 0) {
			String mergedString = listOfNegativeNumbers.stream().collect(Collectors.joining(","));
			throw new Exception("negatives not allowed" + mergedString);
		}
		if (!pattern.matcher(addNumbers).matches()) {
			throw new Exception("Invalid input");
		}
		return pattern.matcher(addNumbers).matches();
	}

	public boolean isDifferentDelimiter(String addNumbers) {
		return addNumbers.startsWith("//");
	}

	public List<String> getListOfNegativeNumbers(String addNumbers) {
		Pattern pattern = Pattern.compile("-\\d*");
		Matcher matcher = pattern.matcher(addNumbers);
		List<String> negativeNumbers = new ArrayList<String>();
		while (matcher.find()) {
			negativeNumbers.add(addNumbers.substring(matcher.start(), matcher.end()));
		}
		return negativeNumbers;
	}

}
