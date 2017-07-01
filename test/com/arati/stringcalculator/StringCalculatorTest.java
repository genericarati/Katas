package com.arati.stringcalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {

	@Test
	public void blankStringShouldReturn0() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(0, sum);
	}

	@Test
	public void OneShouldReturn1() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(1, sum);
	}
	
	@Test
	public void delimterOfAnyLength() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//[***]\n1***2***3";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(6, sum);
	}

	@Test
	public void getDelimiter(){
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//[***]\n1***2***3";
		String delimiter = stringCalculator.getDelimiter(addNumbers);
		assertEquals("***",delimiter);
	}

	@Test
	public void OneAndTwoShouldReturn3() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1,2";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(3, sum);
	}

	@Test
	public void FiveThreeOneShouldReturn9() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "5,3,1";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(9, sum);
	}

	@Test
	public void OneNewLineTwoCommaThreeShouldReturn6() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1\n2,3";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(6, sum);
	}

	@Test
	public void numberGreateThanOneThousandAreIgnored() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1\n2,3,\n2001";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(6, sum);
	}


	@Test
	public void PatternMatcher() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1\n2,3";
		String stringToOperateOn = stringCalculator.getStringToOperateOn(addNumbers);
		Pattern validateInputRegEx = stringCalculator.createValidateInputRegEx(stringToOperateOn);
		boolean validInput = stringCalculator.validInput(stringToOperateOn, validateInputRegEx);
		assertTrue(validInput);
	}

	@Test
	public void Pattern1Matcher() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//;\n1;2";
		String stringToOperateOn = stringCalculator.getStringToOperateOn(addNumbers);
		Pattern validateInputRegEx = stringCalculator.createValidateInputRegEx(addNumbers);
		boolean validInput = stringCalculator.validInput(stringToOperateOn, validateInputRegEx);
		assertTrue(validInput);
	}

	@Test
	public void SemiColonAndStringSumShouldReturnCorrectSum() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//;\n1;2";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(3, sum);
	}

	@Test
	public void getStringToOperateOn() {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//;\n1;2";
		assertEquals("\n1;2", stringCalculator.getStringToOperateOn(addNumbers));
	}

	@Test
	public void validString() {
		StringCalculator stringCalculator = new StringCalculator();
		String regex = "^((\\n*\\d*(;)*)|((;)*\\d*\\n*))*\\d+$";
		
		String s2 = "1\n2,3";
		assertTrue(s2.matches(stringCalculator.validateInputRegEx));
		
		String s = "1,\n,2,3";
		assertTrue(s.matches(stringCalculator.validateInputRegEx));
		
		String addNumbers = ";\n1;2";
		assertTrue(addNumbers.matches(regex));
		
		String regex1 = "^((\\n*\\d*(\\*){3})|((\\*){3}\\d*\\n*))*\\d+$";
		String input1 = "1***\n***2";
		assertTrue(input1.matches(regex1));
		
	}

	@Test
	public void invalidString() {
		StringCalculator stringCalculator = new StringCalculator();
		String s = "1,\n";
		assertFalse(s.matches(stringCalculator.validateInputRegEx));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldReturnException() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1,\n";
		thrown.expectMessage("Invalid input");
		stringCalculator.add(addNumbers);

	}

	@Test
	public void shouldReturnTrueIfDifferentDelimiterExistsThanComma() {
		StringCalculator calculator = new StringCalculator();
		String addNumbers = "//;1;2\n";
		assertTrue(calculator.isDifferentDelimiter(addNumbers));
	}

	@Test
	public void shouldReturnFalseIfCommaExistsAsDelimiter() {
		StringCalculator calculator = new StringCalculator();
		String addNumbers = "/;1;2\n";
		assertFalse(calculator.isDifferentDelimiter(addNumbers));
	}

	@Test
	public void createValidateRegExpTestWithSemiColon() {
		StringCalculator calculator = new StringCalculator();
		String addNumbers = "//;1;2\n";
		Pattern createValidateInputRegEx = calculator.createValidateInputRegEx(addNumbers);
		assertEquals(calculator.validateInputRegEx.replace(',', ';'), createValidateInputRegEx.toString());
	}

	@Test
	public void shouldReturnExceptionWhenInputIsNegativeNumber() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "-11,\n-2";
		thrown.expectMessage("negatives not allowed");
		stringCalculator.add(addNumbers);

	}

	@Test
	public void negativeNumberMatchReturnsTrue() {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "-11,\n1";
		Pattern pattern = Pattern.compile(stringCalculator.validateInputRegEx);
		Matcher matcher = pattern.matcher(addNumbers);
		assertFalse(matcher.matches());
	}

	@Test
	public void nonNegativeNumberMatchReturnsTrue() {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "11,2\n1";
		Pattern pattern = Pattern.compile(stringCalculator.validateInputRegEx);
		Matcher matcher = pattern.matcher(addNumbers);
		assertTrue(matcher.matches());
	}

	@Test
	public void getListOfNegativeNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "-11,\n-2";
		List<String> negativeNumbers = stringCalculator.getListOfNegativeNumbers(addNumbers);
		assertEquals(negativeNumbers.get(0), "-11");
	}

}
