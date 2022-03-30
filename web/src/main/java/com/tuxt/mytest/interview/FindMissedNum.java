package com.tuxt.mytest.interview;


public class FindMissedNum {
	public static int[] find_missing_two_number(int a[], int size){
		int miss1 = 0;  
	    int miss2 = 0;  
	    int number=0;  
	    for (int i=0;i<size;i++)  
	        number ^= ((i+1)^a[i]);  
	    number ^= (size+1);  
	    number ^= (size+2);   
	      
	    // now number will be miss1^miss2  
	    // find the binary 1 in number  
	    int k = number - (number&(number-1));  
	    for (int i=0;i<size;i++)  
	    {  
	        if ( ((i+1)&k)==1)  
	            miss1 ^= (i+1);  
	        if ((a[i]&k)==1)  
	            miss1 ^= a[i];  
	    }  
	    if ( ((size+1) & k)==1 )  
	        miss1 ^= size+1;  
	    if ( ((size+2) & k )==1)  
	        miss1 ^= size+2;  
	    miss2 = number ^ miss1;  
	    return new int[]{miss1,miss2};
	}
	public static int find_missing_number1 (int a[], int size)  
	{  
	    int number=0;  
	    for (int i=0;i<size;i++)  
	        number ^= ((i+1)^a[i]);  
	    number ^= (size+1);  
	    return number;  
	}  
	public static int find_missing_number2 (int a[]){
		int sum1=0;
		for (int i = 0; i < a.length; i++) {
			sum1+=a[i];
		}
		return (sum(a.length+1)-sum1);
	}
	public static int sum(int n){
		return ((1+n)*n)/2;
	}
	
	public static void main(String[] args) {
		int a[]={1,3,2,4,6,5,8,9};
		//System.out.println(find_missing_number1(a, a.length));
		//System.out.println(find_missing_number2(a));
		int[] missArr= find_missing_two_number(a, 8);
		System.out.println("miss1:"+missArr[0]+",miss2:"+missArr[1]);
		//System.out.println(7^10);
	}

}
