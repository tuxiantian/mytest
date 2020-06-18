package com.tuxt.mytest.args;

public class ArgsTest {

	public static void main(String[] args) {
		/*
		-l
		-p
		12
		-d
		a
		*/
		try {
			String[] arrs=new String[]{"-d","param"};
			Args arg=new Args("l,p#,d*",args);//
			boolean b=arg.getBoolean('l');
			int i=arg.getInt('p');
			String string=arg.getString('d');
			System.out.println(b);
			System.out.println(i);
			System.out.println(string);
		} catch (ArgsException e) {
			System.out.printf("Argument error:%s\n",e.errorMessage());
		}
	}

}
