package com.tuxt.mytest.args;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.tuxt.mytest.args.ErrorCode.INVALID_DOUBLE;
import static com.tuxt.mytest.args.ErrorCode.MISSING_DOUBLE;

public class DoubleArgumentMarshaler implements ArgumentMarshaler{

	private double doubleValue;
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter=null;
		try {
			parameter=currentArgument.next();
			doubleValue=Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_DOUBLE);
		}catch (NumberFormatException e) {
			throw new ArgsException(INVALID_DOUBLE,parameter);
		}
		
	}

	public static double getValue(ArgumentMarshaler am) {
		if (am!=null && am instanceof DoubleArgumentMarshaler) {
			return ((DoubleArgumentMarshaler) am).doubleValue;
		}else {
			return 0.0;
		}
	}
}
