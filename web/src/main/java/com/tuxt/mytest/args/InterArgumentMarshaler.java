package com.tuxt.mytest.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.tuxt.mytest.args.ErrorCode.INVALID_INTEGER;
import static com.tuxt.mytest.args.ErrorCode.MISSING_INTEGER;

public class InterArgumentMarshaler implements ArgumentMarshaler {

	private int intValue;
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter=null;
		try {
			parameter=currentArgument.next();
			intValue=Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_INTEGER);
		}catch (NumberFormatException e) {
			throw new ArgsException(INVALID_INTEGER,parameter);
		}
	}

	public static int getValue(ArgumentMarshaler am) {
		if (am!=null && am instanceof InterArgumentMarshaler) {
			return ((InterArgumentMarshaler) am).intValue;
		}else {
			return 0;
		}
	}
}
