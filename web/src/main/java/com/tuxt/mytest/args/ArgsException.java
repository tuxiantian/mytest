package com.tuxt.mytest.args;

import static com.tuxt.mytest.args.ErrorCode.OK;

public class ArgsException extends Exception{

	private char errorArgumentId='\0';
	private String errorParameter;
	private ErrorCode errorCode=OK;
	public ArgsException() {
	}
	public ArgsException(String message) {
		super(message);
	}
	public ArgsException(ErrorCode errorCode) {
		this.errorCode=errorCode;
	}
	public ArgsException(ErrorCode errorCode,String errorParameter) {
		this.errorCode=errorCode;
		this.errorParameter=errorParameter;
	}
	public ArgsException(ErrorCode errorCode,char errorArgumentId,String errorParameter) {
		this.errorCode=errorCode;
		this.errorArgumentId=errorArgumentId;
		this.errorParameter=errorParameter;
	}
	public char getErrorArgumentId() {
		return errorArgumentId;
	}
	public void setErrorArgumentId(char errorArgumentId) {
		this.errorArgumentId = errorArgumentId;
	}
	public String getErrorParameter() {
		return errorParameter;
	}
	public void setErrorParameter(String errorParameter) {
		this.errorParameter = errorParameter;
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public String errorMessage() {
		switch (errorCode) {
		case OK:
			return "TITLE:Should not get here.";
		case UNEXPECTED_ARGUMENT:
			return String.format("Argument -%c unexpected.", errorArgumentId);
		case MISSING_STRING:
			return String.format("Could not find string parameter -%c.", errorArgumentId);
		case INVALID_INTEGER:
			return String.format("Argument -%c expects an integer but was '%s'.", errorArgumentId,errorParameter);
		case MISSING_INTEGER:
			return String.format("Could not find integer parameter -%c.", errorArgumentId);
		case INVALID_DOUBLE:
			return String.format("Argument -%c expects a double but was '%s'.", errorArgumentId,errorParameter);
		case MISSING_DOUBLE:
			return String.format("Could not find double parameter -%c.", errorArgumentId);
		case INVALID_ARGUMENT_NAME:
			return String.format("'%c' is not a valid argument name.", errorArgumentId);
		case INVALID_ARGUMENT_FORMAT:
			return String.format("'%c' is not a valid argument formart.", errorArgumentId);
		default:
			break;
		}
		return "";
	}
}
