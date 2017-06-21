package com.arati.stringcalculator;

import static org.junit.Assert.*;

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
	public void SemiColonAndStringSumShouldReturnCorrectSum() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		String addNumbers = "//;\n1;2";
		int sum = stringCalculator.add(addNumbers);
		assertEquals(3, sum);
	}

	@Test
	public void validString() {
		String regExp = "^\\d*(((\\n)*\\d*(,)*)|((,)*\\d*(\\n)*))*\\d+$";
		String regExp1 = "^\\d*(((\\n)*\\d*(;)*)|((;)*\\d*(\\n)*))*\\d+$";
		String s = "1,\n,2,3";
		String addNumbers = "//;1;2\n";
		assertTrue(s.matches(regExp));
		assertTrue(addNumbers.matches(regExp1));
	}

	@Test
	public void invalidString() {
		String regExp = "^\\d*(((\\n)*\\d*(,)*)|((,)*\\d*(\\n)*))*\\d+$";
		String s = "1,\n";
		assertFalse(s.matches(regExp));
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
		String createValidateInputRegEx = calculator.createValidateInputRegEx(addNumbers);
		assertEquals("^\\d*(((\\n)*\\d*(;)*)|((;)*\\d*(\\n)*))*\\d+$", createValidateInputRegEx);
	}

}
