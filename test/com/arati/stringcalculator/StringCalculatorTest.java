package com.arati.stringcalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void PatternMatcher() throws Exception{
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1\n2,3";
		String stringToOperateOn = stringCalculator.getStringToOperateOn(addNumbers);
		Pattern validateInputRegEx = stringCalculator.createValidateInputRegEx(stringToOperateOn);
		boolean validInput = stringCalculator.validInput(stringToOperateOn, validateInputRegEx);
		assertTrue(validInput);
	}
	
	
	
	@Test
	public void Pattern1Matcher() throws Exception{
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
	public void getStringToOperateOn(){
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//;\n1;2";
		assertEquals("\n1;2", stringCalculator.getStringToOperateOn(addNumbers) );
	}

	@Test
	public void validString() {
		StringCalculator stringCalculator = new StringCalculator();
		String s = "1,\n,2,3";
		String s2 = "1\n2,3";
		String regex = "^((\\n*\\d*(;)*)|((;)*\\d*\\n*))*\\d+$";
		assertTrue(s2.matches(stringCalculator.validateInputRegEx));
		String addNumbers = ";\n1;2";
		assertTrue(s.matches(stringCalculator.validateInputRegEx));
		assertTrue(addNumbers.matches(regex));
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
	public void shouldReturnException() throws Exception{
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
		String addNumbers="//;1;2\n";
		Pattern createValidateInputRegEx = calculator.createValidateInputRegEx(addNumbers);
		assertEquals(calculator.validateInputRegEx.replace(',', ';'), createValidateInputRegEx.toString());
	}
	

	@Rule
	public ExpectedException thrown1 = ExpectedException.none();

	@Test
	public void shouldReturnExceptionWhenInputIsNegativeNumber() throws Exception{
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "-11,\n";
		thrown.expectMessage("negatives not allowed -11");
		stringCalculator.add(addNumbers);
		
	}
	
	@Test
	public void findNumbera(){
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "1";
		//stringCalculator.findNumbers(addNumbers);
		Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
		Matcher matcher = pattern.matcher(addNumbers);
		String pageNumber = matcher.group(1);
		assertEquals("-11",pageNumber);
	}

}
