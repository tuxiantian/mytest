package com.tuxt.mytest.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import static test.args.ErrorCode.*;

/**
 * 命令行参数解析工具类
 * @author tuxiantian@163.com
 *
 */
public class Args {

	private Map<Character, ArgumentMarshaler> marshalers;
	private Set<Character> argsFound;
	private ListIterator<String> currentArgument;
	public Args(String schema,String[] args) throws ArgsException{
		marshalers=new HashMap<>();
		argsFound=new HashSet<>();
		parseSchema(schema);
		parseArgumentStrings(Arrays.asList(args));
	}
	private void parseSchema(String schema)throws ArgsException {
		for (String element : schema.split(",")) {
			if (element.length()>0) {
				parseSchemaElement(element);
			}
		}
	}
	private void parseSchemaElement(String element)throws ArgsException {
		char elementId=element.charAt(0);
		String elementTail=element.substring(1);
		validateSchemaElementId(elementId);
		if (elementTail.length()==0) {
			marshalers.put(elementId, new BooleanArgumentMarshaler());
		}else if (elementTail.equals("*")) {
			marshalers.put(elementId, new StringArgumentMarshaler());
		}else if (elementTail.equals("#")) {
			marshalers.put(elementId, new InterArgumentMarshaler());
		}else if (elementTail.equals("##")) {
			marshalers.put(elementId, new DoubleArgumentMarshaler());
		}else {
			throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
		}
	}
	private void validateSchemaElementId(char elementId)throws ArgsException {
		if (!Character.isLetter(elementId)) {
			throw new ArgsException(INVALID_ARGUMENT_NAME, elementId,null);
		}
	}
	private void parseArgumentStrings(List<String> argsList) throws ArgsException{
		for(currentArgument=argsList.listIterator();currentArgument.hasNext();){
			String argString=currentArgument.next();
			if (argString.startsWith("-")) {
				parseArgumentCharacters(argString.substring(1));
			}else {
				currentArgument.previous();
				break;
			}
		}
	}
	private void parseArgumentCharacters(String argChars)throws ArgsException {
		for (int i = 0; i < argChars.length(); i++) {
			parseArgumentCharacter(argChars.charAt(i));
		}
	}
	
	private void parseArgumentCharacter(char argChar)throws ArgsException {
		ArgumentMarshaler marshaler=marshalers.get(argChar);
		if (marshaler==null) {
			throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
		}else {
			argsFound.add(argChar);
			try {
				marshaler.set(currentArgument);
			} catch (ArgsException e) {
				e.setErrorArgumentId(argChar);
				throw e;
			}
		}
	}
	public boolean has(char arg) {
		return argsFound.contains(arg);
	}
	public int nextArgument() {
		return currentArgument.nextIndex();
	}
	public boolean getBoolean(char arg) {
		return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
	}
	public String getString(char arg) {
		return StringArgumentMarshaler.getValue(marshalers.get(arg));
	}
	public int getInt(char arg) {
		return InterArgumentMarshaler.getValue(marshalers.get(arg));
	}
	public double getDouble(char arg) {
		return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
	}
}
