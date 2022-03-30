package com.tuxt.mytest.interview;

public class MyFindMissedNum {
	public static double average(int a[]){
		int size=a.length;
		return (sum2(size+2)-sum(a))/2.0;
	}
	public static int sum2(int n){
		return ((1+n)*n)/2;
	}
	public static int sum(int a[]){
		int sum=0;
		for(int i=0;i<a.length;i++){
			sum+=a[i];
		}
		return sum;
	}
	public static boolean isInArray(int a[],double x){
		for (int i = 0; i < a.length; i++) {
			if (a[i]==x) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int a[]={1,3,2,5,8,9,7};
		double x=average(a);
		for (int i = 1; (x+i-x%1)<=(a.length+2)&& (x-i+x%1)>=1; i++) {
			if (isInArray(a,x+i-x%1)) {
				continue;
			}else {
				System.out.println(x+i-x%1+" "+(x-i+x%1));
			}
		}

	}

}
