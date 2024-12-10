package com.tanaka.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tanaka.converters.NumberConverter;
import com.tanaka.exceptions.UnsupportedMathOperationException;
import com.tanaka.math.SimpleMath;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	
	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double sun (
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sun(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double sub (
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sub(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double mult (
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.mult(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double div (
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.div(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double mean (
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.mean(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/squareRoot/{number}", 
			method=RequestMethod.GET)
	public Double squareRoot (
			@PathVariable(value="number") String number
			) throws Exception{
		
		if (!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.squareRoot(NumberConverter.convertToDouble(number));
	}

}
