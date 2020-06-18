package com.tuxt.mytest.util;

/**
 * 字符串工具类
 * 
 * @author 
 * @since 2015-03-17 21:09:43
 */
public final class StringUtil {
	/** Private Constructor **/
	private StringUtil(){
	}
	
	/**
	 * 判断字符串是否非null && 非空字符 
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isNotEmpty(String param) {
		return param != null && !"".equals(param.trim());
	}

	/**
	 * 判断字符串是否为null || 空字符串
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String param) {
		return param == null || "".equals(param.trim()) || param.equals("null");
	}
	
	public static boolean isEmptyExcludeNull(String param) {
		String str = trim(param);
		return str.length() < 1;
	}
	
	public static String trim(String str) {
		return trim(str, "");
	}
	
	public static String trim(String str, String defStr) {
		return (str == null) || ("null".equals(str)) ? defStr : str.trim();
	}
	
	public static String delStrAllQuo(String str,int length) {
		if (StringUtil.isEmptyExcludeNull(str)) {
			return "";
		}
		str = str.replaceAll("\\n"," ").replaceAll("\\s{2,}"," ").replaceAll("\\\\\"", "").replaceAll("[\"']", "");
		if (str.length() >= length) {
			str = str.substring(0, Math.max(length-10,0));
		}
		return new String(str);
	}
	
	public static void printConsole(String str){
		boolean flag = true ;
		try{
			flag = Boolean.parseBoolean(PropertiesUtil.getString("PRINT_SWITCH"));
		}catch(Exception e){
			flag = true;
		}
		if(flag){
			System.out.println(str);
		}
	}
	
	public static String[] splitStr(String str,int splitNum,int strMaxLen){
		if(splitNum<1){
			splitNum=1;
		}
		String[] resultArr=new String[splitNum];
		if (StringUtil.isEmptyExcludeNull(str)) {
			resultArr[0]=str;
			return resultArr;
		}
		str = str.replaceAll("\\n"," ").replaceAll("\\s{2,}"," ");
		int strLen=str.length();
		if(strLen<strMaxLen){
			resultArr[0]=str;
			return resultArr;
		}
		int kLen=(strLen-1)/strMaxLen+1;
		for(int i=1,len=Math.min(kLen, splitNum);i<=len;i++){
			resultArr[i-1]=new String(str.substring((i-1)*strMaxLen, Math.min(i*strMaxLen, strLen)));
		}
		str=null;
		return resultArr;
	}

	public static String defaultValue(String str,String defalut){
		if(StringUtil.isEmpty(str))
			return defalut;
		else
			return str;
	}
}
