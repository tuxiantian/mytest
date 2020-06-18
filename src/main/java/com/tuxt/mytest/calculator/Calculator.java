package com.tuxt.mytest.calculator;


public class Calculator {
	private double firstNumber;
	private double secondNumber;
	private CalculateMethod method;
	public Calculator(double firstNumber,double secondNumber,String method){
		this.firstNumber=firstNumber;
		this.secondNumber=secondNumber;
		switch (method) {
		case "+":
			this.method=new Add();
			break;
		case "-":
			this.method=new Minus();
			break;
		case "*":
			this.method=new Multiply();
			break;
		case "/":
			this.method=new Devide();
			break;
		default:
			break;
		}
	}
	public double calcute() {
		return method.calculate(firstNumber, secondNumber);
	}
	public static void main(String[] args) {
		Calculator calculator=new Calculator(1, 2, "+");
		System.out.println(calculator.calcute());
	}

}
