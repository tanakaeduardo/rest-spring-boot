package com.tanaka.math;

public class SimpleMath {

	public Double sun (Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public Double sub (Double numberOne, Double numberTwo ) {
		return numberOne - numberTwo;
	}
	
	public Double mult (Double numberOne, Double numberTwo ) {
		return numberOne * numberTwo;
	}
	
	public Double div (Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public Double mean (Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo)/2;
	}
	
	public Double squareRoot (Double number) {
		return Math.sqrt(number);
	}
}
