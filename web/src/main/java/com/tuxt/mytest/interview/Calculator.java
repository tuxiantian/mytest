package com.tuxt.mytest.interview;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
	private double firstNumber;
	private double secondNumber;
	private Operate operate;
	enum Operate{
		add("+"),minus("-"),multiply("*"),devide("/");
		private String operator;
		Operate(String operator){
			this.operator=operator;
		}
		
		String value(){
			return this.operator;
		}
		/**
		 * 字符串类型转枚举：方式一
		 */
		static final Map<String, Operate> operateMap=new HashMap<String, Operate>();
		static{
			for (Operate operate : values()) {
				operateMap.put(operate.value(), operate);
			}
		}
		static Operate	getInstance(String oper){
			return operateMap.get(oper);
		}
		/**
		 * 字符串类型转枚举：方式二
		 * @param ope
		 * @return
		 */
		static Operate instance(String ope){
			for (Operate operate:values()) {
				if (operate.value().equals(ope)) {
					return operate;
				}
			}
			return null;
		}
	}
	
	
	public Calculator(double firstNumber, double secondNumber, Operate operate) {
		super();
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.operate = operate;
	}
	public Calculator(double firstNumber, double secondNumber, String operate) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
//		this.operate = Operate.instance(operate);
		this.operate=Operate.getInstance(operate);
	}
	
	public double calcute() {
		double result = 0;
		switch (this.operate) {
		case add:
			result=firstNumber+secondNumber;
			break;
		case minus:
			result=firstNumber-secondNumber;
		case multiply:
			result=firstNumber*secondNumber;
		case devide:
			result=firstNumber/secondNumber;
		default:
			throw new RuntimeException("not support operate");
		}
		return result;
	}
	public static void main(String[] args) {
		Calculator calculator1=new Calculator(1, 2, Operate.add);
		Calculator calculator2=new Calculator(1, 2, "+");
		System.out.println(calculator1.calcute());	
		System.out.println(calculator2.calcute());	
		for (Operate ope : Operate.values()) {
			System.out.println(ope.value());
		}
	}

}
