package com.tuxt.mytest.calculator;

public class Devide implements CalculateMethod {
	@Override
	public double calculate(double firstNumber, double secondNumber) {
		if (secondNumber==0) {
			throw new ArithmeticException(firstNumber+"devide by zero");
		}
		return firstNumber/secondNumber;
	}

}
